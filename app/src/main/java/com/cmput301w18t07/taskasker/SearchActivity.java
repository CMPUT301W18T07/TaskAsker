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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private ArrayList<Task> openTaskList;
    private TaskSearchListAdapter taskAdapter;
    private ListView taskListView;
    private TextView requesterTextView;
    private SearchActivity activity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        taskListView = findViewById(R.id.SearchView);
        requesterTextView = findViewById(R.id.textRequester);

        openTaskList = controller.getOpenTasks();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        taskAdapter = new TaskSearchListAdapter(getApplicationContext(), openTaskList);

        taskListView.setAdapter(taskAdapter);


        Button searchButton = findViewById(R.id.searchButton);
        final EditText keywords = findViewById(R.id.searchKeywords);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openTaskList.clear();
                openTaskList.addAll(controller.getOpenTasks(keywords.getText().toString()));
                taskAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

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