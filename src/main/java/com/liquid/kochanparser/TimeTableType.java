package com.liquid.kochanparser;

/**
 * Class holding ints for all the possible timetable types (class/classroom/teacher)
 * @author Krzysztof Gutkowski (LiquidPL)
 * @version dev
 */
/*public enum TimeTableType
{
	CLASS ("class"),
	TEACHER ("teacher"),
	CLASSROOM ("classroom"),
    NONE ("none");

    private String value;

    TimeTableType (String value)
    {
        this.value = value;
    }

    public String getValue ()
    {
        return value;
    }
}*/

public class TimeTableType
{
    public static final int NONE = 0;
    public static final int CLASS = 1;
    public static final int TEACHER = 2;
    public static final int CLASSROOM = 3;

    private TimeTableType () {}
}