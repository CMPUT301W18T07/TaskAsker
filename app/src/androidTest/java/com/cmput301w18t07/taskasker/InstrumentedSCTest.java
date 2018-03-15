package com.cmput301w18t07.taskasker;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by lucasgauk on 2018-03-14.
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedSCTest {
    String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    @Test
    public void createTest(){
        SearchController controller = new SearchController(url);
        assertNotNull(controller);
    }
    @Test
    public void putTaskTest(){
        SearchController controller = new SearchController(url);
        Task task = new Task("Test");
        controller.saveTask(task);
    }
    @Test
    public void putUserTest(){
        SearchController controller = new SearchController(url);
        User user = new User("Test2");
        controller.saveUser(user);
    }
    @Test
    public void getUserTest(){
        SearchController controller = new SearchController(url);
        User user = new User("Test2");
        controller.saveUser(user);
        assertEquals("Test2",controller.getUserByUsername("Test2").getUsername());
        controller.deleteUserByUsername("Test2");
        sleep2();
        assertEquals(null,controller.getUserByUsername("Test2"));
    }
    @Test
    public void deleteAllUserTest(){
        SearchController controller = new SearchController(url);
        User user1 = new User("Bill");
        User user2 = new User("Jill");
        controller.deleteAllUsers();
        assertEquals(null,controller.getUserByUsername("Bill"));
        assertEquals(null,controller.getUserByUsername("Jill"));
    }

    /*Helper function*/
    private void sleep2(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
