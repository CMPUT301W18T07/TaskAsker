package com.cmput301w18t07.taskasker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class NewAccountActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07"; //URL for elasticsearch??
    private SearchController controller = new SearchController(url);
    private NewAccountActivity activity = this;
    private EditText firstName;
    private EditText lastName;
    private EditText username;
    private EditText email;
    private SearchController sc = new SearchController(url);
    private User check = null;
    private User user;

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


                try {
                    check = controller.getUserByUsername(usernameText);
                } catch (Exception e) {e.printStackTrace();}

                if (check == null) {
                    try {
                        User user = new User(usernameText, emailText, phoneNumber, firstNameText, lastNameText);
                        sc.saveUser(user);
                        Intent intent = new Intent(activity, LoginActivity.class);
                        intent.putExtra("username", user.getUsername());
                        setResult(RESULT_OK, intent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: Exit back to login
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
