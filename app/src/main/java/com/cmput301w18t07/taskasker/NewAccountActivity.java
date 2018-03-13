package com.cmput301w18t07.taskasker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewAccountActivity extends AppCompatActivity {
    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07"; //URL for elasticsearch??
    private EditText firstName;
    private EditText lastName;
    private EditText username;
    private EditText email;
    private SearchController sc = new SearchController(url);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        firstName = (EditText) findViewById(R.id.firstNameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);
        username = (EditText) findViewById(R.id.usernameEditText);
        email = (EditText) findViewById(R.id.emailEditText);

        Button signupButton = (Button) findViewById(R.id.signupButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                String firstNameText = firstName.getText().toString();
                String lastNameText = lastName.getText().toString();
                String usernameText = username.getText().toString();
                String emailText = email.getText().toString();
                String phoneNumber = "000-000-0000"; //Filler phone number
                try{
                    User user = new User(usernameText,emailText,phoneNumber,firstNameText,lastNameText);
                    sc.saveUser(user);
                }
                catch(Exception e){}
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: Exit back to login
                setResult(RESULT_OK);
            }
        });
    }
}
