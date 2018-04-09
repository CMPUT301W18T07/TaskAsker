/* User
 *
 * March 2018
 *
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import java.util.regex.Pattern;

import javax.mail.internet.InternetAddress;

/**
 * Purpose:
 * Represents a User object.
 *
 * Design Rationale:
 * Needed to represent a User of the app
 *
 * @author Chris, Thomas
 * @version 1.5
 */
public class User {
    private String username;
    private InternetAddress email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private double rating;
    private int numRatings;
    private final Pattern usernamePattern = Pattern.compile("[A-Za-z0-9][A-Za-z0-9\\-\\_]{0,19}");
    private final Pattern namePattern = Pattern.compile("[A-Za-z][A-Za-z-']*");

    /**
     * Purpose:
     * Basic constructor, mostly for use in the testing of other classes
     *
     * @param username String of the user's username
     */
    public User(String username){
        this.username = username;
    }


    /**
     * Purpose:
     * Constructor for a User
     *
     * @param username String of the user's username
     * @param email String of the user's email
     * @param phoneNumber String of the user's phone number
     * @param firstName String of the user's first name
     * @param lastName String of the user's last name
     * @throws Exception Thrown when a username, email, or phone number does not match it's required format
     */
    public User(String username, String email, String phoneNumber, String firstName, String lastName) throws Exception {

        if(!usernamePattern.matcher(username).matches()){
            throw new Exception("Username is of invalid format");
        } else{
            this.username = username;
        }

        this.email = new InternetAddress(email, true);

        phoneNumber = phoneNumber.replaceAll("[^0-9]","");
        if(phoneNumber.length() != 10){
            throw new Exception("Phone number is of invalid format");
        } else{
            this.phoneNumber = phoneNumber.substring(0,3)+"-"+phoneNumber.substring(3,6)+"-"+phoneNumber.substring(6);
        }

        if(!namePattern.matcher(firstName).matches()){
            throw new Exception("Unrecognized first name");
        } else{
          this.firstName = firstName;
        }

        if(!namePattern.matcher(lastName).matches()){
            throw new Exception("Unrecognized last name");
        } else{
            this.lastName = lastName;
        }
        this.rating = 3.0;
        this.numRatings = 1;
    }

    /**
     * Purpose:
     * Gets the username of the user
     *
     * @return String of the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Purpose:
     * Gets the email of the user
     *
     * @return String of user's email
     */
    public String getEmail() {
        return email.getAddress();
    }

    /**
     * Purpose:
     * Gets the phone number of the user
     *
     * @return String of user's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Purpose:
     * Gets the last name of the user
     *
     * @return String of user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Purpose:
     * Gets the first name of the user
     *
     * @return String of user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Purpose:
     * Gets the current rating of the user
     *
     * @return Double of user's rating
     */
    public double getRating(){
        return this.rating;
    }

    /**
     * Purpose:
     * Sets the email of the user
     *
     * @param email String of the user's new email
     */
    public void setEmail(String email) throws Exception{
        this.email = new InternetAddress(email, true);
    }

    /**
     * Purpose:
     * Sets the phone number of the user
     *
     * @param phoneNumber String of the user's new phone number
     */
    public void setPhoneNumber(String phoneNumber) throws Exception {
        phoneNumber = phoneNumber.replaceAll("[^0-9]","");
        if(phoneNumber.length() != 10){
            throw new Exception("Phone number is of invalid format");
        } else{
            this.phoneNumber = phoneNumber.substring(0,3)+"-"+phoneNumber.substring(3,6)+"-"+phoneNumber.substring(6);
        }
    }

    /**
     * Purpose:
     * Sets the first name of the user
     *
     * @param firstName String of the user's new first name
     */
    public void setFirstName(String firstName) throws Exception{
        if(!namePattern.matcher(firstName).matches()){
            throw new Exception("Unrecognized first name");
        } else{
            this.firstName = firstName;
        }
    }

    /**
     * Purpose:
     * Sets the last name of the user
     *
     * @param lastName String of the user's new last name
     */
    public void setLastName(String lastName) throws Exception {
        if(!namePattern.matcher(lastName).matches()){
            throw new Exception("Unrecognized last name");
        } else{
            this.lastName = lastName;
        }
    }

    /**
     * Purpose:
     * Updates the rating of the user
     *
     * @param rating Double of the user's newest rating
     */
    public void addRating(double rating) throws Exception{
        if(rating < 0.0 || rating > 5.0){
            throw new Exception("Invalid rating");
        }
        double temp = this.rating * this.numRatings;
        temp += rating;
        this.numRatings += 1;
        this.rating = temp / this.numRatings;
    }

}
