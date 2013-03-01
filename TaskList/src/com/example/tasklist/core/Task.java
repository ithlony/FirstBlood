package com.example.tasklist.core;

import android.content.ContentValues;

public class Task {
	
	public int _id;
	public String title;
	public String detail;
	public int priority;
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
