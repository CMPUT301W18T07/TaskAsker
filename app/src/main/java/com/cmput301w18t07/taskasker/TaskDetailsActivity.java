/* Task Details
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
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


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
public class TaskDetailsActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private EditText username;
    private TextView errorMessage;
    private TaskDetailsActivity activity = this;
    private User check = null;
    private ConnectivityManager cm;


    /**
     * Purpose:
     * Sets up the view when the task details activity is started
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task_details);

        final int taskID = getIntent().getIntExtra("task ID", 0);
        final String username = getIntent().getStringExtra("username");

        final Task task = controller.getTaskById(taskID);
        //final int index = getIntent().getIntExtra("Index", -1);
        final User user = controller.getUserByUsername(username);

        final Button backButton = findViewById(R.id.backButton);
        final Button bidButton = findViewById(R.id.bidButton);

        final TextView title = findViewById(R.id.title);
        final TextView status = findViewById(R.id.status);
        final TextView lowestBid = findViewById(R.id.lowestbid);
        final TextView description = findViewById(R.id.description);

        title.setText(task.getName());
        status.setText(task.getStatus());
        description.setText(task.getDescription());
        String bidString = Double.toString(task.getLowestBid());
        //Toast.makeText(activity, bidString, Toast.LENGTH_SHORT).show();
        double lBid = task.getLowestBid();
        if (lBid == 0){
            lowestBid.setText("No Bids");
        }
        else {
            lowestBid.setText("$" + String.format("%.2f", lBid));
        }

        /*Bid low;
        try{
            low = task.getBid();
            lowestBid.setVisibility(View.VISIBLE);
            lowestBidText.setVisibility(View.VISIBLE);
            lowestBid.setText("$" + String.format("%.2f", task.getLowestBid()));
        } catch(Exception e){
            lowestBid.setVisibility(View.GONE);
            lowestBidText.setVisibility(View.GONE);
        }
        */

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int taskID = task.getTaskID();
                Intent intent = new Intent(activity, BidOnTaskActivity.class);
                intent.putExtra("task ID", taskID);
                intent.putExtra("username", user.getUsername());
                startActivity(intent);
                finish();
            }
        });
    }
}
