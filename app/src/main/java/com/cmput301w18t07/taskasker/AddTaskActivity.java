/* Add Task
 *
 * March 2018
 *
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Purpose:
 * Activity that gets the user to add a task.
 *
 * Design Rationale:
 * Having a separate activity for the user to enter the information allowed for a task and
 * then save that task.
 *
 * @author Dylan
 * @version 1.5
 * @see Task
 */
public class AddTaskActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private AddTaskActivity activity = this;
    private EditText title;
    private EditText description;
    private User user;
    private String username;
    private Task task;


    /**
     * Purpose:
     * Sets the view when Add task activity is started.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        title = findViewById(R.id.titleEditText);
        description = findViewById(R.id.descriptionEditText);

        username = getIntent().getStringExtra("username");
        //user = controller.getUserByUsername(username);
        //Gson gson = new Gson();
        //user = gson.fromJson(getIntent().getStringExtra("user"), User.class);

        final Button cancelButton = findViewById(R.id.cancelTaskButton);
        final Button addTaskButton = findViewById(R.id.addTaskButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    task = new Task(title.getText().toString(),description.getText().toString(),controller.getUserByUsername(username));
                    controller.saveTask(task);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });

    }
}
