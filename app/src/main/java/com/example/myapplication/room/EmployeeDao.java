package com.example.myapplication.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert
    public void addEmployee(Employee employee);
    @Query("SELECT * FROM employees")
    List<Employee> getAll();
}
