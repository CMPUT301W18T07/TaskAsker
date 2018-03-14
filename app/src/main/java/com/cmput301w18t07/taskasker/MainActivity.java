package com.cmput301w18t07.taskasker;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView acceptedTaskList;
    private ListView requestedTaskList;
    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private MainActivity activity = this;
    private User check = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acceptedTaskList = findViewById(R.id.acceptedListView);
        requestedTaskList = findViewById(R.id.acceptedListView);

        acceptedTaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = (Task) parent.getItemAtPosition(position);
                int taskID = task.getTaskID();
                Intent intent = new Intent(activity, ViewTaskDetailsActivity.class);
                intent.putExtra("task ID", taskID);
                startActivity(intent);
            }
        });


        requestedTaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = (Task) parent.getItemAtPosition(position);
                int taskID = task.getTaskID();
                Intent intent = new Intent(activity, ViewTaskDetailsActivity.class);
                intent.putExtra("task ID", taskID);
                startActivity(intent);
            }
        });
    }
}
