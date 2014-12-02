package com.liquid.kochanparser;

/**
 * Enum type holding all the possible timetable types (class/classroom/teacher)
 * @author Krzysztof Gutkowski (LiquidPL)
 * @version dev
 */
public enum TimeTableType
{
	TIMETABLE_TYPE_CLASS ("class"),
	TIMETABLE_TYPE_TEACHER ("teacher"),
	TIMETABLE_TYPE_CLASSROOM ("classroom"),
    TIMETABLE_TYPE_NONE ("none");

    private String value;

    TimeTableType (String value)
    {
        this.value = value;
    }

    public String getValue ()
    {
        return value;
    }
}
