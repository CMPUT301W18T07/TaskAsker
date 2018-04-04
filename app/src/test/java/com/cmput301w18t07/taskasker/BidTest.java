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
 * Purpose:
 * Test a Bid object.
 *
 * Design Rationale:
 * Needed to test the Bid class
 *
 * @author Chris
 * @version 1.5
 */

public class BidTest {
    private User firstUser;
    private User secondUser;

    public BidTest(){
        try{
            firstUser = new User("Test", "test@fake.com", "000-000-0000", "Test", "Faker");
            secondUser = new User("Next", "next@fake.com", "000-000-0001", "Next", "Faker");
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void createTest(){
        Bid bid = new Bid(secondUser, 100.0);
        assertNotNull(bid);
    }

    @Test
    public void getUserTest(){
        Bid bid = new Bid(secondUser, 100.);
        assertEquals(secondUser, bid.getBidder());

        bid = new Bid(firstUser, 100.0);
        assertEquals(firstUser, bid.getBidder());
    }

    @Test
    public void getBidTest(){
        Bid bid = new Bid(secondUser, 100.0);
        assertEquals(100.0, bid.getBid());

        bid = new Bid(secondUser, 55.227);
        assertEquals(55.227, bid.getBid());
    }

    @Test
    public void setBidTest(){
        Bid bid = new Bid(secondUser, 100.0);
        assertNotNull(bid);
        assertEquals(100.0, bid.getBid());
        bid.setBid(200.9);
        assertEquals(200.9, bid.getBid());
    }
}
