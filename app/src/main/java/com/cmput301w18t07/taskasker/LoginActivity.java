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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
