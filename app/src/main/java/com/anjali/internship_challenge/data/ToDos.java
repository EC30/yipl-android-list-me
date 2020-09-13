package com.anjali.internship_challenge.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "toDos")
public class ToDos implements Serializable {
    @PrimaryKey(autoGenerate=true)
    private int id;


    String Task;
    String Date;

    public ToDos(String task, String date) {
        Task = task;
        Date = date;
    }

    public ToDos() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        Task = task;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
