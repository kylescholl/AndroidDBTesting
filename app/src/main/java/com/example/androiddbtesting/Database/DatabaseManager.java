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

        c.moveToFirst();
        while(!c.isAfterLast()) {
            String heading = c.getString(c.getColumnIndexOrThrow("COMPASS_HEADING"));
            String rssi = c.getString(c.getColumnIndexOrThrow("RSSI"));

            Log.i("heading", heading);
            Log.i("rssi", rssi);

            heading_list.add(heading);
            rssi_list.add(rssi);

            c.moveToNext();
        }
        c.close();
        db.close();

        Log.i("","");
        for (String head_:heading_list) {
            Log.i("head_", head_);
        }

        Log.i("","");
        for (String rssi_:rssi_list) {
            Log.i("rssi_", rssi_);
        }
    }
}

