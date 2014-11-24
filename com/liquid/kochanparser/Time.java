package com.liquid.kochanparser;

public class Time
{
    private int hour = 0;
    private int minute = 0;
    
    public Time ()
    {
        
    }
    
    public Time (String a)
    {
        String result[] = a.split (":");
        setHour (toInt (result[0]));
        setMinute (toInt (result[1]));
    }
    
    private int toInt (String number)
    {
        int result = 0;
        for (int i = 0; i < number.length (); i++)
        {
            result = result * 10 + (int) (number.charAt (i) - '0');
        }
        return result;
    }

    public int getHour ()
    {
        return hour;
    }

    public void setHour (int hour)
    {
        this.hour = hour;
    }

    public int getMinute ()
    {
        return minute;
    }

    public void setMinute (int minute)
    {
        this.minute = minute;
    }
}
