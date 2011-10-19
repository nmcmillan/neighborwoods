package com.woods.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;

public class NeighborwoodsProvider extends ContentProvider {

	public final String LOG_TAG = getClass().getCanonicalName();
	public static final Uri CONTENT_URI = Uri.parse("content://com.woods.provider.neighborwoodsprovider");
	
	public static final String AUTHORITY = NeighborwoodsProvider.class.getPackage().getName() + ".provider";
	
	private static final String DATABASE_FILE_NAME = "neighborwoods.sqlite";
	private static final int DATABASE_VERSION = 1;
	
	public static final String RESTAURANTS_TABLE = "restaurants";
	public static final String USERS_TABLE = "users";
	public static final String NWLOCATION_TABLE = "nwlocations";
	
	private static Vector<String> listOfTables = null;
	
	static {
		listOfTables = new Vector<String>();
		listOfTables.add(RESTAURANTS_TABLE);
		listOfTables.add(NWLOCATION_TABLE);
		listOfTables.add(USERS_TABLE);
	}
	
	private SqlHelper sqlHelper;

	
	//can't be an enum because switch statements require constants
	private static final int URI_RESTAURANTS = 0;
	private static final int URI_RESTAURANTS_ID = 1;
	private static final int URI_USERS = 2;
	private static final int URI_USERS_ID = 3;
	private static final int URI_NWLOCATION = 4;
	private static final int URI_NWLOCATION_ID = 5;
	
	
	// Set up the URI matcher
	private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		sUriMatcher.addURI(AUTHORITY, "restaurants", URI_RESTAURANTS);
		sUriMatcher.addURI(AUTHORITY, "restaurants/#", URI_RESTAURANTS_ID);
		sUriMatcher.addURI(AUTHORITY, "users", URI_USERS);
		sUriMatcher.addURI(AUTHORITY, "users/#", URI_USERS_ID);
		sUriMatcher.addURI(AUTHORITY, "nwlocations", URI_NWLOCATION);
		sUriMatcher.addURI(AUTHORITY, "nwlocations/#", URI_NWLOCATION_ID);
	}
	
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
		sqlHelper = new SqlHelper(getContext(),DATABASE_FILE_NAME,null,0);
		return true;
	}
	
	private class SqlHelper extends SQLiteOpenHelper {

		public SqlHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, null, DATABASE_VERSION);
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
			Log.d(LOG_TAG, "onCreate sqlHelper");
			createRestaurantsTable(db);
			createUsersTable(db);
			createNWLocationsTable(db);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		}
		
		private void createNWLocationsTable(SQLiteDatabase db){
			Log.d(LOG_TAG,"Creating NWLocations table.");
			//String createTable = ("CREATE TABLE" + NWLOCATION_TABLE + " (" + 
				//)
		}
		
		private void createUsersTable(SQLiteDatabase db){
			Log.d(LOG_TAG, "Creating users table.");
			String createTable = ("CREATE TABLE " + USERS_TABLE + " (" + 
					UsersColumns._ID + " INTEGER PRIMARY KEY," +
					UsersColumns.FIRST_NAME + " TEXT," + 
					UsersColumns.LAST_NAME + " TEXT," + 
					UsersColumns.EMAIL + " BLOB," + 
					UsersColumns.NW_LOCATIONS + " BLOB," + 
					UsersColumns.GPS_LOCATIONS + " BLOB" +
					");");
			Log.d(LOG_TAG, "Sql statement : " + createTable);
			db.execSQL(createTable);
		}
	
		private void createRestaurantsTable(SQLiteDatabase db) {
			Log.d(LOG_TAG,"Creating restaurants table.");
			String createTable = ("CREATE TABLE " + RESTAURANTS_TABLE + " (" +
					RestaurantsColumns._ID + "  INTEGER PRIMARY KEY," +
					RestaurantsColumns.NAME + " TEXT," + 
					RestaurantsColumns.LOCATION + " BLOB," + 
					RestaurantsColumns.PHONE + " INTEGER," +
					RestaurantsColumns.ADDRESS_STREET + " TEXT," +
					RestaurantsColumns.ADDRESS_NUMBER + " INTEGER," +
					RestaurantsColumns.RATING + " REAL," + 
					RestaurantsColumns.TYPE + " TEXT,"  + 
					RestaurantsColumns.LOCAL_RECOMMENDATIONS + " INTEGER"+ ");");
			Log.d(LOG_TAG,"Sql statement : " + createTable);
			db.execSQL(createTable);
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		String order;
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		SQLiteDatabase db;
		Cursor c;
		
		switch(sUriMatcher.match(uri)){
			case URI_RESTAURANTS:
				Log.d(LOG_TAG, "query for restaurants");
				qb.setTables(RESTAURANTS_TABLE);
				if (TextUtils.isEmpty(sortOrder)) {
		            order = RestaurantsColumns.DEFAULT_SORT_ORDER;
		        } else {
		            order = sortOrder;
		        }
		        
		        // Get the database and run the query 
				// don't do this until we know we have a valid URI
				db = sqlHelper.getReadableDatabase();
		        c = qb.query(db, projection, selection, selectionArgs, null, null, order);
	
		        // Tell the cursor what uri to watch, so it knows when its source data changes
		        c.setNotificationUri(getContext().getContentResolver(), uri);
		        return c;
			case URI_USERS:
				Log.d(LOG_TAG,"query for users");
				qb.setTables(USERS_TABLE);
				
				if (TextUtils.isEmpty(sortOrder)) {
		            order = UsersColumns.DEFAULT_SORT_ORDER;
		        } else {
		            order = sortOrder;
		        }
		        
		        // Get the database and run the query 
				// don't do this until we know we have a valid URI
				db = sqlHelper.getReadableDatabase();
		        c = qb.query(db, projection, selection, selectionArgs, null, null, order);
	
		        // Tell the cursor what uri to watch, so it knows when its source data changes
		        c.setNotificationUri(getContext().getContentResolver(), uri);
		        return c;
			case URI_NWLOCATION:
				Log.d(LOG_TAG,"query for nw locations");
				qb.setTables(USERS_TABLE);
				
				if (TextUtils.isEmpty(sortOrder)) {
		            order = NWLocationsColumns.DEFAULT_SORT_ORDER;
		        } else {
		            order = sortOrder;
		        }
		        
		        // Get the database and run the query 
				// don't do this until we know we have a valid URI
				db = sqlHelper.getReadableDatabase();
		        c = qb.query(db, projection, selection, selectionArgs, null, null, order);
	
		        // Tell the cursor what uri to watch, so it knows when its source data changes
		        c.setNotificationUri(getContext().getContentResolver(), uri);
		        return c;
		    default:
		    	throw new IllegalArgumentException("Unknown URI : " + uri.toString());
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static final class RestaurantsColumns implements BaseColumns {
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/restaurants");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.neighborwoods.restaurant";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.neighborwoods.restaurant";
		public static final String NAME = "restaurant_name";
		public static final String ADDRESS_STREET = "address_street";
		public static final String ADDRESS_NUMBER = "address_number";
		public static final String LOCATION = "location";
		public static final String TYPE = "type";
		public static final String PHONE = "phone";
		public static final String RATING = "rating";
		public static final String LOCAL_RECOMMENDATIONS = "local_recs";
		public static final String DEFAULT_SORT_ORDER = NAME + " DESC";
	}
	
	public static final class UsersColumns implements BaseColumns {
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/users");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.neighborwoods.user";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.neighborwoods.user";
		public static final String FIRST_NAME = "first_name";
		public static final String LAST_NAME = "last_name";
		public static final String EMAIL = "email";
		public static final String NW_LOCATIONS = "nw_locations";
		public static final String GPS_LOCATIONS = "gps_locations";
		public static final String DEFAULT_SORT_ORDER = FIRST_NAME + " DESC";
	}
	
	public static final class NWLocationsColumns implements BaseColumns {
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/nwlocations");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.neighborwoods.neighborwoodslocation";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.neighborwoods.neighborwoodslocation";
		public static final String DEFAULT_SORT_ORDER = " DESC";
	}
	
}
