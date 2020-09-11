package com.anjali.internship_challenge.data;

import java.io.Serializable;

public class ToDos implements Serializable {
    String Task;
    String Date;

    public ToDos(String task, String date) {
        Task = task;
        Date = date;
    }

    public ToDos() {

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
