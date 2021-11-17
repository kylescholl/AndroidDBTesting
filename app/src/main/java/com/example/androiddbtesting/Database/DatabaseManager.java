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
        db.execSQL("CREATE TABLE IF NOT EXISTS LocationTable (ITEM_NAME VARCHAR, COMPASS_HEADING VARCHAR, RSSI INT(3));");

        db.close();
    }

    public void logInfo(Context context, String ITEM_NAME, String COMPASS_HEADING, int RSSI) {
        Log.i("Method Call", "logInfo()");

        db = context.openOrCreateDatabase("LocationDB", context.MODE_PRIVATE, null);

        String query = String.format("INSERT INTO LocationTable VALUES ('%s', '%s', %s);", ITEM_NAME, COMPASS_HEADING, RSSI);
        db.execSQL(query);

        db.close();
    }

    public void readDatabase(Context context) {
        Log.i("Method Call", "logInfo()");

        db = context.openOrCreateDatabase("LocationDB", context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("Select * FROM LocationTable;", null);

        ArrayList<String> name_list = new ArrayList<>();
        ArrayList<String> heading_list = new ArrayList<>();
        ArrayList<String> rssi_list = new ArrayList<>();

        c.moveToFirst();
        while(!c.isAfterLast()) {
            String name = c.getString(c.getColumnIndexOrThrow("ITEM_NAME"));
            String heading = c.getString(c.getColumnIndexOrThrow("COMPASS_HEADING"));
            String rssi = c.getString(c.getColumnIndexOrThrow("RSSI"));

            /*
            Log.i("name", name);
            Log.i("heading", heading);
            Log.i("rssi", rssi);
             */

            name_list.add(name);
            heading_list.add(heading);
            rssi_list.add(rssi);

            c.moveToNext();
        }
        c.close();
        db.close();

        String out = "";
        for (int i = 0; i < heading_list.size(); i++) {

            String n = name_list.get(i);
            String h = heading_list.get(i);
            String r = rssi_list.get(i);

            out = String.format("['%s', '%s', %s]", n, h, r);
            Log.i("row", out);
        }
    }
}

