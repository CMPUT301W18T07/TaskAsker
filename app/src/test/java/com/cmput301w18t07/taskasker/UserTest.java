package com.cmput301w18t07.taskasker;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by critt on 2018-03-14.
 */

public class UserTest {
    private String basicName = "test";
    private String basicEmail = "test@example.com";
    private String basicPhone = "";

    @Test
    public void basicCreateTest(){
        User user = new User(basicName);
        assertNotNull(user);
    }

    @Test
    public void complexCreateTest(){
        //User user = new User(basicName, basicEmail, basicPhone)
    }
}
