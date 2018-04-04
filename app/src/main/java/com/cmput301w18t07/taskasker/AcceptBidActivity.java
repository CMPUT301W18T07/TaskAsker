/*
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class AcceptBidActivity extends AppCompatActivity {

    private ListView bidListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_bid);

        bidListView = findViewById(R.id.bidListView);

        bidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(AcceptBidActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.accept_bid_dialogbox, null);
                Button acceptButton = dialogView.findViewById(R.id.acceptButton);
                Button deleteButton = dialogView.findViewById(R.id.deleteButton);
                acceptButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //TODO: Functionality for accepting Bids
                        //MAKE SURE CHANGE STATUS FIELD
                    }
                });
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //TODO: Functionality for deleting Bids
                        //MAKE SURE TO CHECK TO SEE IF THERE ARE NO BIDS, IF SO CHANGE THE STATUS
                    }
                });
                dialogBuilder.setView(dialogView);
                AlertDialog dialogBox = dialogBuilder.create();
                dialogBox.show();
            }
        });

    }
}