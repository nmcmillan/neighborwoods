package com.woods.model;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Standard interface for all model objects to implement.
 * 
 * @author nick
 */

public interface Data {

	/**
	 * Convert cursor row to appropriate data object
	 * 
	 * @param c cursor object pointing to the appropriate row
	 * @return newly constructed object containing information from the cursor
	 */
	public Data fromCursor(Cursor c);
	
	/**
	 * Convert Data object to appropriate format for persistent storage - locally or remotely
	 *  
	 * @return content values object containing all data necessary to be reconstructed
	 */
	public ContentValues toContentValues();
}
