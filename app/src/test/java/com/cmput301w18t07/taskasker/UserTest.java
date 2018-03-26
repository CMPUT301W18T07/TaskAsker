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
 * Test a User object.
 *
 * Design Rationale:
 * Needed to test the User class
 *
 * @author Chris
 * @version 1.5
 */

public class UserTest {
    private String basicName = "testing1";
    private String basicEmail = "example@test.com";
    private String basicPhone = "000-000-0000";
    private String basicFirst = "Test";
    private String basicLast = "Example";

    @Test
    public void createTest(){
        User user = new User(basicName);
        assertNotNull(user);
        boolean thrown = false;
        try{
            User newUser = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertNotNull(newUser);
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    public void getUsernameTest(){
        User user = new User(basicName);
        assertEquals(basicName,user.getUsername());
        try{
            user = new User("test2",basicEmail,basicPhone,basicFirst,basicLast);
            assertEquals("test2",user.getUsername());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getEmailTest(){
        User user = new User(basicName);
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(basicEmail, user.getEmail());
        } catch(Exception e) {
            e.printStackTrace();
        }
        assertNotNull(user);
    }

    @Test
    public void getPhoneNumberTest(){
        User user = new User(basicName);
        assertNotSame(basicPhone, user.getPhoneNumber());
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(basicPhone, user.getPhoneNumber());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getFirstNameTest(){
        User user = new User(basicName);
        assertNotNull(user);
        assertNull(user.getLastName());
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(basicFirst, user.getFirstName());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getLastNameTest(){
        User user = new User(basicName);
        assertNotNull(user);
        assertNull(user.getLastName());
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(basicLast, user.getLastName());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRatingTest(){
        User user = new User(basicName);
        assertNotNull(user);
        assertEquals(0.0, user.getRating());
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(3.0, user.getRating());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void usernameFormatTest(){
        User user = new User(basicName);
        assertNotNull(user);

        boolean thrown = false;
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(basicName, user.getUsername());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user = new User("a", basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals("a", user.getUsername());
        } catch(Exception e){
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user = new User("a-", basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals("a-", user.getUsername());
        } catch(Exception e){
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user = new User("", basicEmail, basicPhone, basicFirst, basicLast);
            assertNull(user.getUsername());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user = new User(" ", basicEmail, basicPhone, basicFirst, basicLast);
            assertNull(user.getUsername());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user = new User("        ", basicEmail, basicPhone, basicFirst, basicLast);
            assertNull(user.getUsername());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user = new User(".", basicEmail, basicPhone, basicFirst, basicLast);
            assertNull(user.getUsername());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user = new User("........", basicEmail, basicPhone, basicFirst, basicLast);
            assertNull(user.getUsername());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user = new User("-a", basicEmail, basicPhone, basicFirst, basicLast);
            assertNull(user.getUsername());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void emailFormatTest(){
        User user = new User(basicName);
        assertNotNull(user);

        boolean thrown = false;
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(basicEmail, user.getEmail());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user.setEmail("a@b.cd");
            assertEquals("a@b.cd", user.getEmail());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user.setEmail("a.b@c.de");
            assertEquals("a.b@c.de", user.getEmail());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user.setEmail("a.@b.cd");
            assertEquals("a.@b.cd", user.getEmail());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user.setEmail("a@b.c");
            assertEquals("a@b.c", user.getEmail());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user.setEmail("");
            assertEquals("", user.getEmail());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user.setEmail(" ");
            assertNull(user.getEmail());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user.setEmail(".");
            assertNull(user.getEmail());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testPhoneNumberFormat(){
        boolean thrown = false;
        User user = new User(basicName);
        assertNotNull(user);

        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(basicPhone, user.getPhoneNumber());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user.setPhoneNumber("0000000000");
            assertNull(user.getPhoneNumber());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user.setPhoneNumber("");
            assertNull(user.getPhoneNumber());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user.setPhoneNumber("0");
            assertNull(user.getPhoneNumber());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user.setPhoneNumber("aaaaaaaaaa");
            assertNull(user.getPhoneNumber());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user.setPhoneNumber("aaa-aaa-aaaa");
            assertNull(user.getPhoneNumber());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    //First and Last name use the same formatter, no sense testing twice, all tests performed on first name
    public void firstNameFormatTest(){
        User user = new User(basicName);
        assertNotNull(user);
        assertNull(user.getFirstName());

        boolean thrown = false;
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(basicFirst, user.getFirstName());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user.setFirstName("a");
            assertEquals("a", user.getFirstName());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user.setFirstName("a'a");
            assertEquals("a'a", user.getFirstName());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user.setFirstName("a'");
            assertEquals("a'", user.getFirstName());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user.setFirstName("'");
            assertNull(user.getFirstName());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user.setFirstName("");
            assertNull(user.getFirstName());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user.setFirstName(" ");
            assertNull(user.getFirstName());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user.setFirstName("a a");
            assertNull(user.getFirstName());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user.setFirstName("a ");
            assertNull(user.getFirstName());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void addRatingTest(){
        User user = new User(basicName);
        assertNotNull(user);
        assertEquals(0.0, user.getRating());

        boolean thrown = false;
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(3.0, user.getRating());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(3.0, user.getRating());
            user.addRating(3.0);
            assertEquals(3.0, user.getRating());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(3.0, user.getRating());
            user.addRating(4.0);
            assertEquals(3.5, user.getRating());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(3.0, user.getRating());
            user.addRating(5.0);
            assertEquals(4.0, user.getRating());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(3.0, user.getRating());
            user.addRating(3.0);
            assertEquals(3.0, user.getRating());
            user.addRating(4.0);
            double temp = 10.0/3;
            assertEquals(temp, user.getRating());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(3.0, user.getRating());
            user.addRating(6.0);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(3.0, user.getRating());
            user.addRating(0.0);
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(3.0, user.getRating());
            user.addRating(-1.0);
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }
}
