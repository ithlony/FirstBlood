package com.example.tasklist.core;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class TaskStorage {
	
	private DBHelper db_helper;
	
	public TaskStorage(Context context)
	{
		db_helper = new DBHelper(context);
	}
	
	public void add_task(Task task)
	{
		db_helper.add(task.toContentValue());
	}
	
	public void add_tasks(List<Task> tasks)
	{
		for (Task task : tasks) 
		{
			db_helper.add(task.toContentValue());
		}
		
	}
	
	public void remove_task(Task task)
	{
		db_helper.remove("_id = ?", new String[]{String.valueOf(task._id)});
	}
	
	public void remove_tasks(List<Task> tasks)
	{
		for (Task task : tasks) 
		{
			db_helper.remove("_id = ?", new String[]{String.valueOf(task._id)});
		}
	}
	
	private Task cursor_to_task(Cursor cursor)
	{
		Task task = new Task();
		task._id = cursor.getInt(cursor.getColumnIndex("_id"));
		task.title = cursor.getString(cursor.getColumnIndex("title"));
		task.detail = cursor.getString(cursor.getColumnIndex("detail"));
		task.priority = cursor.getInt(cursor.getColumnIndex("priority"));
		task.frequency = cursor.getInt(cursor.getColumnIndex("frequency"));
		
		return task;
	}
	
	public List<Task> get_tasks()
	{
		Cursor cursor = db_helper.get_table_cursor();
		if (cursor == null)
		{
			return null;
		} else if (!cursor.moveToFirst()){
			cursor.close();
			return null;
		} else {
			ArrayList<Task> tasks = new ArrayList<Task>();
			while (cursor.moveToNext())
			{
				Task task = cursor_to_task(cursor);
				tasks.add(task);
			}
			cursor.close();
			return tasks;
		}
	}
}
