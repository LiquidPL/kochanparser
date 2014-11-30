package com.liquid.kochanparser;

import java.io.File;

/**
 * Created by liquid on 26.11.14.
 */
public class test
{
    public static void main (String[] args)
    {
        File file = new File ("o15.html");

        TimeTable table = new TimeTable ();
        table.parse (file);

        // Printing

        System.out.println ("Plan lekcji klasy " + table.getLongName () + " (" + table.getShortName () + ")");

        for (int i = 0; i < table.getLessons ().size (); i++)
        {
            Lesson lesson = table.getLessons ().get (i);
            System.out.println ("Day: " + lesson.getDay ());
            System.out.println ("Lesson: " + lesson.getLesson ());
            if (lesson.getGroup () >= 1) System.out.println ("Group: " + lesson.getGroup ());
            System.out.println ("Subject: " + lesson.getSubject ());
            System.out.println ("Teacher: " + lesson.getTeacherCode ());
            System.out.println ("Classroom: " + lesson.getClassroom ());
            System.out.println ("Class: " + lesson._getClass () + "\n");
        }

        /*for (int i = 0; i < table.getStarthours ().size (); i++)
        {
            System.out.println (table.getStarthours ().get (i).toString () + " - " + table.getEndhours ().get (i).toString ());
        }*/
    }
}
