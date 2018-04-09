/*
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Purpose:
 * Accept a bid on a given task
 *
 * Design Rationale:
 * Way to accept bids on a task.
 *
 * @author Thomas, Dylan, Lucas
 * @version 1.5
 * @see Bid
 */
public class AcceptBidActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private ListView bidListView;
    Task task;

    /**
     * Purpose:
     * Sets the view when Accept Bid activity is started.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_bid);

        int taskID = getIntent().getIntExtra("taskID", 0);
        task = controller.getTaskById(taskID);

        bidListView = findViewById(R.id.bidListView);

        ArrayList<Bid> bidList = controller.getTaskById(taskID).getBidList();

        BidListAdapter bidAdapter = new BidListAdapter(getApplicationContext(), bidList);

        bidListView.setAdapter(bidAdapter);

        bidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(AcceptBidActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.accept_bid_dialogbox, null);
                Button acceptButton = dialogView.findViewById(R.id.acceptButton);
                Button deleteButton = dialogView.findViewById(R.id.deleteButton);

                final Bid bid = (Bid) parent.getItemAtPosition(position);

                acceptButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        task.setBid(bid);
                        task.setLowestBid(bid.getBid());
                        task.setTaker(bid.getBidder());
                        Toast.makeText(getApplicationContext(), "Bid Accepted", Toast.LENGTH_LONG).show();
                        controller.updateTask(task);
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("task ID", task.getTaskID());
                        setResult(RESULT_OK, resultIntent);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        finish();
                    }
                });
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        task.removeBid(bid);
                        Toast.makeText(getApplicationContext(), "Bid Declined", Toast.LENGTH_LONG).show();
                        controller.updateTask(task);
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("task ID", task.getTaskID());
                        setResult(RESULT_CANCELED, resultIntent);
                        finish();
                    }
                });
                dialogBuilder.setView(dialogView);
                AlertDialog dialogBox = dialogBuilder.create();
                dialogBox.show();
            }
        });

    }
}