package com.liquid.kochanparser;

import java.util.List;
import java.util.ArrayList;

import com.liquid.kochanparser.Lesson;

/**
 * A class for storing a single day in the timetable
 *
 * @author Krzysztof Gutkowski (LiquidPL)
 * @version dev
 */
public class Day 
{
	private String name;
	private List <Lesson> lessons = new ArrayList <Lesson> ();
	
	public Day() 
	{
		this.name = "";
	}

	public Day (String name)
	{
		this.name = name;
	}

	public void addLesson () // adds an empty lesson
	{
		lessons.add (new Lesson ());
	}
	
	public void addLesson (String name, String code, String room, String _class)
	{
	    lessons.add (new Lesson (name, code, room, _class));
	}
	
	public Lesson getLesson (int number)
	{
		return lessons.get (number);
	}
	
	public int getSize ()
	{
		return lessons.size ();
	}

	public String getName() 
	{
		return name;
	}

	public void setName (String name) 
	{
		this.name = name;
	}
	
	public Lesson getCurrentLesson ()
	{
		return lessons.get (lessons.size() - 1);
	}
	
	public Lesson getPreviousLesson ()
	{
		return lessons.get (lessons.size () - 2);
	}
}
