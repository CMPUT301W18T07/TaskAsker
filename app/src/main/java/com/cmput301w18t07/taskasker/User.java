


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
    private String username = "";
    private String email = "";
    private String phoneNumber = "";
    private String firstName = "";
    private String lastName = "";
    private String emailPatternString = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
    private final Pattern usernamePattern = Pattern.compile("[A-Za-z0-9]+([_A-Za-z0-9-]){0,19}");
    private final Pattern emailPattern = Pattern.compile(emailPatternString);
    private final Pattern phoneNumberPattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");

    /**
     * Purpose:
     * Basic constructor, mostly for use in the testing of other classes
     *
     * @param username
     */
    public User(String username){
        this.username = username;
    }


    /**
     * Purpose:
     * Constructor for a User
     *
     * @param username
     * @param email
     * @param phoneNumber
     * @param firstName
     * @param lastName
     * @throws Exception
     */
    public User(String username, String email, String phoneNumber, String firstName, String lastName) throws Exception {

        if(!usernamePattern.matcher(username).matches()){
            throw new Exception("Username is of invalid format");
        } else{
            this.username = username;
        }
        if(!emailPattern.matcher(email).matches()){
            throw new Exception("Email is of invalid format");
        } else{
            this.email = email;
        }
        if(!phoneNumberPattern.matcher(phoneNumber).matches()){
            throw new Exception("Phone number is of invalid format");
        }
        else{
            this.phoneNumber = phoneNumber;
        }

        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Purpose:
     * Gets the username of the user
     *
     * @return string of the users username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Purpose:
     * Gets the email of the user
     *
     * @return string of users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Purpose:
     * Gets the phone number of the user
     *
     * @return string of users phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Purpose:
     * Gets the last name of the user
     *
     * @return string of users last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Purpose:
     * Gets the first name of the user
     *
     * @return string of users frost name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Purpose:
     * Sets the email of the user
     *
     * @param email string of the users email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Purpose:
     * Sets the phone number of the user
     *
     * @param phoneNumber string of the users phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Purpose:
     * Sets the first name of the user
     *
     * @param firstName string of the user first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Purpose:
     * Sets the last name of the user
     *
     * @param lastName string of the user last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
