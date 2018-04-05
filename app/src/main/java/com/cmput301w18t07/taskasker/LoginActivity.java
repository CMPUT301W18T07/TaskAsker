/* Login
 *
 * March 2018
 *
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


/**
 * Purpose:
 * Activity that allows a user to login
 *
 * Design Rationale:
 * First screen of the app that has the user specify who they are.
 *
 * @author Brendan, Thomas
 * @version 1.5
 * @see User
 */
public class LoginActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private EditText username;
    private TextView errorMessage;
    private LoginActivity activity = this;
    private Button loginButton;
    private Button createNewAccount;
    private User user;
    private ConnectivityManager cm;
    private ProgressBar progressBar;


    /**
     * Purpose:
     * Sets the view when login activity is started.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        errorMessage = findViewById(R.id.noUserFound);
        username = findViewById(R.id.usernameEditText);
        loginButton = findViewById(R.id.loginButton);
        createNewAccount = findViewById(R.id.createAccountButton);
        progressBar = findViewById(R.id.ProgressBar1);
        progressBar.setVisibility(View.GONE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                if (isConnected) {
                    try {
                        user = controller.getUserByUsername(username.getText().toString());
                    } catch (Exception e) {e.printStackTrace();}
                    if (user != null) {
                        Intent intent = new Intent(activity, MainActivity.class);
                        Gson gson = new Gson();

                        intent.putExtra("user",gson.toJson(user));
                        //intent.putExtra("username", username.getText().toString());
                        //I think this line is unnecessary... -Thomas
                        setResult(RESULT_OK);
                        startActivity(intent);
                        
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No User Found", Toast.LENGTH_LONG).show();
                        //errorMessage.setVisibility(View.VISIBLE);
                        //wait(1000);
                        //errorMessage.setVisibility(View.INVISIBLE);
                    }
                } else {
                    //TODO: HANDLE OFFLINE ERROR MESSAGES FOR LOGIN SCREEN
                    Toast.makeText(getApplicationContext(), "Please Connect To Internet To Log In", Toast.LENGTH_LONG);
                }

            }
        });

        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, NewAccountActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }


    /**
     * Purpose:
     * When an new account activity sends back a result set the username edit text to the new username
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                username.setText(data.getStringExtra("username"));

            }
        }
    }

}
