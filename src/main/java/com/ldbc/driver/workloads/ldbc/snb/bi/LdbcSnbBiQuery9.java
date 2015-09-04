package com.ldbc.driver.workloads.ldbc.snb.bi;

import com.ldbc.driver.Operation;
import com.ldbc.driver.SerializingMarshallingException;

import java.util.List;

public class LdbcSnbBiQuery9 extends Operation<List<LdbcSnbBiQuery9Result>>
{
    public static final int TYPE = 9;
    // TODO
    public static final int DEFAULT_LIMIT = 20;
    private final String tagClassA;
    private final String tagClassB;
    private final int limit;

    public LdbcSnbBiQuery9( String tagClassA, String tagClassB, int limit )
    {
        this.tagClassA = tagClassA;
        this.tagClassB = tagClassB;
        this.limit = limit;
    }

    public String tagClassA()
    {
        return tagClassA;
    }

    public String tagClassB()
    {
        return tagClassB;
    }

    public int limit()
    {
        return limit;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        { return true; }
        if ( o == null || getClass() != o.getClass() )
        { return false; }

        LdbcSnbBiQuery9 that = (LdbcSnbBiQuery9) o;

        if ( limit != that.limit )
        { return false; }
        if ( tagClassA != null ? !tagClassA.equals( that.tagClassA ) : that.tagClassA != null )
        { return false; }
        return !(tagClassB != null ? !tagClassB.equals( that.tagClassB ) : that.tagClassB != null);

    }

    @Override
    public int hashCode()
    {
        int result = tagClassA != null ? tagClassA.hashCode() : 0;
        result = 31 * result + (tagClassB != null ? tagClassB.hashCode() : 0);
        result = 31 * result + limit;
        return result;
    }

    @Override
    public String toString()
    {
        return "LdbcSnbBiQuery9{" +
               "tagClassA='" + tagClassA + '\'' +
               ", tagClassB='" + tagClassB + '\'' +
               ", limit=" + limit +
               '}';
    }

    @Override
    public List<LdbcSnbBiQuery9Result> marshalResult( String serializedResults ) throws SerializingMarshallingException
    {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public String serializeResult( Object resultsObject ) throws SerializingMarshallingException
    {
        // TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public int type()
    {
        return TYPE;
    }
}
