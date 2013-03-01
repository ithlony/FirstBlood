package com.example.tasklist.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DB_Name = "task_list_database.db";
	private static final int DB_Version = 1;
	public static final String[] DB_Columns = new String[]{"_id", "title", "detail", "priority", "frequency"};
	private SQLiteDatabase db;
	
	public DBHelper(Context context)
	{
		super(context, DB_Name, null, DB_Version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		this.db = db;
		db.execSQL("create table if not exists " +
				"tasks(_id integer primary key autoincrement, title varchar, detail TEXT, priorirty integer, frequency integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("ALTER TABLE tasks ADD COLUMN other STRING");  
	}

	public Cursor get_table_cursor()
	{
		Cursor cursor = db.query(DB_Name, DB_Columns, null, null, null, null, null);
				
		return cursor;
	}
	
	public void remove(String where, String[] where_args)
	{
		db.beginTransaction();
		try {
			db.delete(DB_Name, where, where_args);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}
	
	public void add(ContentValues value)
	{
		db.beginTransaction();
		try {
			db.insert(DB_Name, null, value);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}
}
