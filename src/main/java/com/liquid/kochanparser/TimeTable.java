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

public class TimeTable 
{
	private List <Day> days = new ArrayList <Day> ();
	private TimeTableType type;
	
	public void parse (File xml)
	{		
		try
		{
			SAXParserFactory factory = SAXParserFactory.newInstance ();
			SAXParser parser = factory.newSAXParser ();
			
			DefaultHandler handler = new SaxHandler (this);
			parser.parse(xml, handler);
		}
		catch (Exception e)
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
		return days.size();
	}
	
	public void addDay() 
	{
		this.days.add (new Day ());
	}
	
	public void addDay (String name)
	{
		this.days.add (new Day (name));
	}

    public TimeTableType getType()
    {
        return type;
    }

    public void setType(TimeTableType type)
    {
        this.type = type;
    }
}