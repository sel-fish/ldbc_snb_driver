package com.ldbc.driver.workloads.ldbc.snb.bi;

import com.google.common.collect.Lists;
import com.ldbc.driver.ClientException;
import com.ldbc.driver.Operation;
import com.ldbc.driver.Workload;
import com.ldbc.driver.WorkloadException;
import com.ldbc.driver.control.ConsoleAndFileDriverConfiguration;
import com.ldbc.driver.control.DriverConfiguration;
import com.ldbc.driver.control.DriverConfigurationException;
import com.ldbc.driver.testutils.TestUtils;
import com.ldbc.driver.util.Bucket;
import com.ldbc.driver.util.Histogram;
import com.ldbc.driver.util.MapUtils;
import com.ldbc.driver.util.Tuple;
import com.ldbc.driver.util.Tuple2;
import com.ldbc.driver.util.TypeChangeFun;
import com.ldbc.driver.validation.ClassNameWorkloadFactory;
import com.ldbc.driver.workloads.OperationMixBuilder;
import com.ldbc.driver.workloads.WorkloadTest;
import com.ldbc.driver.workloads.ldbc.snb.bi.db.DummyLdbcSnbBiDb;
import com.ldbc.driver.workloads.ldbc.snb.bi.db.DummyLdbcSnbBiOperationInstances;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BiWorkloadTest extends WorkloadTest
{
    @Override
    public Workload workload() throws Exception
    {
        DriverConfiguration configuration = ConsoleAndFileDriverConfiguration
                .fromDefaults( DummyLdbcSnbBiDb.class.getName(), LdbcSnbBiWorkload.class.getName(), 1 )
                .applyArgs( LdbcSnbBiWorkloadConfiguration.defaultConfigSF1() )
                .applyArg(
                        LdbcSnbBiWorkloadConfiguration.PARAMETERS_DIRECTORY,
                        TestUtils.getResource( "/snb/bi/" ).getAbsolutePath()
                );
        Workload workload = new ClassNameWorkloadFactory( configuration.workloadClassName() ).createWorkload();
        workload.init( configuration );
        return workload;
    }

    @Override
    public List<Operation> operations()
    {
        return Lists.<Operation>newArrayList(
                DummyLdbcSnbBiOperationInstances.read1(),
                DummyLdbcSnbBiOperationInstances.read2(),
                DummyLdbcSnbBiOperationInstances.read3(),
                DummyLdbcSnbBiOperationInstances.read4(),
                DummyLdbcSnbBiOperationInstances.read5(),
                DummyLdbcSnbBiOperationInstances.read6(),
                DummyLdbcSnbBiOperationInstances.read7(),
                DummyLdbcSnbBiOperationInstances.read8(),
                DummyLdbcSnbBiOperationInstances.read9(),
                DummyLdbcSnbBiOperationInstances.read10(),
                DummyLdbcSnbBiOperationInstances.read11(),
                DummyLdbcSnbBiOperationInstances.read12(),
                DummyLdbcSnbBiOperationInstances.read13(),
                DummyLdbcSnbBiOperationInstances.read14(),
                DummyLdbcSnbBiOperationInstances.read15(),
                DummyLdbcSnbBiOperationInstances.read16(),
                DummyLdbcSnbBiOperationInstances.read17(),
                DummyLdbcSnbBiOperationInstances.read18(),
                DummyLdbcSnbBiOperationInstances.read19(),
                DummyLdbcSnbBiOperationInstances.read20(),
                DummyLdbcSnbBiOperationInstances.read21(),
                DummyLdbcSnbBiOperationInstances.read22(),
                DummyLdbcSnbBiOperationInstances.read23(),
                DummyLdbcSnbBiOperationInstances.read24()
        );
    }

    @Override
    public List<DriverConfiguration> configurations() throws Exception
    {
        return Lists.newArrayList(
                ConsoleAndFileDriverConfiguration
                        .fromDefaults(
                                DummyLdbcSnbBiDb.class.getName(),
                                LdbcSnbBiWorkload.class.getName(),
                                1_000_000
                        )
                        .applyArgs( LdbcSnbBiWorkloadConfiguration.defaultConfigSF1() )
                        .applyArg( ConsoleAndFileDriverConfiguration.IGNORE_SCHEDULED_START_TIMES_ARG, "true" )
                        .applyArg(
                                LdbcSnbBiWorkloadConfiguration.PARAMETERS_DIRECTORY,
                                TestUtils.getResource( "/snb/bi/" ).getAbsolutePath()
                        )
        );
    }

    @Override
    public List<Tuple2<DriverConfiguration,Histogram<Class,Double>>> configurationsWithExpectedQueryMix()
            throws Exception
    {
        Histogram<Class,Double> expectedQueryMixHistogram = new Histogram<>( 0d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery1.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery2.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery3.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery4.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery5.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery6.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery7.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery8.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery9.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery10.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery11.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery12.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery13.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery14.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery15.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery16.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery17.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery18.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery19.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery20.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery21.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery22.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery23.class ), 1d );
        expectedQueryMixHistogram.addBucket( Bucket.DiscreteBucket.<Class>create( LdbcSnbBiQuery24.class ), 1d );

        return Lists.newArrayList(
                Tuple.tuple2(
                        ConsoleAndFileDriverConfiguration
                                .fromDefaults(
                                        DummyLdbcSnbBiDb.class.getName(),
                                        LdbcSnbBiWorkload.class.getName(),
                                        1_000_000
                                )
                                .applyArgs( LdbcSnbBiWorkloadConfiguration.defaultConfigSF1() )
                                .applyArg( ConsoleAndFileDriverConfiguration.IGNORE_SCHEDULED_START_TIMES_ARG, "true" ),
                        expectedQueryMixHistogram
                )
        );
    }

    @Test
    public void shouldConvertFrequenciesToInterleavesWhenFrequenciesProvidedAndSomeInterleavesNotProvided()
            throws WorkloadException, DriverConfigurationException, IOException
    {
        // Given
        long updateInterleave = 10;
        OperationMixBuilder.OperationMix operationMix = OperationMixBuilder.fromFrequencies( updateInterleave )
                .addOperationFrequency( LdbcSnbBiQuery1.TYPE, 10 )
                .addOperationFrequency( LdbcSnbBiQuery2.TYPE, 20 )
                .addOperationFrequency( LdbcSnbBiQuery3.TYPE, 30 )
                .addOperationFrequency( LdbcSnbBiQuery4.TYPE, 40 )
                .addOperationFrequency( LdbcSnbBiQuery5.TYPE, 50 )
                .addOperationFrequency( LdbcSnbBiQuery6.TYPE, 60 )
                .addOperationFrequency( LdbcSnbBiQuery7.TYPE, 70 )
                .addOperationFrequency( LdbcSnbBiQuery8.TYPE, 80 )
                .addOperationFrequency( LdbcSnbBiQuery9.TYPE, 90 )
                .addOperationFrequency( LdbcSnbBiQuery10.TYPE, 100 )
                .addOperationFrequency( LdbcSnbBiQuery11.TYPE, 200 )
                .addOperationFrequency( LdbcSnbBiQuery12.TYPE, 300 )
                .addOperationFrequency( LdbcSnbBiQuery13.TYPE, 400 )
                .addOperationFrequency( LdbcSnbBiQuery14.TYPE, 500 )
                .addOperationFrequency( LdbcSnbBiQuery15.TYPE, 600 )
                .addOperationFrequency( LdbcSnbBiQuery16.TYPE, 700 )
                .addOperationFrequency( LdbcSnbBiQuery17.TYPE, 800 )
                .addOperationFrequency( LdbcSnbBiQuery18.TYPE, 900 )
                .addOperationFrequency( LdbcSnbBiQuery19.TYPE, 1000 )
                .addOperationFrequency( LdbcSnbBiQuery20.TYPE, 10 )
                .addOperationFrequency( LdbcSnbBiQuery21.TYPE, 20 )
                .addOperationFrequency( LdbcSnbBiQuery22.TYPE, 30 )
                .addOperationFrequency( LdbcSnbBiQuery23.TYPE, 40 )
                .addOperationFrequency( LdbcSnbBiQuery24.TYPE, 50 )
                .build();
        DriverConfiguration configuration = ConsoleAndFileDriverConfiguration.fromDefaults(
                DummyLdbcSnbBiDb.class.getName(),
                LdbcSnbBiWorkload.class.getName(),
                1
        ).applyArgs(
                LdbcSnbBiWorkloadConfiguration.defaultConfigSF1()
        ).applyArg(
                LdbcSnbBiWorkloadConfiguration.PARAMETERS_DIRECTORY,
                TestUtils.getResource( "/snb/bi/" ).getAbsolutePath()
        ).applyArg( ConsoleAndFileDriverConfiguration.IGNORE_SCHEDULED_START_TIMES_ARG, "true"
        ).applyArgs(
                MapUtils.UNSAFE_changeTypes(
                        operationMix.interleaves(),
                        TypeChangeFun.mapped( LdbcSnbBiWorkloadConfiguration.OPERATION_TYPE_TO_INTERLEAVE_KEY_MAPPING ),
                        TypeChangeFun.TO_STRING
                )
        );

        // When
        try ( Workload workload = new LdbcSnbBiWorkload() )
        {
            workload.init( configuration );

            // Then

            Map<String,String> configurationAsMap = configuration.asMap();
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_1_INTERLEAVE_KEY ),
                    equalTo( "100" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_2_INTERLEAVE_KEY ),
                    equalTo( "200" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_3_INTERLEAVE_KEY ),
                    equalTo( "300" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_4_INTERLEAVE_KEY ),
                    equalTo( "400" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_5_INTERLEAVE_KEY ),
                    equalTo( "500" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_6_INTERLEAVE_KEY ),
                    equalTo( "600" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_7_INTERLEAVE_KEY ),
                    equalTo( "700" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_8_INTERLEAVE_KEY ),
                    equalTo( "800" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_9_INTERLEAVE_KEY ),
                    equalTo( "900" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_10_INTERLEAVE_KEY ),
                    equalTo( "1000" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_11_INTERLEAVE_KEY ),
                    equalTo( "2000" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_12_INTERLEAVE_KEY ),
                    equalTo( "3000" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_13_INTERLEAVE_KEY ),
                    equalTo( "4000" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_14_INTERLEAVE_KEY ),
                    equalTo( "5000" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_15_INTERLEAVE_KEY ),
                    equalTo( "6000" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_16_INTERLEAVE_KEY ),
                    equalTo( "7000" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_17_INTERLEAVE_KEY ),
                    equalTo( "8000" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_18_INTERLEAVE_KEY ),
                    equalTo( "9000" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_19_INTERLEAVE_KEY ),
                    equalTo( "10000" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_20_INTERLEAVE_KEY ),
                    equalTo( "100" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_21_INTERLEAVE_KEY ),
                    equalTo( "200" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_22_INTERLEAVE_KEY ),
                    equalTo( "300" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_23_INTERLEAVE_KEY ),
                    equalTo( "400" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_24_INTERLEAVE_KEY ),
                    equalTo( "500" ) );
        }
    }

    @Test
    public void shouldThrowExceptionWhenSomeFrequenciesNotProvidedAndSomeInterleavesNoProvided()
            throws WorkloadException, DriverConfigurationException, IOException
    {
        // Given
        Map<String,Long> operationMixMap = new HashMap<>();
//        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_1_INTERLEAVE_KEY ,1l);
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_2_INTERLEAVE_KEY, 2l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_3_INTERLEAVE_KEY, 3l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_4_INTERLEAVE_KEY, 4l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_5_INTERLEAVE_KEY, 5l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_6_INTERLEAVE_KEY, 6l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_7_INTERLEAVE_KEY, 7l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_8_INTERLEAVE_KEY, 8l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_9_INTERLEAVE_KEY, 9l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_10_INTERLEAVE_KEY, 10l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_11_INTERLEAVE_KEY, 11l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_12_INTERLEAVE_KEY, 12l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_13_INTERLEAVE_KEY, 13l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_14_INTERLEAVE_KEY, 14l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_15_INTERLEAVE_KEY, 15l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_16_INTERLEAVE_KEY, 16l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_17_INTERLEAVE_KEY, 17l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_18_INTERLEAVE_KEY, 18l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_19_INTERLEAVE_KEY, 19l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_20_INTERLEAVE_KEY, 20l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_21_INTERLEAVE_KEY, 21l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_22_INTERLEAVE_KEY, 22l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_23_INTERLEAVE_KEY, 23l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_24_INTERLEAVE_KEY, 24l );
//        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_1_FREQUENCY_KEY ,1l);
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_2_FREQUENCY_KEY, 2l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_3_FREQUENCY_KEY, 3l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_4_FREQUENCY_KEY, 4l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_5_FREQUENCY_KEY, 5l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_6_FREQUENCY_KEY, 6l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_7_FREQUENCY_KEY, 7l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_8_FREQUENCY_KEY, 8l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_9_FREQUENCY_KEY, 9l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_10_FREQUENCY_KEY, 10l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_11_FREQUENCY_KEY, 11l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_12_FREQUENCY_KEY, 12l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_13_FREQUENCY_KEY, 13l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_14_FREQUENCY_KEY, 14l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_15_FREQUENCY_KEY, 15l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_16_FREQUENCY_KEY, 16l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_17_FREQUENCY_KEY, 17l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_18_FREQUENCY_KEY, 18l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_19_FREQUENCY_KEY, 19l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_20_FREQUENCY_KEY, 20l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_21_FREQUENCY_KEY, 21l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_22_FREQUENCY_KEY, 22l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_23_FREQUENCY_KEY, 23l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_24_FREQUENCY_KEY, 24l );

        Map<String,String> defaultSnbBiParams = LdbcSnbBiWorkloadConfiguration.defaultConfigSF1();
        defaultSnbBiParams.remove( LdbcSnbBiWorkloadConfiguration.OPERATION_1_INTERLEAVE_KEY );
        defaultSnbBiParams.remove( LdbcSnbBiWorkloadConfiguration.OPERATION_1_FREQUENCY_KEY );
        defaultSnbBiParams.put(
                LdbcSnbBiWorkloadConfiguration.PARAMETERS_DIRECTORY,
                TestUtils.getResource( "/snb/bi/" ).getAbsolutePath()
        );

        DriverConfiguration configuration = ConsoleAndFileDriverConfiguration.fromDefaults(
                DummyLdbcSnbBiDb.class.getName(),
                LdbcSnbBiWorkload.class.getName(),
                1 )
                .applyArg( ConsoleAndFileDriverConfiguration.IGNORE_SCHEDULED_START_TIMES_ARG, "true" )
                .applyArgs( defaultSnbBiParams )
                .applyArgs(
                        MapUtils.UNSAFE_changeTypes(
                                operationMixMap,
                                TypeChangeFun.IDENTITY,
                                TypeChangeFun.TO_STRING
                        ) );

        // When
        boolean exceptionThrown = false;
        try ( Workload workload = new LdbcSnbBiWorkload() )
        {
            workload.init( configuration );
        }
        catch ( WorkloadException e )
        {
            System.out.println( e.getMessage() );
            exceptionThrown = true;
        }

        // Then
        // either interleaves or frequencies need to be provided
        assertTrue( exceptionThrown );
    }

    @Test
    public void shouldUseInterleavesWhenAllInterleavesProvided()
            throws WorkloadException, DriverConfigurationException, IOException
    {
        // Given
        Map<String,Long> operationMixMap = new HashMap<>();
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_1_INTERLEAVE_KEY, 10l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_2_INTERLEAVE_KEY, 20l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_3_INTERLEAVE_KEY, 30l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_4_INTERLEAVE_KEY, 40l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_5_INTERLEAVE_KEY, 50l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_6_INTERLEAVE_KEY, 60l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_7_INTERLEAVE_KEY, 70l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_8_INTERLEAVE_KEY, 80l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_9_INTERLEAVE_KEY, 90l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_10_INTERLEAVE_KEY, 100l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_11_INTERLEAVE_KEY, 110l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_12_INTERLEAVE_KEY, 120l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_13_INTERLEAVE_KEY, 130l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_14_INTERLEAVE_KEY, 140l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_15_INTERLEAVE_KEY, 150l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_16_INTERLEAVE_KEY, 160l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_17_INTERLEAVE_KEY, 170l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_18_INTERLEAVE_KEY, 180l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_19_INTERLEAVE_KEY, 190l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_20_INTERLEAVE_KEY, 200l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_21_INTERLEAVE_KEY, 210l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_22_INTERLEAVE_KEY, 220l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_23_INTERLEAVE_KEY, 230l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_24_INTERLEAVE_KEY, 240l );
//        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_1_FREQUENCY_KEY ,1l);
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_2_FREQUENCY_KEY, 2l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_3_FREQUENCY_KEY, 3l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_4_FREQUENCY_KEY, 4l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_5_FREQUENCY_KEY, 5l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_6_FREQUENCY_KEY, 6l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_7_FREQUENCY_KEY, 7l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_8_FREQUENCY_KEY, 8l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_9_FREQUENCY_KEY, 9l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_10_FREQUENCY_KEY, 10l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_11_FREQUENCY_KEY, 11l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_12_FREQUENCY_KEY, 12l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_13_FREQUENCY_KEY, 13l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_14_FREQUENCY_KEY, 14l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_15_FREQUENCY_KEY, 15l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_16_FREQUENCY_KEY, 16l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_17_FREQUENCY_KEY, 17l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_18_FREQUENCY_KEY, 18l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_19_FREQUENCY_KEY, 19l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_20_FREQUENCY_KEY, 20l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_21_FREQUENCY_KEY, 21l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_22_FREQUENCY_KEY, 22l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_23_FREQUENCY_KEY, 23l );
        operationMixMap.put( LdbcSnbBiWorkloadConfiguration.OPERATION_24_FREQUENCY_KEY, 24l );

        Map<String,String> defaultSnbBiParams = LdbcSnbBiWorkloadConfiguration.defaultConfigSF1();
        defaultSnbBiParams.remove( LdbcSnbBiWorkloadConfiguration.OPERATION_1_FREQUENCY_KEY );
        defaultSnbBiParams.put(
                LdbcSnbBiWorkloadConfiguration.PARAMETERS_DIRECTORY,
                TestUtils.getResource( "/snb/bi/" ).getAbsolutePath()
        );

        DriverConfiguration configuration = ConsoleAndFileDriverConfiguration.fromDefaults(
                DummyLdbcSnbBiDb.class.getName(),
                LdbcSnbBiWorkload.class.getName(),
                1 )
                .applyArg( ConsoleAndFileDriverConfiguration.IGNORE_SCHEDULED_START_TIMES_ARG, "true" )
                .applyArgs( defaultSnbBiParams )
                .applyArg(
                        LdbcSnbBiWorkloadConfiguration.PARAMETERS_DIRECTORY,
                        TestUtils.getResource( "/snb/bi/" ).getAbsolutePath() )
                .applyArgs(
                        MapUtils.UNSAFE_changeTypes(
                                operationMixMap,
                                TypeChangeFun.IDENTITY,
                                TypeChangeFun.TO_STRING
                        ) );

        // When
        try ( Workload workload = new LdbcSnbBiWorkload() )
        {
            workload.init( configuration );

            // Then
            Map<String,String> configurationAsMap = configuration.asMap();
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_1_INTERLEAVE_KEY ),
                    equalTo( "10" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_2_INTERLEAVE_KEY ),
                    equalTo( "20" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_3_INTERLEAVE_KEY ),
                    equalTo( "30" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_4_INTERLEAVE_KEY ),
                    equalTo( "40" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_5_INTERLEAVE_KEY ),
                    equalTo( "50" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_6_INTERLEAVE_KEY ),
                    equalTo( "60" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_7_INTERLEAVE_KEY ),
                    equalTo( "70" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_8_INTERLEAVE_KEY ),
                    equalTo( "80" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_9_INTERLEAVE_KEY ),
                    equalTo( "90" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_10_INTERLEAVE_KEY ),
                    equalTo( "100" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_11_INTERLEAVE_KEY ),
                    equalTo( "110" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_12_INTERLEAVE_KEY ),
                    equalTo( "120" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_13_INTERLEAVE_KEY ),
                    equalTo( "130" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_14_INTERLEAVE_KEY ),
                    equalTo( "140" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_15_INTERLEAVE_KEY ),
                    equalTo( "150" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_16_INTERLEAVE_KEY ),
                    equalTo( "160" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_17_INTERLEAVE_KEY ),
                    equalTo( "170" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_18_INTERLEAVE_KEY ),
                    equalTo( "180" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_19_INTERLEAVE_KEY ),
                    equalTo( "190" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_20_INTERLEAVE_KEY ),
                    equalTo( "200" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_21_INTERLEAVE_KEY ),
                    equalTo( "210" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_22_INTERLEAVE_KEY ),
                    equalTo( "220" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_23_INTERLEAVE_KEY ),
                    equalTo( "230" ) );
            assertThat(
                    configurationAsMap.get( LdbcSnbBiWorkloadConfiguration.OPERATION_24_INTERLEAVE_KEY ),
                    equalTo( "240" ) );
        }
    }

    // TODO move workload validation code to WorkloadTest and remove it from driver code
    @Ignore
    @Test
    public void shouldPassWorkloadValidation()
            throws ClientException, IOException, DriverConfigurationException, WorkloadException
    {
        // TODO get from somewhere
        Iterable<DriverConfiguration> configurations = null;

        for ( DriverConfiguration configuration : configurations )
        {
            configuration = configuration.applyArg(
                    ConsoleAndFileDriverConfiguration.OPERATION_COUNT_ARG,
                    Long.toString( 100_000 )
            );

            // TODO create using factory & configuration
            try ( Workload workload = null )
            {
                workload.init( configuration );

                // TODO validate workload
                assertTrue( false );
            }
        }
    }
}
