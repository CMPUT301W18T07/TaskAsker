/*
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Purpose:
 * Search through the tasks
 *
 * Design Rationale:
 * Initially brings up all tasks and then can specify keyword to limit search.
 *
 * @author Dylan
 * @version 1.5
 * @see Bid
 */
public class SearchActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private ArrayList<Task> openTaskList;
    private TaskSearchListAdapter taskAdapter;
    private ListView taskListView;
    private ProgressBar progressBar;
    private String myUserName;
    private EditText keywords;

    /**
     * Purpose:
     * Sets the view when Search activity is started.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        myUserName = getIntent().getStringExtra("username");

        taskListView = findViewById(R.id.SearchView);
        progressBar = findViewById(R.id.ProgressBar1);
        progressBar.setVisibility(View.GONE);

        openTaskList = controller.getOpenTasks();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        taskAdapter = new TaskSearchListAdapter(getApplicationContext(), openTaskList, "Requester", myUserName);

        taskListView.setAdapter(taskAdapter);


        Button searchButton = findViewById(R.id.searchButton);
        keywords = findViewById(R.id.searchKeywords);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                openTaskList.clear();
                openTaskList.addAll(controller.getOpenTasks(keywords.getText().toString()));
                taskAdapter.notifyDataSetChanged();

            }
        });
    }

    /**
     * Purpose:
     * When the search is resumed reset the task list to all open tasks
     */
    @Override
    protected void onResume() {
        super.onResume();
        keywords.setText("");
        try {
            openTaskList.clear();
            Thread.sleep(500);
            openTaskList.addAll(controller.getOpenTasks());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //openTaskList.addAll(controller.getOpenTasks());
        taskAdapter.notifyDataSetChanged();
    }
}