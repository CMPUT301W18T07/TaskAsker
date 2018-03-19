package com.cmput301w18t07.taskasker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private ArrayList<Task> openTaskList;
    private TaskListAdapter taskAdapter;
    private ListView taskListView;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //taskListView = findViewById(R.id.???);

        //openTaskList = controller.getOpenTasks();

        taskAdapter = new TaskListAdapter(getApplicationContext(), openTaskList);

        taskListView.setAdapter(taskAdapter);

    }

}
