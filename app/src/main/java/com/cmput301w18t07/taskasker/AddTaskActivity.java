/* Add Task
 *
 * March 2018
 *
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;


/**
 * Purpose:
 * Activity that gets the user to add a task.
 *
 * Design Rationale:
 * Having a separate activity for the user to enter the information allowed for a task and
 * then save that task.
 *
 * @author Dylan
 * @version 1.5
 * @see Task
 */
public class AddTaskActivity extends AppCompatActivity {

    private String url = "http://cmput301.softwareprocess.es:8080/cmput301w18t07";
    private SearchController controller = new SearchController(url);
    private AddTaskActivity activity = this;
    private EditText title;
    private EditText description;
    private User user;
    private String username;
    private Task task;
    private Bitmap outBitmap;
    private TextView textTargetUri;




    /**
     * Purpose:
     * Sets the view when Add task activity is started.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        textTargetUri = findViewById(R.id.targetUri);

        title = findViewById(R.id.titleEditText);
        description = findViewById(R.id.descriptionEditText);

        username = getIntent().getStringExtra("username");

        //user = controller.getUserByUsername(username);
        //Gson gson = new Gson();
        //user = gson.fromJson(getIntent().getStringExtra("user"), User.class);

        final Button cancelButton = findViewById(R.id.cancelTaskButton);
        final Button addTaskButton = findViewById(R.id.addTaskButton);
        final Button addPhotoButton = findViewById(R.id.addPhotoButton);

        addPhotoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    task = new Task(title.getText().toString(),description.getText().toString(),controller.getUserByUsername(username));
                    task.setImage(outBitmap);
                    task.setTaskID(controller.getMaxTaskId());
                    controller.saveTask(task);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                setResult(RESULT_OK);
                finish();
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode == RESULT_OK){
            Uri resultUri = data.getData();
            String imagepath = resultUri.getPath();
            textTargetUri.setText(resultUri.toString());
            Bitmap bitmap;

            if(imagepath != null){

                outBitmap = BitmapFactory.decodeFile(imagepath);

            }

            //try{
                //bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                //bitmap.compress(Bitmap.CompressFormat.JPEG, 10, out);
               // Bitmap decode = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));




           // } catch (FileNotFoundException e){
                //e.printStackTrace();
            //} catch (IOException e){
                //e.printStackTrace();
           // }

    }


    }
}
