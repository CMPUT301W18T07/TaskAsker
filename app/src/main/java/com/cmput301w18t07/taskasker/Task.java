/* Task
 *
 * March 2018
 *
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.location.Location;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Thomas Mackay on 2018-03-12.
 */

/**
 * Purpose:
 * Represents a Task object.
 *
 * Design Rationale:
 * Needed in app
 *
 * @author
 * @version 1.5
 * @see TaskListAdapter
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


    /**
     * Purpose:
     * Basic constructor, mostly for use in the testing of other classes
     *
     * @param name
     */
    public Task(String name){
        this.name = name;
    }

    /**
     * Purpose:
     * Constructor for a task
     *
     * @param name
     * @param description
     * @param req
     * @throws Exception
     */
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

    /**
     * Purpose:
     * Sets the taker of the task
     *
     * @param user User object of the task taker
     */
    public void setTaker(User user){
        this.taker = user;
        this.takerUsername = user.getUsername();
    }

    public void setTaskID(int taskID){
        this.taskID = taskID;
    }

    /**
     * Purpose:
     * Sets the requester of the task
     *
     * @param user User object of the task requester
     */
    public void setRequester(User user){
        this.requester = user;
        this.requesterUsername = user.getUsername();
    }

    /**
     * Purpose:
     * Gets the title of the task
     *
     * @return string of the task title
     */
    public String getName() {
        return name;
    }

    /**
     * Purpose:
     * Gets the description of the task
     *
     * @return string of the task description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Purpose:
     * Gets the task ID of the task
     *
     * @return int of the task ID
     */
    public int getTaskID(){
        return taskID;
    }

    /**
     * Purpose:
     * Gets the photos of a task
     *
     * @return array list of the task photos
     */
    public ArrayList<ContactsContract.CommonDataKinds.Photo> getPhotolist() {
        return photoList;
    }

    /**
     * Purpose:
     * Gets the bid on the task
     *
     * @return Bid object of the task bid
     */
    public Bid getBid() {
        return bid;
    }

    /**
     * Purpose:
     * Gets the requester of the task
     *
     * @return User object of the task requester
     */
    public User getRequester() {
        return requester;
    }

    /**
     * Purpose:
     * Gets the taker of the task
     *
     * @return User object of the task taker/provider
     */
    public User getTaker(){
        return taker;
    }

    /**
     * Purpose:
     * Gets the time of the task
     *
     * @return Date object of the task time
     */
    public Date getTime() {
        return time;
    }

    /**
     * Purpose:
     * Gets the minimum price of the task
     *
     * @return double of the task minimum price
     */
    public double getMinPrice() {
        return minPrice;
    }

    /**
     * Purpose:
     * Gets the location of the task
     *
     * @return Location object of the task location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Purpose:
     * Gets the status of the task
     *
     * @return string of the task status
     */
    public String getStatus() { return status; }

    /**
     * Purpose:
     * Sets the requester username of the task
     *
     * @param requesterUsername String of the task requester username
     */
    public void setRequesterUsername(String requesterUsername) {
        this.requesterUsername = requesterUsername;
    }

    /**
     * Purpose:
     * Sets the taker username of the task
     *
     * @param takerUsername String object of the task taker username
     */
    public void setTakerUsername(String takerUsername) {
        this.takerUsername = takerUsername;
    }
}
