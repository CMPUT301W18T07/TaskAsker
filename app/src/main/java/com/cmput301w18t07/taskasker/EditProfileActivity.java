/* Edit Profile
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


/**
 * Purpose:
 * Activity that allows a user to edit their name and contact information
 *
 * Design Rationale:
 * Having a separate activity for the user to change the information they used to initially
 * create their account
 *
 * @author Dylan, Brendan, Thomas
 * @version 1.5
 * @see User
 */
public class EditProfileActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private EditProfileActivity activity = this;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText phone;
    private TextView userNameTextView;
    private User user;
    private String username;


    /**
     * Purpose:
     * Sets the view when edit profile activity is started.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        firstName = findViewById(R.id.firstnameEditText);
        lastName = findViewById(R.id.lastnameEditText);
        email = findViewById(R.id.emailEditText);
        phone = findViewById(R.id.phoneEditText);
        userNameTextView = findViewById(R.id.usernameTextView);

        username = getIntent().getStringExtra("username");
        user = controller.getUserByUsername(username);
        //Gson gson = new Gson();
        //user = gson.fromJson(getIntent().getStringExtra("user"), User.class);

        final Button cancelButton = findViewById(R.id.cancelButton);
        final Button confirmButton = findViewById(R.id.confirmButton);

        userNameTextView.setText(username);
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        phone.setText(user.getPhoneNumber());

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstNameText = firstName.getText().toString();
                String lastNameText = lastName.getText().toString();
                String emailText = email.getText().toString();
                String phoneNumber = phone.getText().toString();
                //TODO add check for changed profile
                try {
                    User newUser = new User(username, emailText, phoneNumber, firstNameText, lastNameText);
                    controller.deleteUserByUsername(username);
                    controller.saveUser(newUser);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
