/*
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Purpose:
 * Activity that edits a task
 *
 * Design Rationale:
 * Needed simple layout for users to edit task info.
 *
 * @author Dylan
 * @version 1.5
 * @see User
 */
public class EditTask extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private EditText username;
    private TextView errorMessage;
    private EditTask activity = this;
    private User check = null;
    private ConnectivityManager cm;
    private ArrayList<Bitmap> imageFolder;



    /**
     * Purpose:
     * Sets up the view when the edit task activity is started
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        final int taskID = getIntent().getIntExtra("task ID", 0);

        final Task task = controller.getTaskById(taskID);
        //final int index = getIntent().getIntExtra("Index", -1);

        final Button backButton = findViewById(R.id.backTaskButton);
        final Button editButton = findViewById(R.id.editTaskButton);

        final TextView title = findViewById(R.id.title);
        final TextView description = findViewById(R.id.description);


        title.setText(task.getName());
        description.setText(task.getDescription());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (task.getStatus().equalsIgnoreCase("requested")) {
                    String titleText = title.getText().toString();
                    String descriptionText = description.getText().toString();
                    ArrayList<String> photoFolder = task.getImageFolder();

                    try {
                        Task taskEdit = new Task(titleText, descriptionText, task.getRequester());

                        controller.deleteTaskById(taskID);

                        taskEdit.setTaskID(task.getTaskID());
                        //taskEdit.setTaker(task.getTaker());

                        controller.saveTask(taskEdit);
                        setResult(RESULT_OK);
                        finish();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "To edit a task the status must be requested.", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}