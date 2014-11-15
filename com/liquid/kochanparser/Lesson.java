package com.liquid.kochanparser;

public class Lesson 
{
	private String name = "";
	private String teacherCode = "";
	private String classroom = "";
	private int index = -1;
	private int group = 0;
	
	public Lesson () 
	{
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
}
