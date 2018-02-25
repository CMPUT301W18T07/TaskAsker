package com.example.lucas.taskaskerapp;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;
import junit.framework.TestCase;

/**
 * Created by lucas on 2/25/2018.
 */

public class MainActivityTest extends ActivityInstrumentationTestCase2 {
    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Test
    public void testStart() {
        Activity activity = getActivity();
        assertNotNull(activity);
    }

    @Test
    public void testEditTask() {
        MainActivity activity = (MainActivity) getActivity();
        activity.getTasks().clear();

        //Test adding a Task
        Task task = new Task("Hello!");
        activity.getTasks().add(task);
        assertEquals(1,activity.getTasks().size());
        assertEquals("Hello!",activity.getTasks().get(0).getName());
    }
}
