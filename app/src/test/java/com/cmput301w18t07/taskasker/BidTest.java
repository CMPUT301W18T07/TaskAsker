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
    Task secondTask;

    public BidTest(){
        try{
            firstUser = new User("Test", "test@fake.com", "000-000-0000", "Test", "Faker");
            secondUser = new User("Next", "next@fake.com", "000-000-0001", "Next", "Faker");
            firstTask = new Task("Primary", "Basic task", firstUser);
            secondTask = new Task("Secondary", "Another basic task", secondUser);
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

    @Test
    public void getUserTest(){
        Bid bid = new Bid(secondUser, 100.0, firstTask);
        assertEquals(secondUser, bid.getBidder());

        bid = new Bid(firstUser, 100.0, firstTask);
        assertEquals(firstUser, bid.getBidder());
    }

    @Test
    public void getBidTest(){
        Bid bid = new Bid(secondUser, 100.0, firstTask);
        assertEquals(100.0, bid.getBid());

        bid = new Bid(secondUser, 55.227, firstTask);
        assertEquals(55.227, bid.getBid());
    }

    @Test
    public void getTaskTest(){
        Bid bid = new Bid(secondUser, 100.0, firstTask);
        assertEquals(firstTask, bid.getTask());

        bid = new Bid(secondUser, 100.0, secondTask);
        assertEquals(secondTask, bid.getTask());
    }

}
