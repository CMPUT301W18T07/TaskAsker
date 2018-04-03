/*
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Purpose:
 * Activity that displays the details of a task
 *
 * Design Rationale:
 * Having a way to display the information from a task in a user friendly way.
 *
 * @author Dylan
 * @version 1.5
 * @see Task
 */
public class MyTaskDetailsActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private EditText username;
    private TextView errorMessage;
    private MyTaskDetailsActivity activity = this;
    private User check = null;
    private ConnectivityManager cm;
    private ArrayList<Bitmap> imageFolder = new ArrayList<>();


    /**
     * Purpose:
     * Sets up the view when the task details activity is started
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_task_details);

        final int taskID = getIntent().getIntExtra("task ID", 0);

        Task task = controller.getTaskById(taskID);
        //final int index = getIntent().getIntExtra("Index", -1);
        imageFolder = task.getImageFolder();


        final Button backButton = findViewById(R.id.backTaskButton);
        final Button deleteButton = findViewById(R.id.deleteTaskButton);
        final Button editButton = findViewById(R.id.editTaskButton);


        final TextView title = findViewById(R.id.title);
        final TextView status = findViewById(R.id.status);
        final TextView lowestBid = findViewById(R.id.lowestbid);
        final TextView description = findViewById(R.id.description);

        ImageView imageView = findViewById(R.id.imageView);
        Bitmap bm = imageFolder.get(0);
        imageView.setImageBitmap(bm);


        title.setText(task.getName());
        status.setText(task.getStatus());
        lowestBid.setText("$" + String.format("%.2f", task.getLowestBid()));
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
                Intent intent = new Intent(activity, EditTask.class);
                intent.putExtra("task ID", taskID);
                startActivity(intent);
                setResult(RESULT_OK);
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            controller.deleteTaskById(taskID);
            setResult(RESULT_OK);
            finish();
            }
        });


    }
}
