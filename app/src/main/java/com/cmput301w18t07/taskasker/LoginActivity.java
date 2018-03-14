package com.cmput301w18t07.taskasker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private EditText username;
    private TextView errorMessage;
    private LoginActivity activity = this;
    private Button loginButton;
    private Button createNewAccount;
    private User check = null;
    private ConnectivityManager cm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        username = findViewById(R.id.usernameEditText);
        errorMessage = findViewById(R.id.noUserFound);

        loginButton = findViewById(R.id.loginButton);
        createNewAccount = findViewById(R.id.createAccountButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                if (isConnected) {
                    setResult(RESULT_OK);
                    User user = new User(username.getText().toString());
                    try {
                        check = controller.getUserByUsername(username.getText().toString());
                    } catch (Exception e) {e.printStackTrace();}

                    if (check != null && user.getUsername().toString().equals(check.getUsername())) {

                        Intent intent = new Intent(activity, MainActivity.class);
                        Gson gson = new Gson();
                        intent.putExtra("user",gson.toJson(user));
                        startActivity(intent);
                        
                    }
                    else {errorMessage.setVisibility(View.VISIBLE);}
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
