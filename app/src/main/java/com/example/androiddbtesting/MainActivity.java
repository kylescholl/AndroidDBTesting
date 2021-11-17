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
        databaseManager.logInfo(getApplicationContext(), -50, "North");

        databaseManager.logInfo(getApplicationContext(), -50, "North");
        databaseManager.logInfo(getApplicationContext(), -30, "East");
        databaseManager.logInfo(getApplicationContext(), 20, "South");

        // Read from database
        databaseManager.readDatabase(getApplicationContext());
    }
}