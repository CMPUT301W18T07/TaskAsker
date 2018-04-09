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
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
    private ImageView targetImage;
    private File imageFile;
    private ArrayList<Bitmap> bitmapList = new ArrayList<>();
    private ArrayList<String> base64Array = new ArrayList<>();
    private int arrayIndex = 0;
    private Button addPhotoButton;
    private ProgressBar newProgress;


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

        title = findViewById(R.id.titleEditText);
        description = findViewById(R.id.descriptionEditText);
        username = getIntent().getStringExtra("username");
        newProgress = findViewById(R.id.ProgressBar1);
        newProgress.setVisibility(View.GONE);

        final Button cancelButton = findViewById(R.id.cancelTaskButton);
        final Button addTaskButton = findViewById(R.id.addTaskButton);
        addPhotoButton = findViewById(R.id.addPhotoButton);
        final Button addLocationButton = findViewById(R.id.addLocation);

        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "LOCATION FEATURE COMING SOON", Toast.LENGTH_LONG).show();
            }
        });

        addPhotoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newProgress.setVisibility(View.VISIBLE);
                finish();
            }
        });
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newProgress.setVisibility(View.VISIBLE);

                try {
                    task = new Task(title.getText().toString(), description.getText().toString(), controller.getUserByUsername(username));
                    task.setTaskID(controller.getMaxTaskId());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                if(bitmapList.size() != 0) {
                    if (task != null) {
                        try {
                            for (int i = 0; i < arrayIndex; i++) {
                                Bitmap bm = bitmapList.get(i);
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                bm.compress(Bitmap.CompressFormat.WEBP, 100, baos);
                                byte[] b = baos.toByteArray();
                                String base64Image = Base64.encodeToString(b, Base64.DEFAULT);
                                base64Array.add(i, base64Image);
                            }
                            task.setImage(base64Array);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }else{
                    newProgress.setVisibility(View.GONE);
                }
                if (task != null) {

                    controller.saveTask(task);
                    setResult(RESULT_OK);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    finish();
                }
            }
        });

    }

    /**
     * Purpose:
     * After inserting a photo checks the size and sets it to the task.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode == RESULT_OK){
            Uri resultUri = data.getData();
            targetImage = findViewById(R.id.imageView);
            Boolean toLarge = false;


            try{
                outBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),resultUri);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                outBitmap.compress(Bitmap.CompressFormat.WEBP, 100, baos);
                byte[] b = baos.toByteArray();
                long imageSize = b.length;
                if (imageSize > 65536){
                    for(int i = 100; i > 30; i--){
                        outBitmap.compress(Bitmap.CompressFormat.WEBP, i, baos);
                        byte[] bt = baos.toByteArray();
                        imageSize = bt.length;
                        if(imageSize < 65536){
                            break;
                        }
                    }
                    if(imageSize > 65536){
                        toLarge = true;
                    }


                }else{
                    Toast.makeText(getApplicationContext(), Long.toString(imageSize), Toast.LENGTH_LONG).show();
                }

            } catch (IOException e){
                e.printStackTrace();
            }
            if(arrayIndex > 9){
                Toast.makeText(getApplicationContext(), "Max 10 Photos", Toast.LENGTH_LONG).show();
            }
            else {
                if(!toLarge) {
                    bitmapList.add(arrayIndex, outBitmap);
                    targetImage.setImageBitmap(outBitmap);
                    arrayIndex++;
                }else{
                    Toast.makeText(getApplicationContext(), "Image Size Too Large", Toast.LENGTH_LONG).show();
                }
            }

            String newText = "Add Another Photo";
            addPhotoButton.setText(newText);
        }
    }
}
