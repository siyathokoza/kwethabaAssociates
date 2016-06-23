package com.siyathokoza.virtual_classroom;

import com.siyathokoza.virtual_classroom.TableData.TableInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseOperations extends SQLiteOpenHelper {
public static final int database_version = 1;
public String CREATE_QUERY = "CREATE_TABLE" + TableInfo.TABLE_NAME+"("+TableInfo.USER_NAME+"TEXT,"+TableInfo.USER_PASS+"TEXT );";
	
public DataBaseOperations(Context context) {
		super(context, TableInfo.DATABASE_NAME, null,database_version);
		Log.d("Database Operatins", "Database created");
		
	}
	public void onCreate(SQLiteDatabase sdb) {
            sdb.execSQL(CREATE_QUERY);

	}
    
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
   public void popTable(DataBaseOperations dop,String name,String pass){
	   
	   SQLiteDatabase SQ = dop.getWritableDatabase();
	   ContentValues cv = new ContentValues();
	   cv.put(TableInfo.USER_NAME, name);
	   cv.put(TableInfo.USER_PASS, pass);
	long K =   SQ.insert(TableInfo.TABLE_NAME, null, cv);
	Log.d("Database Operatins", "One raw inserted");
   }
}
