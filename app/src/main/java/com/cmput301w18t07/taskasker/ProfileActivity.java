/* Profile
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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Purpose:
 * Activity that displays the information of a user
 *
 * Design Rationale:
 * Have an activity to display the users info and where a user can decide if they want to edit
 * their profile or logout
 *
 * @author Dylan, Brendan, Thomas
 * @version 1.5
 * @see User
 */
public class ProfileActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private ProfileActivity activity = this;
    private TextView firstName;
    private TextView lastName;
    private TextView email;
    private TextView phone;
    private TextView userNameTextView;
    private User user;
    private String username;


    /**
     * Purpose:
     * Sets up the view when the profile activity is started
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firstName = findViewById(R.id.firstnameTextView);
        lastName = findViewById(R.id.lastnameTextView);
        email = findViewById(R.id.emailTextView);
        phone = findViewById(R.id.phoneTextView);
        userNameTextView = findViewById(R.id.profileusernameTextView);

        username = getIntent().getStringExtra("username");
        user = controller.getUserByUsername(username);
        Gson gson = new Gson();
        //user = gson.fromJson(getIntent().getStringExtra("user"), User.class);

        final Button editButton = findViewById(R.id.editButton);
        final Button backButton = findViewById(R.id.backButton);
        final Button logOutButton = findViewById(R.id.logOutButton);

        userNameTextView.setText(username);
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        phone.setText(user.getPhoneNumber());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, EditProfileActivity.class);
                //Gson gson = new Gson();
                //intent.putExtra("user",gson.toJson(user));
                intent.putExtra("username",user.getUsername());
                startActivity(intent);
                finish();
            }
        });
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}