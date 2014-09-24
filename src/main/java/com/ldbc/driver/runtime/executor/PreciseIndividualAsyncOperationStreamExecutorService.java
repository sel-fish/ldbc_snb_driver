package com.ldbc.driver.runtime.executor;

import com.ldbc.driver.Db;
import com.ldbc.driver.Operation;
import com.ldbc.driver.OperationClassification;
import com.ldbc.driver.runtime.ConcurrentErrorReporter;
import com.ldbc.driver.runtime.coordination.GlobalCompletionTimeReader;
import com.ldbc.driver.runtime.coordination.LocalCompletionTimeWriter;
import com.ldbc.driver.runtime.metrics.ConcurrentMetricsService;
import com.ldbc.driver.runtime.scheduling.Spinner;
import com.ldbc.driver.temporal.Duration;
import com.ldbc.driver.temporal.TimeSource;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class PreciseIndividualAsyncOperationStreamExecutorService {
    private static final Duration SHUTDOWN_WAIT_TIMEOUT = Duration.fromSeconds(10);

    private final TimeSource TIME_SOURCE;
    private final PreciseIndividualAsyncOperationStreamExecutorServiceThread preciseIndividualAsyncOperationStreamExecutorServiceThread;
    private final AtomicBoolean hasFinished = new AtomicBoolean(false);
    private final ConcurrentErrorReporter errorReporter;
    private final AtomicBoolean executing = new AtomicBoolean(false);
    private final AtomicBoolean shutdown = new AtomicBoolean(false);
    private final AtomicBoolean forceThreadToTerminate = new AtomicBoolean(false);

    public PreciseIndividualAsyncOperationStreamExecutorService(TimeSource timeSource,
                                                                ConcurrentErrorReporter errorReporter,
                                                                Iterator<Operation<?>> gctReadOperations,
                                                                Iterator<Operation<?>> gctWriteOperations,
                                                                Spinner spinner,
                                                                Spinner slightlyEarlySpinner,
                                                                OperationHandlerExecutor operationHandlerExecutor,
                                                                Map<Class<? extends Operation>, OperationClassification> operationClassifications,
                                                                Db db,
                                                                LocalCompletionTimeWriter localCompletionTimeWriter,
                                                                GlobalCompletionTimeReader globalCompletionTimeReader,
                                                                ConcurrentMetricsService metricsService,
                                                                Duration durationToWaitForAllHandlersToFinishBeforeShutdown) {
        this.TIME_SOURCE = timeSource;
        this.errorReporter = errorReporter;
        if (gctReadOperations.hasNext() || gctWriteOperations.hasNext()) {
            this.preciseIndividualAsyncOperationStreamExecutorServiceThread = new PreciseIndividualAsyncOperationStreamExecutorServiceThread(
                    TIME_SOURCE,
                    operationHandlerExecutor,
                    errorReporter,
                    gctReadOperations,
                    gctWriteOperations,
                    hasFinished,
                    spinner,
                    slightlyEarlySpinner,
                    forceThreadToTerminate,
                    operationClassifications,
                    db,
                    localCompletionTimeWriter,
                    globalCompletionTimeReader,
                    metricsService,
                    durationToWaitForAllHandlersToFinishBeforeShutdown);
        } else {
            this.preciseIndividualAsyncOperationStreamExecutorServiceThread = null;
            executing.set(true);
            hasFinished.set(true);
            shutdown.set(false);
        }
    }

    synchronized public AtomicBoolean execute() {
        if (executing.get())
            return hasFinished;
        executing.set(true);
        preciseIndividualAsyncOperationStreamExecutorServiceThread.start();
        return hasFinished;
    }

    synchronized public void shutdown() throws OperationHandlerExecutorException {
        if (shutdown.get()) {
            throw new OperationHandlerExecutorException("Executor has already been shutdown");
        }
        if (null != preciseIndividualAsyncOperationStreamExecutorServiceThread)
            doShutdown();
        shutdown.set(true);
    }

    private void doShutdown() {
        try {
            forceThreadToTerminate.set(true);
            preciseIndividualAsyncOperationStreamExecutorServiceThread.join(SHUTDOWN_WAIT_TIMEOUT.asMilli());
        } catch (Exception e) {
            String errMsg = String.format("Unexpected error encountered while shutting down thread\n%s",
                    ConcurrentErrorReporter.stackTraceToString(e));
            errorReporter.reportError(this, errMsg);
        }
    }
}
