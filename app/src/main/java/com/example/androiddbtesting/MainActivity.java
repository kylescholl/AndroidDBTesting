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
        databaseManager.logInfo(getApplicationContext(), "North", -50);
        databaseManager.logInfo(getApplicationContext(), "East", -30);
        databaseManager.logInfo(getApplicationContext(), "South", 20);
        databaseManager.logInfo(getApplicationContext(), "West", 45);

        // Read from database
        databaseManager.readDatabase(getApplicationContext());
    }
}