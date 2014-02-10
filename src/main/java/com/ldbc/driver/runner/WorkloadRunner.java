package com.ldbc.driver.runner;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.ldbc.driver.*;
import com.ldbc.driver.metrics.WorkloadMetricsManager;
import com.ldbc.driver.temporal.Duration;
import com.ldbc.driver.temporal.Time;
import org.apache.log4j.Logger;

import java.util.Iterator;

public class WorkloadRunner {
    private static Logger logger = Logger.getLogger(WorkloadRunner.class);

    public static final Duration DEFAULT_TOLERATED_OPERATION_START_TIME_DELAY = Duration.fromSeconds(1);
    private final Duration DEFAULT_STATUS_UPDATE_INTERVAL = Duration.fromSeconds(2);
    private final boolean IGNORE_SCHEDULED_START_TIME = false;

    private final Db db;
    private final Spinner spinner;
    private final OperationHandlerExecutor operationHandlerExecutor;
    private final MetricsLoggingThread metricsLoggingThread;
    private final WorkloadStatusThread workloadStatusThread;
    private final Iterable<OperationHandler<?>> operationHandlers;
    private final WorkloadMetricsManager metricsManager;
    private final boolean showStatus;
    private final ConcurrentErrorReporter concurrentErrorReporter = new ConcurrentErrorReporter();

    public WorkloadRunner(Db db, Iterator<Operation<?>> operations, boolean showStatus, int threadCount,
                          WorkloadMetricsManager metricsManager) throws WorkloadException {
        this.db = db;
        // TODO make Spinner & OperationSchedulingPolicy configurable
        this.spinner = new Spinner(new ErrorLoggingOperationSchedulingPolicy(
                DEFAULT_TOLERATED_OPERATION_START_TIME_DELAY, IGNORE_SCHEDULED_START_TIME));
        this.operationHandlerExecutor = new ThreadPoolOperationHandlerExecutor(threadCount, concurrentErrorReporter);
        this.metricsLoggingThread = new MetricsLoggingThread(operationHandlerExecutor, metricsManager, concurrentErrorReporter);
        Duration statusInterval = DEFAULT_STATUS_UPDATE_INTERVAL;
        this.workloadStatusThread = new WorkloadStatusThread(statusInterval, metricsManager);
        this.operationHandlers = ImmutableList.copyOf(operationsToOperationHandlers(operations, spinner));
        this.metricsManager = metricsManager;
        this.showStatus = showStatus;
    }

    public void run() throws WorkloadException {
        metricsManager.setStartTime(Time.now());
        metricsLoggingThread.start();
        if (showStatus) workloadStatusThread.start();
        for (OperationHandler<?> operationHandler : operationHandlers) {
            // This occurs in OperationHandler too
            // TODO do this here, but schedule earlier by some amount to account for context switch latency
            spinner.waitForScheduledStartTime(operationHandler.getOperation());
            operationHandlerExecutor.execute(operationHandler);
            if (concurrentErrorReporter.errorEncountered()) {
                String errMsg = String.format("Benchmark terminating due to fatal error!\n" +
                        "Number of operations completed before termination: %s\n%s",
                        metricsManager.getMeasurementCount(), concurrentErrorReporter.toString());
                throw new WorkloadException(errMsg);
            }
        }

        try {
            metricsLoggingThread.finishLoggingRemainingResults();
            metricsLoggingThread.join();
            if (showStatus) workloadStatusThread.interrupt();
        } catch (InterruptedException e) {
            logger.error("Error encountered while waiting for logging thread to finish", e);
        }

        try {
            operationHandlerExecutor.shutdown();
        } catch (OperationHandlerExecutorException e) {
            logger.error("Error encountered while shutting down operation handler executor", e);
        }
    }

    private Iterator<OperationHandler<?>> operationsToOperationHandlers(Iterator<Operation<?>> operations, final Spinner spinner) throws WorkloadException {
        try {
            return Iterators.transform(operations, new Function<Operation<?>, OperationHandler<?>>() {
                @Override
                public OperationHandler<?> apply(Operation<?> operation) {
                    try {
                        OperationHandler<?> operationHandler = db.getOperationHandler(operation);
                        operationHandler.init(spinner, operation);
                        return operationHandler;
                    } catch (DbException e) {
                        throw new RuntimeException();
                    }
                }
            });
        } catch (RuntimeException e) {
            String errMsg = "Error encountered while transforming Operation stream to OperationHandler stream";
            logger.error(errMsg, e);
            throw new WorkloadException(errMsg, e.getCause());
        }
    }

}
