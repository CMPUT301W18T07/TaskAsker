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
        assertNotSame(basicFirst, user.getFirstName());
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
        assertNotSame(basicLast, user.getLastName());
        try{
            user = new User(basicName, basicEmail, basicPhone, basicFirst, basicLast);
            assertEquals(basicLast, user.getLastName());
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
            user = new User(basicName, "a@b.cd", basicPhone, basicFirst, basicLast);
            assertEquals("a@b.cd", user.getEmail());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user = new User(basicName, "a.b@c.de", basicPhone, basicFirst, basicLast);
            assertEquals("a.b@c.de", user.getEmail());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user = new User(basicName, "a.@b.cd", basicPhone, basicFirst, basicLast);
            assertEquals("a.@b.cd", user.getEmail());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user = new User(basicName, "a@b.c", basicPhone, basicFirst, basicLast);
            assertEquals("a@b.c", user.getEmail());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);

        thrown = false;
        try{
            user = new User(basicName, "", basicPhone, basicFirst, basicLast);
            assertNull(user.getEmail());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user = new User(basicName, " ", basicPhone, basicFirst, basicLast);
            assertNull(user.getEmail());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user = new User(basicName, ".", basicPhone, basicFirst, basicLast);
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
            user = new User(basicName, basicEmail, "0000000000", basicFirst, basicLast);
            assertNull(user.getPhoneNumber());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user = new User(basicName, basicEmail, "", basicFirst, basicLast);
            assertNull(user.getPhoneNumber());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user = new User(basicName, basicEmail, "0", basicFirst, basicLast);
            assertNull(user.getPhoneNumber());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user = new User(basicName, basicEmail, "aaaaaaaaaa", basicFirst, basicLast);
            assertNull(user.getPhoneNumber());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try{
            user = new User(basicName, basicEmail, "aaa-aaa-aaaa", basicFirst, basicLast);
            assertNull(user.getPhoneNumber());
        } catch(Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }
}
