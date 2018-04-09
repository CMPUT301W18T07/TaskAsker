/*
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import org.junit.Test;

import android.location.Location;

import java.util.ArrayList;

import static junit.framework.Assert.*;

/**
 * Purpose:
 * Test a Task object.
 *
 * Design Rationale:
 * Needed to test the Task class
 *
 * @author Chris
 * @version 1.5
 */

public class TaskTest {
    private User firstUser;
    private User secondUser;
    private Bid firstBid;
    private Bid secondBid;
    private String basicName = "a";
    private String basicDescription = "a";

    public TaskTest(){
        try{
            firstUser = new User("req", "req@fake.com", "000-000-0000", "Requester", "Fake");
            secondUser = new User("take","take@fake.com","000-000-0000","Taker","Fake");
            firstBid = new Bid(firstUser, 12.12);
            secondBid = new Bid(secondUser, 11.11);
        } catch(Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void createTest(){
        Task task = new Task("");
        assertNotNull(task);
        boolean thrown = false;
        try{
            Task newTask = new Task(basicName,basicDescription,firstUser);
            assertNotNull(newTask);
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            Task newTask = new Task("", basicDescription, firstUser);
            assertNotNull(newTask);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            Task newTask = new Task("aaaaaaaaaa", basicDescription, firstUser);
            assertNotNull(newTask);
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            Task newTask = new Task("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", basicDescription, firstUser);
            assertNotNull(newTask);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            Task newTask = new Task(basicName, "", firstUser);
            assertNotNull(newTask);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            Task newTask = new Task(basicName, "aaaaaaaaaa", firstUser);
            assertNotNull(newTask);
        }catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        String tooLong = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        thrown = false;
        try{
            Task newTask = new Task(basicName, tooLong, firstUser);
            assertNotNull(newTask);
        }catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void changeTakerTest(){
        Task task = new Task("");
        try{
            task = new Task(basicName,basicDescription,firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        assertNull(task.getTaker());
        task.setTaker(secondUser);
        assertEquals(secondUser,task.getTaker());
    }

    @Test
    public void changeRequesterTest(){
        Task task = new Task("");
        try{
            task = new Task(basicName,basicDescription,firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        assertEquals(firstUser,task.getRequester());
        task.setRequester(secondUser);
        assertEquals(secondUser,task.getRequester());
        assertNull(task.getTaker());
    }

    @Test
    public void changeTaskIDTest(){
        Task task = new Task("");
        try{
            task = new Task(basicName,basicDescription,firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        assertEquals(0, task.getTaskID());
        task.setTaskID(1);
        assertEquals(1, task.getTaskID());
    }

    @Test
    public void changeStatusTest(){
        Task task = new Task("");
        try{
            task = new Task(basicName,basicDescription,firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        assertEquals("Requested", task.getStatus());
        task.setStatus("Completed");
        assertEquals("Completed", task.getStatus());
        task.setStatus("LOL");
        assertEquals("LOL", task.getStatus());
        task.setStatus("");
        assertEquals("", task.getStatus());
    }

    @Test
    public void bidListTesting(){
        Task task = new Task("");
        try{
            task = new Task(basicName,basicDescription,firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        ArrayList<Bid> list = new ArrayList<>();
        assertEquals(list, task.getBidList());
        task.addBid(firstBid);
        list.add(firstBid);
        assertEquals(list, task.getBidList());
        task.addBid(secondBid);
        list.add(secondBid);
        assertEquals(list, task.getBidList());
        task.removeBid(firstBid);
        list.remove(firstBid);
        assertEquals(list, task.getBidList());
        task.removeBid(secondBid);
        list.remove(secondBid);
        assertEquals(list, task.getBidList());
    }

    @Test
    public void getNameTest(){
        Task task = new Task("");
        try{
            task = new Task(basicName, basicDescription, firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        assertEquals(basicName, task.getName());

        try{
            task = new Task("Primary", basicDescription, firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        assertEquals("Primary", task.getName());
    }

    @Test
    public void getDescriptionTest(){
        Task task = new Task("");
        try{
            task = new Task(basicName,basicDescription,firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        assertEquals(basicDescription,task.getDescription());

        try{
            task = new Task(basicName,"First Task",firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        assertEquals("First Task",task.getDescription());
    }

    @Test
    public void changeLocationTest(){
        Task task = new Task("");
        try{
            task = new Task(basicName,basicDescription,firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        assertNull(task.getLocation());

        Location loc = new Location("here");
        task.setLocation(loc);
        assertEquals(loc, task.getLocation());
    }
}
