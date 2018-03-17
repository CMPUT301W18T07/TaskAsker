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
    private String description;
    private int taskID;
    private User requester;
    private User taker; // TEMPORARY - WILL BE REPLACED WITH BID
    /* These are necessary because of the way elasticsearch handles searching,
       I can explain more in person if necessary. */
    private String requesterUsername;
    private String takerUsername;
    private Location location;
    private ArrayList<ContactsContract.CommonDataKinds.Photo> photoList;
    private double minPrice;
    private Bid bid;
    private Date time;
    private String status;

    //Basic constructor, mostly for use in the testing of other classes
    public Task(String name){
        this.name = name;
    }

    public Task(String name, String description, User req) throws Exception {
        if(name.length() > 30){
            throw new Exception("Task name too long");
        } else {
            this.name = name;
        }
        if(description.length() > 300){
            throw new Exception("Task description too long");
        } else{
            this.description = description;
        }
        this.requesterUsername = req.getUsername();
        this.requester = req;
        this.time = new Date();
        this.status = "Requested";
    }

    public void setTaker(User user){
        this.taker = user;
        this.takerUsername = user.getUsername();
    }

    public void setRequester(User user){
        this.requester = user;
        this.requesterUsername = user.getUsername();
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getTaskID(){
        return taskID;
    }

    public ArrayList<ContactsContract.CommonDataKinds.Photo> getPhotolist() {
        return photoList;
    }

    public Bid getBid() {
        return bid;
    }

    public User getRequester() {
        return requester;
    }

    public User getTaker(){
        return taker;
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

    public String getStatus() { return status; }

    public void setRequesterUsername(String requesterUsername) {
        this.requesterUsername = requesterUsername;
    }

    public void setTakerUsername(String takerUsername) {
        this.takerUsername = takerUsername;
    }
}
