package com.example.tasklist.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DB_Name = "task_list_database.db";
	private static final String Table_Name = "tasks";
	private static final int DB_Version = 1;
	public static final String[] DB_Columns = new String[]{"_id", "title", "detail", "priority", "frequency"};
	private SQLiteDatabase db;
	
	public DBHelper(Context context)
	{
		super(context, DB_Name, null, DB_Version);
		db = getWritableDatabase();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table if not exists " +
				this.Table_Name + "(_id integer primary key autoincrement, title varchar, detail TEXT, priority integer, frequency integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("ALTER TABLE tasks ADD COLUMN other STRING");  
	}

	public Cursor get_table_cursor()
	{
		Cursor cursor = db.query(this.Table_Name, null, null, null, null, null, null);
				
		return cursor;
	}
	
	public void remove(String where, String[] where_args)
	{
		db.beginTransaction();
		try {
			db.delete(Table_Name, where, where_args);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}
	
	public void add(ContentValues value)
	{
		db.beginTransaction();
		try {
			//db.execSQL("insert into tasks(title, detail, priority) values('11', '22', 11)");
			db.insert(this.Table_Name, null, value);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}
}
