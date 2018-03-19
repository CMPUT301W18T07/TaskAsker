/*
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by critt on 2018-03-19.
 */

public class BidTest {
    User firstUser;
    User secondUser;
    Task firstTask;

    public BidTest(){
        try{
            firstUser = new User("Test", "test@fake.com", "000-000-0000", "Test", "Faker");
            secondUser = new User("Next", "next@fake.com", "000-000-0001", "Next", "Faker");
            firstTask = new Task("Primary", "Basic task", firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void createTest(){
        Bid bid = new Bid(secondUser, 100.0,firstTask);
        assertNotNull(bid);
    }

}
