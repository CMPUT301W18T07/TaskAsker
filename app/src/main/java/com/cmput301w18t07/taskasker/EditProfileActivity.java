package com.cmput301w18t07.taskasker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                //TODO add check for changed profile
                controller.deleteUserByUsername(username);
                user.setEmail(email.getText().toString());
                user.setFirstName(firstName.getText().toString());
                user.setLastName(lastName.getText().toString());
                user.setPhoneNumber(phone.getText().toString());

                controller.saveUser(user);
                finish();
            }
        });


    }
}
