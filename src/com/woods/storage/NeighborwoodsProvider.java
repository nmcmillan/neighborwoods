package com.woods.storage;

import java.util.Vector;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

public class NeighborwoodsProvider extends ContentProvider {

	public final String LOG_TAG = getClass().getCanonicalName();
	public static final Uri CONTENT_URI = Uri.parse("content://com.woods.storage.restaurantprovider");
	
	private static final String DATABASE_FILE_NAME = "neighborwoods.sqlite";
	private static final int DATABASE_VERSION = 1;
	
	public static final String RESTAURANTS_TABLE = "restaurants";
	
	private static Vector<String> listOfTables = null;
	
	static {
		listOfTables = new Vector<String>();
		listOfTables.add(RESTAURANTS_TABLE);
	}
	
	private SqlHelper sqlHelper;

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Called from application main thread, so must be quick.
	 */
	@Override
	public boolean onCreate() {
		SqlHelper sql = new SqlHelper(getContext(),"",null,0);
		return false;
	}
	
	private class SqlHelper extends SQLiteOpenHelper {

		public SqlHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onOpen(SQLiteDatabase db){
			super.onOpen(db);
			if(!db.isReadOnly()){
				db.execSQL("PRAGMA foreign_keys=ON;");
			}
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			createRestaurantsTable(db);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		}
	
		private void createRestaurantsTable(SQLiteDatabase db) {
			Log.d(LOG_TAG,"Creating restaurants table.");
			db.execSQL("CREATE TABLE " + RESTAURANTS_TABLE + " (" +
					RestaurantsColumns._ID + "  INTEGER PRIMARY KEY," +
					RestaurantsColumns.NAME + " TEXT," + 
					RestaurantsColumns.LOCATION + " BLOB," + 
					RestaurantsColumns.PHONE + " INTEGER," +
					RestaurantsColumns.ADDRESS_STREET + " TEXT," +
					RestaurantsColumns.ADDRESS_NUMBER + " INTEGER," +
					RestaurantsColumns.RATING + " REAL," + 
					RestaurantsColumns.TYPE + " TEXT"  + 
					RestaurantsColumns.LOCAL_RECOMMENDATIONS + ");");				
		}
		
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static final class RestaurantsColumns implements BaseColumns{
		public static final String NAME = "name";
		public static final String ADDRESS_STREET = "address_street";
		public static final String ADDRESS_NUMBER = "address_number";
		public static final String LOCATION = "location";
		public static final String TYPE = "type";
		public static final String PHONE = "phone";
		public static final String RATING = "rating";
		public static final String LOCAL_RECOMMENDATIONS = "local_recs";
	}
	
}
