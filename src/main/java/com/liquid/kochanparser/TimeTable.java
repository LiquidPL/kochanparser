package com.liquid.kochanparser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.liquid.kochanparser.SaxHandler;
import com.liquid.kochanparser.TimeTableType;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
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

    private List <Lesson> lessons = new ArrayList <Lesson> ();

    private List<Time> starthours = new ArrayList<Time> ();
    private List<Time> endhours = new ArrayList<Time> ();

    private TimeTableType type;

    private SAXParserFactory factory;

    public TimeTable ()
    {
        factory = SAXParserFactory.newInstance ();
    }

    public void parse (File xml) throws ParserConfigurationException, SAXException
    {
        try
        {
            SAXParser parser = factory.newSAXParser ();
            setType (xml.getName ());

            DefaultHandler handler = new SaxHandler (this);
            parser.parse (xml, handler);
        }
        catch (IOException e)
        {
            e.printStackTrace ();
        }
    }

    public void parse (InputStream istr) throws ParserConfigurationException, SAXException
    {
        try
        {
            SAXParser parser = factory.newSAXParser ();
            DefaultHandler handler = new SaxHandler (this);
            parser.parse (istr, handler);
        }
        catch (IOException e)
        {
            e.printStackTrace ();
        }


    }

    public void setType (String name)
    {
        switch (name.toString ().charAt (0))
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
    }

    public void setType (TimeTableType type)
    {
        this.type = type;
    }

    public List<Lesson> getLessons ()
    {
        return lessons;
    }

    public Lesson getCurrentLesson ()
    {
        return lessons.get (lessons.size() - 1);
    }

    public TimeTableType getType ()
    {
        return type;
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