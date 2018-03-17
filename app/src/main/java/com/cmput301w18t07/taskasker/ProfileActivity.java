package com.cmput301w18t07.taskasker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

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
        //Gson gson = new Gson();
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
                intent.putExtra("username",username);
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