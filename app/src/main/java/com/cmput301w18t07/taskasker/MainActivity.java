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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

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

    private ListView acceptedTaskListView;
    private ListView requestedTaskListView;
    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private MainActivity activity = this;
    private User user;
    private ArrayList<Task> acceptedTaskList;
    private ArrayList<Task> requestedTaskList;
    private TaskListAdapter requestedAdapter;
    private TaskListAdapter acceptedAdapter;
    private ProgressBar progressBar;
    private Spinner spinner;


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

        //listTasks = findViewById(R.id.acceptedTitleTextView);

        acceptedTaskListView = findViewById(R.id.acceptedListView);
        requestedTaskListView = findViewById(R.id.requestedListView);
        progressBar= findViewById(R.id.ProgressBar1);
        progressBar.setVisibility(View.GONE);

        spinner = findViewById(R.id.taskSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.taskTypes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        acceptedTaskList = controller.getTaskByBidder(user.getUsername()); //NOW GETS TASKS THAT YOU HAVE A BID ON
        requestedTaskList = controller.getTaskByRequester(user.getUsername());

        requestedAdapter = new TaskListAdapter(getApplicationContext(), requestedTaskList);

        requestedTaskListView.setAdapter(requestedAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if (item.equals("My Accepted Tasks")){
                    acceptedTaskList.clear();
                    acceptedTaskList = controller.getTaskByTaker(user.getUsername());
                    TaskSearchListAdapter taskAcceptedAdapter = new TaskSearchListAdapter(getApplicationContext(), acceptedTaskList, "Requester", user.getUsername());
                    acceptedTaskListView.setAdapter(taskAcceptedAdapter);
                }
                else if (item.equals("Tasks I've Bidded On")){
                    acceptedTaskList.clear();
                    acceptedTaskList = controller.getTaskByBidder(user.getUsername());
                    TaskSearchListAdapter taskBidAdapter = new TaskSearchListAdapter(getApplicationContext(), acceptedTaskList, "Bidder", user.getUsername());
                    acceptedTaskListView.setAdapter(taskBidAdapter);
                }
                else if (item.equals("My Tasks With Status Bidded")){
                    acceptedTaskList.clear();
                    acceptedTaskList = controller.getTaskByRequester(user.getUsername(),"Bidded");
                    acceptedAdapter = new TaskListAdapter(getApplicationContext(), acceptedTaskList);
                    acceptedTaskListView.setAdapter(acceptedAdapter);
                }
                else if (item.equals("My Tasks With Status Assigned")){
                    acceptedTaskList.clear();
                    acceptedTaskList = controller.getTaskByRequester(user.getUsername(),"Assigned");
                    TaskSearchListAdapter acceptedAdapter = new TaskSearchListAdapter(getApplicationContext(), acceptedTaskList, "Provider", user.getUsername());
                    acceptedTaskListView.setAdapter(acceptedAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        acceptedTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                progressBar.setVisibility(View.VISIBLE);
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
                progressBar.setVisibility(View.VISIBLE);
                Task task = (Task) parent.getItemAtPosition(position);
                int taskID = task.getTaskID();
                Intent intent = new Intent(activity, MyTaskDetailsActivity.class);
                intent.putExtra("task ID", taskID);
                intent.putExtra("username", user.getUsername());
                startActivityForResult(intent, 11);

            }
        });

    }

    /**
     * Purpose:
     * When main activity is resumed update the task lists.
     */
    @Override
    public void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);

        try {
            requestedTaskList.clear();
            requestedTaskList = controller.getTaskByRequester(user.getUsername());

            String item = spinner.getSelectedItem().toString();
            if (item.equals("My Accepted Tasks")){
                acceptedTaskList.clear();
                acceptedTaskList = controller.getTaskByTaker(user.getUsername());
                TaskSearchListAdapter taskAcceptedAdapter = new TaskSearchListAdapter(getApplicationContext(), acceptedTaskList, "Requester", user.getUsername());
                acceptedTaskListView.setAdapter(taskAcceptedAdapter);
            }
            else if (item.equals("Tasks I've Bidded On")){
                acceptedTaskList.clear();
                acceptedTaskList = controller.getTaskByBidder(user.getUsername());
                TaskSearchListAdapter taskBidAdapter = new TaskSearchListAdapter(getApplicationContext(), acceptedTaskList, "Bidder", user.getUsername());
                acceptedTaskListView.setAdapter(taskBidAdapter);
            }
            else if (item.equals("My Tasks With Status Bidded")){
                acceptedTaskList.clear();
                acceptedTaskList = controller.getTaskByRequester(user.getUsername(),"Bidded");
                acceptedAdapter = new TaskListAdapter(getApplicationContext(), acceptedTaskList);
                acceptedTaskListView.setAdapter(acceptedAdapter);
            }
            else if (item.equals("My Tasks With Status Assigned")){
                acceptedTaskList.clear();
                acceptedTaskList = controller.getTaskByRequester(user.getUsername(),"Assigned");
                TaskSearchListAdapter acceptedAdapter = new TaskSearchListAdapter(getApplicationContext(), acceptedTaskList, "Provider", user.getUsername());
                acceptedTaskListView.setAdapter(acceptedAdapter);
            }

            Thread.sleep(500);

            requestedAdapter = new TaskListAdapter(getApplicationContext(), requestedTaskList);
            requestedTaskListView.setAdapter(requestedAdapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        requestedAdapter.notifyDataSetChanged();
    }


    /**
     * Purpose:
     * Starts the profile activity when the profile icon is clicked
     *
     * @param view
     */
    public void profileClick(View view){
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(activity, ProfileActivity.class);
        Gson gson = new Gson();
        //intent.putExtra("user",gson.toJson(user));
        intent.putExtra("username",user.getUsername());
        intent.putExtra("Check", true);
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
        progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(activity, SearchActivity.class);
        intent.putExtra("username", user.getUsername());
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

