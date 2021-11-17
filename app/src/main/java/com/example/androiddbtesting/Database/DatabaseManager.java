package com.example.androiddbtesting.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.*;
import android.util.Log;

import java.util.ArrayList;


public class DatabaseManager {

    // GLOBAL VARIABLES
    SQLiteDatabase db = null;
    String db_name = "";
    String table_name = "";

    public void createDatabase(Context context){
        Log.i("Method Call", "createDatabase()");

        db = context.openOrCreateDatabase("LocationDB", context.MODE_PRIVATE, null);

        db.execSQL("Drop table if exists LocationTable;");
        db.execSQL("Create table if not exists LocationTable (RSSI INT(3), COMPASS_HEADING VARCHAR);");

        db.close();
    }

    public void logInfo(Context context, int RSSI, String COMPASS_HEADING) {
        Log.i("Method Call", "logInfo()");

        db = context.openOrCreateDatabase("LocationDB", context.MODE_PRIVATE, null);

        String query = String.format("Insert into LocationTable VALUES (%s, '%s');", RSSI, COMPASS_HEADING);
        db.execSQL(query);

        db.close();
    }

    public void readDatabase(Context context) {
        Log.i("Method Call", "logInfo()");
        db = context.openOrCreateDatabase("LocationDB", context.MODE_PRIVATE, null);

        // on below line we are creating a cursor with query to read data from database.
        Cursor c = db.rawQuery("Select * FROM LocationTable;", null);

        // on below line we are creating a new array list.
        ArrayList<Object> db_list = new ArrayList<>();

        Log.i("!!!!!!!!!!!!!!!!!!!!!!", "!!!!!!!!!!!!!!!!!!!!1");

        c.moveToFirst();
        while(!c.isAfterLast()) {
            db_list.add(c.getString(c.getColumnIndexOrThrow("COMPASS_HEADING"))); //add the item
            c.moveToNext();
        }

        // moving our cursor to first position.
        if (c.moveToFirst()) {
            do {
                Log.i("Cursor Info", c.toString());
                db_list.add(c.toString());
            } while (c.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        c.close();

        Log.i("!!!!!!!!!!!!!!!!!!!!!!", "!!!!!!!!!!!!!!!!!!!!1");

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

