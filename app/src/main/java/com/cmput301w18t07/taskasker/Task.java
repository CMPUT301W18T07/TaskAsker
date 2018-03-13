package com.cmput301w18t07.taskasker;

import android.location.Location;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Thomas Mackay on 2018-03-12.
 */

public class Task {
    private String name;
    private int taskID;
    private User requester;
    private Location location;
    private ArrayList<ContactsContract.CommonDataKinds.Photo> photolist;
    private double minPrice;
    private Bid bid;
    private Date time;

    public Task(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getTaskID(){
        return taskID;
    }
    public ArrayList<ContactsContract.CommonDataKinds.Photo> getPhotolist() {
        return photolist;
    }
    public Bid getBid() {
        return bid;
    }
    public User getRequester() {
        return requester;
    }
    public Date getTime() {
        return time;
    }
    public double getMinPrice() {
        return minPrice;
    }
    public Location getLocation() {
        return location;
    }
}
