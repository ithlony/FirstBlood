package com.example.tasklist.core;

import android.content.ContentValues;

public class Task {
	
	public int _id;
	public String title;
	public String detail;
	public int priority;
	public enum Freq{
		important(0), routine(1), longterm(2);
		
		private int value;
		
		private Freq(int value)
		{
			this.value = value;
		}
		
		public static Freq valueOf(int value)
		{
			switch (value)
			{
			case 0:
				return important;
			case 1:
				return routine;
			case 2:
				return longterm;
			}
			return null;
		}
		
		public int value()
		{
			return value;
		}
	};
	public int frequency;
	
	public Task()
	{
		
	}
	
	public Task(int _id, String title, String detail, int type, int frequency)
	{
		this._id = _id;
		this.title = title;
		this.detail = detail;
		this.priority = type;
		this.frequency = frequency;
	}
	
	public ContentValues toContentValue()
	{
		ContentValues value = new ContentValues();
		
		value.put("title", title);
		value.put("detail", detail);
		value.put("priority", priority);
		value.put("frequency", frequency);
		
		return value;
	}
}
