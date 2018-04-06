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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
public class TaskDetailsActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private EditText username;
    private TextView errorMessage;
    private TaskDetailsActivity activity = this;
    private User check = null;
    private ConnectivityManager cm;
    private ImageView imageView;
    private ArrayList<Bitmap> imageFolder = new ArrayList<>();
    private ArrayList<String> base64Folder = new ArrayList<>();
    private int arrayIndex = 0;


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
        final Button nextPhoto = findViewById(R.id.nextPhoto);
        final Button lastPhoto = findViewById(R.id.lastPhoto);

        final TextView title = findViewById(R.id.title);
        final TextView status = findViewById(R.id.status);
        final TextView lowestBid = findViewById(R.id.lowestbid);
        final TextView description = findViewById(R.id.description);

        imageView = findViewById(R.id.imageView);

        base64Folder = task.getImageFolder();

        for(int i = 0; i < base64Folder.size(); i++){
            String base64Image = base64Folder.get(i);
            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap bm = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageFolder.add(i, bm);
        }

        if(imageFolder.isEmpty()){
            Toast.makeText(getApplicationContext(), "No Picture Found", Toast.LENGTH_LONG).show();
        }else{
            imageView.setImageBitmap(imageFolder.get(0));
        }




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

        nextPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrayIndex < imageFolder.size() - 1){
                    arrayIndex++;
                    imageView.setImageBitmap(imageFolder.get(arrayIndex));
                }else{
                    arrayIndex = 0;
                    imageView.setImageBitmap(imageFolder.get(arrayIndex));
                }

            }
        });

        lastPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrayIndex > 0){
                    arrayIndex--;
                    imageView.setImageBitmap(imageFolder.get(arrayIndex));
                }else{
                    arrayIndex = imageFolder.size() - 1;
                    imageView.setImageBitmap(imageFolder.get(arrayIndex));
                }
            }
        });

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
