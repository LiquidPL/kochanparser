package com.liquid.kochanparser;

import java.util.ArrayList;
import java.util.List;

import com.liquid.kochanparser.SaxHandler;
import com.liquid.kochanparser.Day;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;

public class TimeTable 
{
	private List <Day> days = new ArrayList <Day> ();
		
	public void parse (String xml)
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
	
	/*public void print ()
	{
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				System.out.println ("Nazwa: " + tab[i][j].getName());
				System.out.println ("Nauczyciel: " + tab[i][j].getTeacherCode());
				System.out.println ("Sala: " + tab[i][j].getClassroom());
			}
		}
	}*/
}