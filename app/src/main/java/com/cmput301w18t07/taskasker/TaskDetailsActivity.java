package com.cmput301w18t07.taskasker;

import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class TaskDetailsActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private EditText username;
    private TextView errorMessage;
    private TaskDetailsActivity activity = this;
    private User check = null;
    private ConnectivityManager cm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task_details);

        int taskID = getIntent().getIntExtra("task ID", 0);
        //Task task = controller.getTaskByTaskID(taskID);

        //final int index = getIntent().getIntExtra("Index", -1);

        final TextView title = findViewById(R.id.title);
        final TextView status = findViewById(R.id.status);
        final TextView lowestBid = findViewById(R.id.lowestbid);
        final TextView description = findViewById(R.id.description);


        //title.setText(task.getName());
        //status.setText(task.getStatus());
        //lowestBid.setText("$" + String.format("%.2f", task.getMinPrice()));
        //description.setText(task.getDescription());


    }
}
