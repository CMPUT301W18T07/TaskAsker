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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.provider.ContactsContract;
import android.util.Base64;

import com.bumptech.glide.load.resource.bitmap.BitmapDecoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Purpose:
 * Represents a Task object.
 *
 * Design Rationale:
 * Needed in app
 *
 * @author Chris, Thomas
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
    private double lowestBid;
    private Bid bid;
    private Date time;
    private String status;
    private ArrayList<Bitmap> imageFolder;
    private ArrayList<String> base64Folder;





    /**
     * Purpose:
     * Basic constructor, mostly for use in the testing of other classes
     *
     * @param name String of the task's name
     */
    public Task(String name){
        this.name = name;
    }

    /**
     * Purpose:
     * Constructor for a task
     *
     * @param name String of the task's name
     * @param description String of the task's description
     * @param req User object of the task requester
     * @throws Exception Thrown when a name or descrition is longer than the maximum allowed
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
        this.status = "Requested";
        this.time = new Date();;
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
        this.status = "Assigned";
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

    public void setStatus(String status){
        this.status = status;
    }
    /**
     * Purpose:
     * Sets list of Images that have been converted to Base64
     *
     * @return
     */


    public void setImage(ArrayList<Bitmap> imageList){

        base64Folder.clear();
        ArrayList<String> tempArray = new ArrayList<>();
        for(int i= 0; i < imageList.size();i++){
            Bitmap bitmap = imageList.get(i);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteStream);
            byte[] b = byteStream.toByteArray();
            String base64Image = Base64.encodeToString(b, Base64.DEFAULT);
            tempArray.add(base64Image);
        }

        this.base64Folder = tempArray;}

    /**
     * Purpose:
     * Sets the title of the task
     *
     * @return String of the task's title
     */

    public String getName() {
        return name;
    }

    /**
     * Purpose:
     * Gets the description of the task
     *
     * @return String of the task's description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Purpose:
     * Gets the task ID of the task
     *
     * @return int of the task's ID number
     */
    public int getTaskID(){
        return taskID;
    }

    /**
     * Purpose:
     * Gets the photos of a task
     *
     * @return array list of the task's photos
     */
    public ArrayList<ContactsContract.CommonDataKinds.Photo> getPhotolist() {
        return photoList;
    }

    /**
     * Purpose:
     * Gets the bid on the task
     *
     * @return Bid object of the task's bid
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
     * @return Date object of the task's time
     */
    public Date getTime() {
        return time;
    }

    /**
     * Purpose:
     * Gets the minimum price of the task
     *
     * @return double of the task's minimum price
     */
    public double getLowestBid() {
        return lowestBid;
    }

    /**
     * Purpose:
     * Gets the location of the task
     *
     * @return Location object of the task's location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Purpose:
     * Gets the status of the task
     *
     * @return String of the task's status
     */
    public String getStatus() { return status; }

    /**
     * Purpose:
     * Sets the requester username of the task
     *
     * @param requesterUsername String of the task requester's username
     */
    public void setRequesterUsername(String requesterUsername) {
        this.requesterUsername = requesterUsername;
    }

    /**
     * Purpose:
     * Sets the taker username of the task
     *
     * @param takerUsername String object of the task taker's username
     */
    public void setTakerUsername(String takerUsername) {
        this.takerUsername = takerUsername;
    }

    public ArrayList<Bitmap> getImageFolder(){
        imageFolder.clear();
        for(int i = 0; i < base64Folder.size();i++) {
            byte[] decodedString = Base64.decode(base64Folder.get(i), Base64.DEFAULT);
            Bitmap decodedbyte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageFolder.add(decodedbyte);
        }
        return this.imageFolder;}
    /**
     * Gets a list of Images converted from Base64
     *
     * @return imageFolder
     */


}
