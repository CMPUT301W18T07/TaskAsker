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

    public TaskTest(){
        try{
            firstUser = new User("req", "req@fake.com", "000-000-0000", "Requester", "Fake");
            secondUser = new User("take","take@fake.com","000-000-0000","Taker","Fake");
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
            Task newTask = new Task("","",firstUser);
            assertNotNull(newTask);
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            Task newTask = new Task("aaaaaaaaaa", "", firstUser);
            assertNotNull(newTask);
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            Task newTask = new Task("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "", firstUser);
            assertNotNull(newTask);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            Task newTask = new Task("", "aaaaaaaaaa", firstUser);
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
            Task newTask = new Task("", tooLong, firstUser);
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
            task = new Task("","",firstUser);
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
            task = new Task("","",firstUser);
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
    public void getNameTest(){
        Task task = new Task("");
        try{
            task = new Task("", "", firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        assertEquals("", task.getName());

        try{
            task = new Task("Primary", "", firstUser);
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
            task = new Task("","",firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        assertEquals("",task.getDescription());

        try{
            task = new Task("","First Task",firstUser);
        } catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
        assertEquals("First Task",task.getDescription());
    }
}
