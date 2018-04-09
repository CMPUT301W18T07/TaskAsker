package com.cmput301w18t07.taskasker;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.os.SystemClock.sleep;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.*;
import static org.hamcrest.Matchers.anything;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class IntentTesting {
    private String testFirst = "First";
    private String testLast = "Last";
    private String testUser = "testing999";
    private String testEmail = "first_last@testing.com";
    private String testPhone = "123-456-7890";
    private SearchController controller = new SearchController("http://cmput301.softwareprocess.es:8080/cmput301w18t07");

    public void checkMain(){
        onView(withId(R.id.acceptedListView)).check(matches(isDisplayed()));
        onView(withId(R.id.requestedListView)).check(matches(isDisplayed()));
        onView(withId(R.id.taskSpinner)).check(matches(isDisplayed()));
        onView(withId(R.id.imageButton3)).check(matches(isDisplayed()));
        onView(withId(R.id.imageButton4)).check(matches(isDisplayed()));
        onView(withId(R.id.imageButton5)).check(matches(isDisplayed()));
    }

    public void createUser(boolean full){
        if(controller.getUserByUsername(testUser) != null) {
            controller.deleteUserByUsername(testUser);
        }

        onView(withId(R.id.createAccountButton)).perform(click());
        ActivityRule = new ActivityTestRule<>(NewAccountActivity.class);

        onView(withId(R.id.firstNameEditText)).perform(clearText(), typeText(testFirst));
        onView(withId(R.id.lastNameEditText)).perform(clearText(), typeText(testLast));
        onView(withId(R.id.usernameEditText)).perform(clearText(), typeText(testUser));
        onView(withId(R.id.emailEditText)).perform(clearText(), typeText(testEmail));
        onView(withId(R.id.phoneEditText)).perform(clearText(), typeText(testPhone));
        closeSoftKeyboard();

        onView(withId(R.id.signupButton)).perform(click());
        ActivityRule = new ActivityTestRule<>(LoginActivity.class);

        if(full) {
            onView(withId(R.id.usernameEditText)).perform(clearText(), typeText(testUser));
            closeSoftKeyboard();
            onView(withId(R.id.loginButton)).perform(click());
            ActivityRule = new ActivityTestRule<>(MainActivity.class);
        }
    }

    @Rule
    public ActivityTestRule ActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void logInScreenTest(){
        onView(withId(R.id.usernameEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.createAccountButton)).check(matches(isDisplayed()));
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));
    }

    @Test
    public void goToNewUserTest(){
        if(controller.getUserByUsername(testUser) != null) {
            controller.deleteUserByUsername(testUser);
        }

        onView(withId(R.id.createAccountButton)).perform(click());
        ActivityRule = new ActivityTestRule<>(NewAccountActivity.class);

        onView(withId(R.id.firstNameEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.lastNameEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.usernameEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.emailEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.phoneEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.cancelButton)).check(matches(isDisplayed()));
        onView(withId(R.id.signupButton)).check(matches(isDisplayed()));

        onView(withId(R.id.cancelButton)).perform(click());
        ActivityRule = new ActivityTestRule<>(LoginActivity.class);

        //Check that we are back on the login page
        onView(withId(R.id.usernameEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.createAccountButton)).check(matches(isDisplayed()));
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));
    }

    @Test
    public void makeANewUserTest(){
        createUser(false);

        //Check that we are back on the login page
        onView(withId(R.id.usernameEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.createAccountButton)).check(matches(isDisplayed()));
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));
    }

    @Test
    public void logInTest(){
        createUser(true);

        checkMain();
    }

    @Test
    public void goToProfileTest(){
        createUser(true);

        onView(withId(R.id.imageButton4)).perform(click());
        ActivityRule = new ActivityTestRule<>(ProfileActivity.class);

        onView(withId(R.id.backButton)).check(matches(isDisplayed()));
        onView(withId(R.id.editButton)).check(matches(isDisplayed()));

        onView(withId(R.id.backButton)).perform(click());
        ActivityRule = new ActivityTestRule<>(MainActivity.class);

        checkMain();
    }

    @Test
    public void editProfileTest(){
        createUser(true);

        onView(withId(R.id.imageButton4)).perform(click());
        ActivityRule = new ActivityTestRule<>(ProfileActivity.class);

        onView(withId(R.id.editButton)).perform(click());
        ActivityRule = new ActivityTestRule<>(EditProfileActivity.class);

        onView(withId(R.id.firstnameEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.lastnameEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.emailEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.phoneEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.usernameTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.usernameTextView)).check(matches(withText(testUser)));
        onView(withId(R.id.cancelButton)).check(matches(isDisplayed()));
        onView(withId(R.id.confirmButton)).check(matches(isDisplayed()));

        onView(withId(R.id.firstnameEditText)).perform(clearText(), typeText(testFirst+"alt"));
        onView(withId(R.id.lastnameEditText)).perform(clearText(), typeText(testLast+"alt"));

        closeSoftKeyboard();
        onView(withId(R.id.confirmButton)).perform(click());
        ActivityRule = new ActivityTestRule<>(MainActivity.class);

        checkMain();

        //Check that the user information changed
        User user;
        boolean thrown = false;
        try{
            user = controller.getUserByUsername(testUser);
            assertNotNull(user);
            assertEquals(testFirst+"alt", user.getFirstName());
            assertEquals(testLast+"alt", user.getLastName());
            assertEquals(testUser, user.getUsername());
            assertEquals(testEmail, user.getEmail());
            assertEquals(testPhone, user.getPhoneNumber());
        } catch(Exception e){
            e.printStackTrace();
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    public void goToAddTaskTest(){
        createUser(true);

        onView(withId(R.id.imageButton3)).perform(click());
        ActivityRule = new ActivityTestRule<>(AddTaskActivity.class);

        onView(withId(R.id.titleEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.descriptionEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.cancelTaskButton)).check(matches(isDisplayed()));
        onView(withId(R.id.addTaskButton)).check(matches(isDisplayed()));
        onView(withId(R.id.addLocation)).check(matches(isDisplayed()));

        onView(withId(R.id.cancelTaskButton)).perform(click());
        ActivityRule = new ActivityTestRule<>(MainActivity.class);

        checkMain();
    }

    @Test
    public void addNewTaskTest(){
        createUser(true);

        onView(withId(R.id.imageButton3)).perform(click());
        ActivityRule = new ActivityTestRule<>(AddTaskActivity.class);

        onView(withId(R.id.titleEditText)).perform(clearText(), typeText("Test"));
        onView(withId(R.id.descriptionEditText)).perform(clearText(), typeText("A Test"));
        closeSoftKeyboard();
        onView(withId(R.id.addTaskButton)).perform(click());
        ActivityRule = new ActivityTestRule<>(MainActivity.class);

        checkMain();
    }

    @Test
    public void clickNewTaskTest(){
        createUser(true);

        onView(withId(R.id.imageButton3)).perform(click());
        ActivityRule = new ActivityTestRule<>(AddTaskActivity.class);

        onView(withId(R.id.titleEditText)).perform(clearText(), typeText("Test"));
        onView(withId(R.id.descriptionEditText)).perform(clearText(), typeText("A Test"));
        closeSoftKeyboard();
        onView(withId(R.id.addTaskButton)).perform(click());
        ActivityRule = new ActivityTestRule<>(MainActivity.class);

        checkMain();
        onData(anything()).inAdapterView(withId(R.id.requestedListView)).atPosition(0).perform(click());
        ActivityRule = new ActivityTestRule<>(MyTaskDetailsActivity.class);

        onView(withId(R.id.deleteTaskButton)).check(matches(isDisplayed()));
        onView(withId(R.id.editTaskButton)).check(matches(isDisplayed()));
        onView(withId(R.id.lastPhoto)).check(matches(isDisplayed()));
        onView(withId(R.id.nextPhoto)).check(matches(isDisplayed()));
        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.status)).check(matches(isDisplayed()));
        onView(withId(R.id.lowestbid)).check(matches(isDisplayed()));
        onView(withId(R.id.description)).check(matches(isDisplayed()));
        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
    }
}
