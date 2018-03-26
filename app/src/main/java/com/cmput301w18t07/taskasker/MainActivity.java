/* Main Activity
 *
 * March 2018
 *
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Purpose:
 * Shows the list of tasks the user has requested and accepted. Can request more tasks from this
 * activity or view user profile.
 *
 * Design Rationale:
 * Have an informative main screen that shows a user their most important tasks that they have
 * requested or accepted.
 *
 * @author Dylan, Thomas, Brendan, Tyler
 * @version 1.5
 * @see Task
 * @see User
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> AcceptedTaskList;
    private ArrayList<Task> RequestedTaskList;
    private ListView acceptedTaskListView;
    private ListView requestedTaskListView;
    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private MainActivity activity = this;
    private User check = null;
    private User user;
    private ArrayList<Task> acceptedTaskList;
    private ArrayList<Task> requestedTaskList;
    private TaskListAdapter requestedAdapter;
    private TaskListAdapter acceptedAdapter;


    /**
     * Purpose:
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        user = gson.fromJson(getIntent().getStringExtra("user"), User.class);

        acceptedTaskListView = findViewById(R.id.acceptedListView);
        requestedTaskListView = findViewById(R.id.requestedListView);

        acceptedTaskList = controller.getTaskByTaker(user.getUsername());
        requestedTaskList = controller.getTaskByRequester(user.getUsername());

        acceptedAdapter = new TaskListAdapter(getApplicationContext(), acceptedTaskList);
        requestedAdapter = new TaskListAdapter(getApplicationContext(), requestedTaskList);

        acceptedTaskListView.setAdapter(acceptedAdapter);
        requestedTaskListView.setAdapter(requestedAdapter);

        acceptedTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = (Task) parent.getItemAtPosition(position);
                int taskID = task.getTaskID();
                Intent intent = new Intent(activity, TaskDetailsActivity.class);
                intent.putExtra("task ID", taskID);
                intent.putExtra("username", user.getUsername());
                startActivity(intent);
            }
        });


        requestedTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = (Task) parent.getItemAtPosition(position);
                int taskID = task.getTaskID();
                Intent intent = new Intent(activity, MyTaskDetailsActivity.class);
                intent.putExtra("task ID", taskID);
                //startActivity(intent);
                startActivityForResult(intent, 11);
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();

        //Toast.makeText(getApplicationContext(), "This is on Resume", Toast.LENGTH_LONG).show();

        try {
            acceptedTaskList = controller.getTaskByTaker(user.getUsername());
            requestedTaskList = controller.getTaskByRequester(user.getUsername());

            Thread.sleep(500);

            acceptedAdapter = new TaskListAdapter(getApplicationContext(), acceptedTaskList);
            requestedAdapter = new TaskListAdapter(getApplicationContext(), requestedTaskList);

            acceptedTaskListView.setAdapter(acceptedAdapter);
            requestedTaskListView.setAdapter(requestedAdapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
        try {
            requestedTaskList = controller.getTaskByRequester(user.getUsername());
            Thread.sleep(500);
            requestedAdapter.notifyDataSetChanged();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        //acceptedAdapter = new TaskListAdapter(getApplicationContext(), acceptedTaskList);
        //requestedAdapter = new TaskListAdapter(getApplicationContext(), requestedTaskList);

        //acceptedTaskListView.setAdapter(acceptedAdapter);
        //requestedTaskListView.setAdapter(requestedAdapter);

        //acceptedAdapter.swapItems(acceptedTaskList);
        //requestedAdapter.swapItems(requestedTaskList);
        requestedAdapter.notifyDataSetChanged();
    }


    /**
     * Purpose:
     * Starts the profile activity when the profile icon is clicked
     *
     * @param view
     */
    public void profileClick(View view){
        Intent intent = new Intent(activity, ProfileActivity.class);
        Gson gson = new Gson();
        //intent.putExtra("user",gson.toJson(user));
        intent.putExtra("username",user.getUsername());
        startActivity(intent);
    }

    /**
     * Purpose:
     * Starts the add task activity when the plus icon is clicked
     *
     * @param view
     */
    public void addTaskClick(View view) {
        Intent intent = new Intent(activity, AddTaskActivity.class);
        intent.putExtra("username", user.getUsername());
        //startActivity(intent);
        startActivityForResult(intent, 10);

    }

    public void searchTaskClick(View view) {
        Intent intent = new Intent(activity, SearchActivity.class);
        //intent.putExtra("username", user.getUsername());
        //startActivity(intent);
        startActivity(intent);

    }

    /**
     * Purpose:
     * after activity do certain action
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10){
            if(resultCode == RESULT_OK){
                try {
                    requestedTaskList = controller.getTaskByRequester(user.getUsername());
                    Thread.sleep(500);
                    requestedAdapter.notifyDataSetChanged();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } if(requestCode == 11){
            if(resultCode == RESULT_OK) {
                try {
                    requestedTaskList = controller.getTaskByRequester(user.getUsername());
                    Thread.sleep(500);
                    requestedAdapter.notifyDataSetChanged();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
