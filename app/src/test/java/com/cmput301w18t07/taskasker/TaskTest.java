package com.cmput301w18t07.taskasker;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by critt on 2018-03-17.
 */

public class TaskTest {
    User firstUser;
    User secondUser;
    String tooLong = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

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
            e.printStackTrace();
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            Task newTask = new Task("", "aaaaaaaaaa", firstUser);
            assertNotNull(task);
        }catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            Task newTask = new Task("", tooLong, firstUser);
            assertNotNull(newTask);
        }catch(Exception e){
            e.printStackTrace();
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
