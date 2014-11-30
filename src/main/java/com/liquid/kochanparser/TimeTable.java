package com.liquid.kochanparser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.liquid.kochanparser.SaxHandler;
import com.liquid.kochanparser.Day;
import com.liquid.kochanparser.TimeTableType;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

/**
 * A singleton for the entire timetable parsed
 *
 * @author Krzysztof Gutkowski (LiquidPL)
 * @version dev
 */
public class TimeTable
{
    private String longName = "";
    private String shortName = "";

    private List<Day> days = new ArrayList<Day> ();

    private List<Time> starthours = new ArrayList<Time> ();
    private List<Time> endhours = new ArrayList<Time> ();

    private TimeTableType type;

    public void parse (File xml)
    {
        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance ();
            SAXParser parser = factory.newSAXParser ();

            switch (xml.toString ().charAt (0))
            {
                case 'o':
                    type = TimeTableType.TIMETABLE_TYPE_CLASS;
                    break;
                case 's':
                    type = TimeTableType.TIMETABLE_TYPE_CLASSROOM;
                    break;
                case 'n':
                    type = TimeTableType.TIMETABLE_TYPE_TEACHER;
                    break;
            }

            DefaultHandler handler = new SaxHandler (this);
            parser.parse (xml, handler);
        } catch (Exception e)
        {
            e.printStackTrace ();
        }
    }

    public Day getDay (int number)
    {
        return days.get (number);
    }

    public int getDayCount ()
    {
        return days.size ();
    }

    public void addDay ()
    {
        this.days.add (new Day ());
    }

    public void addDay (String name)
    {
        this.days.add (new Day (name));
    }

    public TimeTableType getType ()
    {
        return type;
    }

    public void setType (TimeTableType type)
    {
        this.type = type;
    }

    public List<Time> getStarthours ()
    {
        return starthours;
    }

    public List<Time> getEndhours ()
    {
        return endhours;
    }

    public String getLongName ()
    {
        return longName;
    }

    public void setLongName (String longName)
    {
        this.longName = longName;
    }

    public String getShortName ()
    {
        return shortName;
    }

    public void setShortName (String shortName)
    {
        this.shortName = shortName;
    }
}