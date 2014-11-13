package com.liquid.kochanparser;

public class Lesson 
{
	private String name;
	private String teacherCode;
	private String classroom;
	
	public Lesson () 
	{
		this.name = "";
		this.teacherCode = "";
		this.classroom = "";
	}
	
	public Lesson (String name, String code, String room)
	{
		this.name = name;
		this.teacherCode = code;
		this.classroom = room;
	}

}
