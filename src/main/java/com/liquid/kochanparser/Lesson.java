package com.liquid.kochanparser;

/**
 * A class for storing a single lesson.
 */
public class Lesson 
{
	private String name = "";
	private String teacherCode = "";
	private String classroom = "";
	private String _class = "";
	
	private int index = -1;
	private int group = 0;
	
	public Lesson () {}
	
	public Lesson (String name, String code, String room, String _class) 
	{
	    this.name = name;
	    this.teacherCode = code;
	    this.classroom = room;
	    this._class = _class;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getTeacherCode() 
	{
		return teacherCode;
	}

	public void setTeacherCode(String teacherCode) 
	{
		this.teacherCode = teacherCode;
	}

	public String getClassroom() 
	{
		return classroom;
	}

	public void setClassroom(String classroom) 
	{
		this.classroom = classroom;
	}
	
	public int getGroup ()
	{
		return this.group;
	}
	
	public void setGroup (int val)
	{
		this.group = val;
	}

	public int getIndex () 
	{
		return index;
	}

	public void setIndex (int index) 
	{
		this.index = index;
	}

	public String _getClass() 
	{
		return _class;
	}

	public void setClass(String _class) 
	{
		this._class = _class;
	}
}
