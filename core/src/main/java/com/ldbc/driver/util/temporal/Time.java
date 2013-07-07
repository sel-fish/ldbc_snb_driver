package com.ldbc.driver.util.temporal;

public class Time implements Comparable<Time>, MultipleTimeUnitProvider
{
    public static Time now()
    {
        return new Time( TimeUnitConvertor.nanoFromMilli( System.currentTimeMillis() ) );
    }

    public static Time fromNano( long nanoTime )
    {
        return new Time( nanoTime );
    }

    public static Time fromMicro( long microTime )
    {
        return new Time( TimeUnitConvertor.nanoFromMicro( microTime ) );
    }

    public static Time fromMilli( long milliTime )
    {
        return new Time( TimeUnitConvertor.nanoFromMilli( milliTime ) );
    }

    public static Time fromSeconds( long secondsTime )
    {
        return new Time( TimeUnitConvertor.nanoFromSecond( secondsTime ) );
    }

    public static Time from( TimeUnit timeUnit, long unitOfTime )
    {
        switch ( timeUnit )
        {
        case NANO:
            return Time.fromNano( unitOfTime );
        case MICRO:
            return Time.fromMicro( unitOfTime );
        case MILLI:
            return Time.fromMilli( unitOfTime );
        case SECOND:
            return Time.fromSeconds( unitOfTime );
        }
        throw new RuntimeException( "Unexpected error - unsupported TimeUnit" );
    }

    private final Long timeNano;

    private Time( long timeNano )
    {
        this.timeNano = timeNano;
    }

    public Time plus( Duration duration )
    {
        return Time.fromNano( timeNano + duration.asNano() );
    }

    public Time minus( Duration duration )
    {
        return Time.fromNano( timeNano - duration.asNano() );
    }

    @Override
    public String toString()
    {
        return timeNano + "(ns)";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( timeNano ^ ( timeNano >>> 32 ) );
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Time other = (Time) obj;
        if ( false == timeNano.equals( other.timeNano ) ) return false;
        return true;
    }

    @Override
    public int compareTo( Time o )
    {
        return timeNano.compareTo( o.timeNano );
    }

    @Override
    public long asNano()
    {
        return timeNano;
    }

    @Override
    public long asMicro()
    {
        return TimeUnitConvertor.nanoToMicro( timeNano );
    }

    @Override
    public long asMilli()
    {
        return TimeUnitConvertor.nanoToMilli( timeNano );
    }

    @Override
    public long asSeconds()
    {
        return TimeUnitConvertor.nanoToSecond( timeNano );
    }

    @Override
    public long as( TimeUnit timeUnit )
    {
        switch ( timeUnit )
        {
        case NANO:
            return this.asNano();
        case MICRO:
            return this.asMicro();
        case MILLI:
            return this.asMilli();
        case SECOND:
            return this.asSeconds();
        }
        throw new RuntimeException( "Unexpected error - unsupported TimeUnit" );
    }
}