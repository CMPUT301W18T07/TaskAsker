package com.cmput301w18t07.taskasker;

import java.util.regex.Pattern;

/**
 * Created by Thomas Mackay on 2018-03-12.
 */

public class User {
    private String username = "";
    private String email = "";
    private String phoneNumber = "";
    private String firstName = "";
    private String lastName = "";
    private final Pattern usernamePattern = Pattern.compile("\\S{1,}");
    private final Pattern emailPattern = Pattern.compile("\\S+@\\S+[.]\\S{2,}");
    private final Pattern phoneNumberPattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");

    //Basic constructor, mostly for use in the testing of other classes
    public User(String username){
        this.username = username;
    }

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

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
