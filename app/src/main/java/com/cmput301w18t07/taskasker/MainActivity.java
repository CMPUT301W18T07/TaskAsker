package com.cmput301w18t07.taskasker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
    private ArrayList<Task> acceptedTaskList = new ArrayList<Task>();
    private ArrayList<Task> requestedTaskList = new ArrayList<Task>();
    private ArrayAdapter<Task> requestedAdapter;
    private ArrayAdapter<Task> acceptedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        user = gson.fromJson(getIntent().getStringExtra("user"), User.class);

        acceptedTaskListView = findViewById(R.id.acceptedListView);
        requestedTaskListView = findViewById(R.id.acceptedListView);


        acceptedTaskList = controller.getTaskByTaker(user.getUsername());
        requestedTaskList = controller.getTaskByRequester(user.getUsername());

        //Toast.makeText(getApplicationContext(),"TEST", Toast.LENGTH_SHORT).show();
        //for (Task task : requestedTaskList)
        //{
        //    Toast.makeText(getApplicationContext(),"TEST", Toast.LENGTH_SHORT).show();
        //    Toast.makeText(getApplicationContext(),task.getName(), Toast.LENGTH_SHORT).show();
        //}

        acceptedAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, acceptedTaskList);
        requestedAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, requestedTaskList);

        acceptedTaskListView.setAdapter(acceptedAdapter);
        requestedTaskListView.setAdapter(acceptedAdapter);

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
        //Gson gson = new Gson();
        //intent.putExtra("user",gson.toJson(user));
        intent.putExtra("username",user.getUsername());
        startActivity(intent);
    }

    public void addTaskClick(View view) {
        Intent intent = new Intent(activity, AddTaskActivity.class);
        intent.putExtra("username", user.getUsername());
        startActivity(intent);
    }
}
