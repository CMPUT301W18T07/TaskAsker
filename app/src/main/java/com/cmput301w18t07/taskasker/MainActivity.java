package com.cmput301w18t07.taskasker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Task> AcceptedTaskList;
    private ArrayList<Task> RequestedTaskList;
    private ListView acceptedTaskListView;
    private ListView requestedTaskListView;
    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private MainActivity activity = this;
    private User check = null;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        user = gson.fromJson(getIntent().getStringExtra("user"), User.class);

        acceptedTaskListView = findViewById(R.id.acceptedListView);
        requestedTaskListView = findViewById(R.id.acceptedListView);

        acceptedTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = (Task) parent.getItemAtPosition(position);
                int taskID = task.getTaskID();
                Intent intent = new Intent(activity, TaskDetailsActivity.class);
                intent.putExtra("task ID", taskID);
                startActivity(intent);
            }
        });


        requestedTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = (Task) parent.getItemAtPosition(position);
                int taskID = task.getTaskID();
                Intent intent = new Intent(activity, TaskDetailsActivity.class);
                intent.putExtra("task ID", taskID);
                startActivity(intent);
            }
        });
    }


    public void profileClick(View view){
        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra("username",user.getUsername());
        startActivity(intent);
    }
}
