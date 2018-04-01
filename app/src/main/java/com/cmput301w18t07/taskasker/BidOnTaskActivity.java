/*
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Purpose:
 * Activity that allows placing of a bid on a task
 *
 * Design Rationale:
 * Having a way to add a bid in a useful way
 *
 * @author Chris
 * @version 1.5
 * @see Bid
 * @see Task
 */

public class BidOnTaskActivity extends AppCompatActivity {
    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);

    /**
     * Purpose:
     * Sets up the view when the add a bid activity is started
     *
     * @param savedInstanceState Not entirely sure what this is
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_on_task);
        final int taskID = getIntent().getIntExtra("task ID", 0);
        final String username = getIntent().getStringExtra("username");
        final Task task = controller.getTaskById(taskID);
        final User user = controller.getUserByUsername(username);

        final Button backButton = findViewById(R.id.backButton);
        final Button submitButton = findViewById(R.id.submitButton);

        final TextView title = findViewById(R.id.title);
        final TextView lowestBid = findViewById(R.id.lowestbid);
        final TextView lowestBidText = findViewById(R.id.lowestBidText);

        final EditText bidEditText = findViewById(R.id.bidEditText);

        title.setText(task.getName());
        Bid low;
        try{
            low = task.getBid();
            lowestBid.setVisibility(View.VISIBLE);
            lowestBidText.setVisibility(View.VISIBLE);
            lowestBidText.setText("$" + String.format("%.2f", task.getLowestBid()));
        } catch(Exception e){
            lowestBid.setVisibility(View.GONE);
            lowestBidText.setVisibility(View.GONE);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //@TODO: Add the new bid to the server
                double bid = Double.parseDouble(bidEditText.getText().toString());
                Bid newBid = new Bid(user, bid);
                controller.setBid(newBid,taskID);
                finish();
            }
        });
    }
}
