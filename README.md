# kochanparser

A parser for my [schools timetables](http://kochanowski.iq.pl/plan20140915/index.html). You are probably not getting anything useful from it, except for a shitty SAX parser implementation.

## How to use

```java
File file = new File ("o15.html");
String xml = file.toString ();

TimeTable table = new TimeTable ();
table.parse (xml);

// Printing

for (int i = 0; i < 5; i++)
{
	Day day = table.getDay (i);
	System.out.println (day.getName ());
	System.out.println ("-------------");
	for (int j = 0; j < day.getSize (); j++)
	{
		Lesson lesson = day.getLesson (j);
		if (lesson.getGroup () >= 1) System.out.println ("Group: " + lesson.getGroup ());
		System.out.println ("Subject: " + lesson.getName ());
		System.out.println ("Teacher: " + lesson.getTeacherCode ());
		System.out.println ("Classroom: " + lesson.getClassroom () + "\n");
	}
}
```
