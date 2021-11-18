package com.example.androiddbtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androiddbtesting.Database.DatabaseManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the DatabaseManager class
        DatabaseManager databaseManager = new DatabaseManager();

        // Create the database if not already
        databaseManager.createDatabase(getApplicationContext());

        // Log location info to database
        databaseManager.logInfo(getApplicationContext(), "Backpack", "North", -50, 200);
        databaseManager.logInfo(getApplicationContext(), "Fire Extinguisher", "East", -30, 245);
        databaseManager.logInfo(getApplicationContext(), "Helmet", "South", 20, 1500);
        databaseManager.logInfo(getApplicationContext(), "Person", "West", 45, 910);

        // Read from database
        databaseManager.readDatabase(getApplicationContext());
    }
}