package com.woods;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.woods.provider.NeighborwoodsProvider;

public class NeighborwoodsActivity extends Activity {
    /** Called when the activity is first created. */
	private static String LOG_TAG = "com.woods.NeighborwoodsActivity";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        String[] restProjection = {
        		NeighborwoodsProvider.RestaurantsColumns._ID,
        		NeighborwoodsProvider.RestaurantsColumns.NAME
        };
        
        String[] userProjection = {
        		NeighborwoodsProvider.UsersColumns._ID,
        		NeighborwoodsProvider.UsersColumns.FIRST_NAME
        };
        
        String[] nwProjection = {
        		NeighborwoodsProvider.RestaurantsColumns._ID,
        		NeighborwoodsProvider.RestaurantsColumns.NAME
        };
        
        ContentResolver cr = getContentResolver();
        
        Cursor c = cr.query(NeighborwoodsProvider.RestaurantsColumns.CONTENT_URI, restProjection, null, null, null);
        Log.d(LOG_TAG, "Count of cursor : " + c.getCount());
        c = cr.query(NeighborwoodsProvider.UsersColumns.CONTENT_URI, userProjection, null, null, null);
        Log.d(LOG_TAG, "Count of cursor : " + c.getCount());
    }
}