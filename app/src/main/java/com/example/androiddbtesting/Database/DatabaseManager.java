package com.example.androiddbtesting.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.*;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


public class DatabaseManager {

    // GLOBAL VARIABLES
    SQLiteDatabase db = null;
    String db_name = "";
    String table_name = "";

    public void createDatabase(Context context){
        Log.i("Method Call", "createDatabase()");

        db = context.openOrCreateDatabase("LocationDB", context.MODE_PRIVATE, null);

        db.execSQL("DROP TABLE IF EXISTS LocationTable;");
        db.execSQL("CREATE TABLE IF NOT EXISTS LocationTable (COMPASS_HEADING VARCHAR, RSSI INT(3));");

        db.close();
    }

    public void logInfo(Context context, String COMPASS_HEADING, int RSSI) {
        Log.i("Method Call", "logInfo()");

        db = context.openOrCreateDatabase("LocationDB", context.MODE_PRIVATE, null);

        String query = String.format("INSERT INTO LocationTable VALUES ('%s', %s);", COMPASS_HEADING, RSSI);
        db.execSQL(query);

        db.close();
    }

    public void readDatabase(Context context) {
        Log.i("Method Call", "logInfo()");

        db = context.openOrCreateDatabase("LocationDB", context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("Select * FROM LocationTable;", null);

        ArrayList<String> heading_list = new ArrayList<>();
        ArrayList<String> rssi_list = new ArrayList<>();

        int count_ = 0;

        c.moveToFirst();
        while(!c.isAfterLast()) {
            String heading = c.getString(c.getColumnIndexOrThrow("COMPASS_HEADING"));
            String rssi = c.getString(c.getColumnIndexOrThrow("RSSI"));

            Log.i("heading", heading);
            Log.i("rssi", rssi);

            heading_list.add(heading);
            rssi_list.add(rssi);

            //            db_list.add(c.getString(c.getColumnIndexOrThrow("COMPASS_HEADING"))); //add the item
            c.moveToNext();
//            Log.i("count_", Integer.toString(count_));
            count_++;
        }

        if (c.moveToFirst()) {
            do {
                Log.i("Cursor Info", c.toString());
//                db_list.add(c.toString());
            } while (c.moveToNext());
        }
        c.close();

//        Log.i("!!!!!!!", db_list.size());
//
//        ContentValues cv = new ContentValues();
//        cv.put(FeedEntry.COLUMN_NAME.);

        Cursor c2 = db.rawQuery("Select * FROM LocationTable;", null);
        c2.moveToFirst();
        Log.d("COMPASS_HEADING", c2.getString(c2.getColumnIndexOrThrow("COMPASS_HEADING")));

        db.close();
    }
}

