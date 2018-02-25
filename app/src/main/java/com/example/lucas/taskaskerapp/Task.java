package com.example.lucas.taskaskerapp;

import java.util.Date;

/**
 * Created by lucas on 2/25/2018.
 */

public class Task {
    private String name;
    private String description;
    private Date createdDate;
    // @TODO the rest of these later

    public Task(String name){
        this.name = name;
        createdDate = new Date();
    }
    public String getName(){
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }

    // @TODO the rest of these later
}
