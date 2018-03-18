package com.cmput301w18t07.taskasker;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by critt on 2018-03-17.
 */

public class TaskTest {
    User requester;

    @Test
    public void createTest(){
        Task task = new Task("");
        assertNotNull(task);
        boolean thrown = false;
        try{
            try{
                requester = new User("req", "req@fake.com", "000-000-0000", "Requester", "Fake");
            } catch(Exception e){
                e.printStackTrace();
            }
            task = new Task("","",requester);
            assertNotNull(task);
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);
    }
}
