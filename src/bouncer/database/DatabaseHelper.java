package bouncer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	static int DATABASE_VERSION = 1;
	static String DATABASE_NAME = "bouncerGame.db";
	
	static String TABLE_NAME = "Results";
	static String RESULTS_COLUMN_NAME = "Name";
	static String RESULTS_COLUMN_POINTS = "Points";
	static String RESULTS_COLUMN_ID = "_id";
	
	String TAG = DatabaseHelper.class.getCanonicalName();

	public DatabaseHelper(Context context) {
	
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.d(TAG, "DatabaseHelper contructor");
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.d(TAG,"onCreate");
	database.execSQL("CREATE TABLE " + TABLE_NAME  +
	                 "( " + RESULTS_COLUMN_ID  + " integer primary key, " + RESULTS_COLUMN_NAME + " text, " +
	                       RESULTS_COLUMN_POINTS + " text)");
	
	}

	//on upgrade will be invoked if i change version of db in consturctor
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVer, int newVer) {
		Log.d(TAG,"onUpgrade");
		
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
		onCreate(database);
		
	}
	
	public void saveRecord(String points, String name){
		Log.d(TAG,"saveRecord");
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(RESULTS_COLUMN_NAME, name);
		contentValues.put(RESULTS_COLUMN_POINTS, points);
	
		
		database.insert(TABLE_NAME , null, contentValues );
		
	}
	
	public Cursor getAllRecors(){
		SQLiteDatabase database = this.getReadableDatabase();
		return database.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + RESULTS_COLUMN_POINTS + " DESC" , null);
	}

}
