package com.example.myapplication.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
}
