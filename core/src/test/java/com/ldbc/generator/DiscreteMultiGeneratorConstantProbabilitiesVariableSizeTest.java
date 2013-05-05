package com.ldbc.generator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.ldbc.generator.DiscreteMultiGenerator;
import com.ldbc.generator.Generator;
import com.ldbc.generator.GeneratorException;
import com.ldbc.util.Histogram;
import com.ldbc.util.Pair;
import com.ldbc.util.Bucket.DiscreteBucket;

public class DiscreteMultiGeneratorConstantProbabilitiesVariableSizeTest extends GeneratorTest<Set<String>, Integer>
{

    @Override
    public Histogram<Set<String>, Integer> getExpectedDistribution()
    {
        Set<String> s = new HashSet<String>();
        Set<String> s1 = new HashSet<String>( Arrays.asList( new String[] { "1" } ) );
        Set<String> s2 = new HashSet<String>( Arrays.asList( new String[] { "2" } ) );
        Set<String> s3 = new HashSet<String>( Arrays.asList( new String[] { "3" } ) );
        Set<String> s12 = new HashSet<String>( Arrays.asList( new String[] { "1", "2" } ) );
        Set<String> s13 = new HashSet<String>( Arrays.asList( new String[] { "1", "3" } ) );
        Set<String> s23 = new HashSet<String>( Arrays.asList( new String[] { "2", "3" } ) );
        Set<String> s123 = new HashSet<String>( Arrays.asList( new String[] { "1", "2", "3" } ) );
        Histogram<Set<String>, Integer> expectedDistribution = new Histogram<Set<String>, Integer>( 0 );
        expectedDistribution.addBucket( new DiscreteBucket<Set<String>>( s ), 3 );
        expectedDistribution.addBucket( new DiscreteBucket<Set<String>>( s1 ), 1 );
        expectedDistribution.addBucket( new DiscreteBucket<Set<String>>( s2 ), 1 );
        expectedDistribution.addBucket( new DiscreteBucket<Set<String>>( s3 ), 1 );
        expectedDistribution.addBucket( new DiscreteBucket<Set<String>>( s12 ), 1 );
        expectedDistribution.addBucket( new DiscreteBucket<Set<String>>( s13 ), 1 );
        expectedDistribution.addBucket( new DiscreteBucket<Set<String>>( s23 ), 1 );
        expectedDistribution.addBucket( new DiscreteBucket<Set<String>>( s123 ), 3 );
        return expectedDistribution;
    }

    @Override
    public double getDistributionTolerance()
    {
        return 0.01;
    }

    @Override
    public Generator<Set<String>> getGeneratorImpl()
    {
        Pair<Double, String> p1 = new Pair<Double, String>( 1.0, "1" );
        Pair<Double, String> p2 = new Pair<Double, String>( 1.0, "2" );
        Pair<Double, String> p3 = new Pair<Double, String>( 1.0, "3" );
        ArrayList<Pair<Double, String>> items = new ArrayList<Pair<Double, String>>();
        items.add( p1 );
        items.add( p2 );
        items.add( p3 );
        Generator<Integer> amountToRetrieveGenerator = getGeneratorBuilder().uniformNumberGenerator( 0, 3 ).build();
        DiscreteMultiGenerator<String> generator = getGeneratorBuilder().discreteMultiGenerator( items,
                amountToRetrieveGenerator ).build();
        return generator;
    }

    @Test( expected = GeneratorException.class )
    public void emptyConstructorTest()
    {
        // Given
        Generator<Integer> amountToRetrieveGenerator = getGeneratorBuilder().uniformNumberGenerator( 0, 3 ).build();
        ArrayList<Pair<Double, String>> emptyItems = new ArrayList<Pair<Double, String>>();
        Generator<Set<String>> generator = getGeneratorBuilder().discreteMultiGenerator( emptyItems,
                amountToRetrieveGenerator ).build();

        // When
        generator.next();

        // Then
        assertEquals( "Empty DiscreteGenerator should throw exception on next()", false, true );
    }
}