package com.example.lucas.taskaskerapp;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lucas on 2/25/2018.
 */

public class TaskTest {

    @Test
    public void testCreateTask(){
        Task task = new Task("Test Name");
        assertNotNull(task);
    }

    @Test
    public void testTaskGetters(){
        Task task = new Task("Test Name");
        assertEquals("Test Name",task.getName());
    }
}
