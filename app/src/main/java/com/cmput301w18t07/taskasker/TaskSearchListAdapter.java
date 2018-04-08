/*
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Purpose:
 * Represents a task object.
 *
 * Design Rationale:
 * Needed to represent a task with its title and status
 *
 * @author Dylan
 * @version 1.5
 * @see MainActivity
 * @see Task
 */
public class TaskSearchListAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Task> taskArrayList;
    private String userType;
    private String userName;

    /**
     * Purpose:1
     * Changes how a task is represented in a list view.
     *
     * @param mContext
     * @param taskArrayList
     */
    public TaskSearchListAdapter(Context mContext, ArrayList<Task> taskArrayList) {
        this.mContext = mContext;
        this.taskArrayList = taskArrayList;
        this.userType = "Requester";
    }

    /**
     * Purpose:
     * Changes how a task is represented in a list view.
     *
     * @param mContext
     * @param taskArrayList
     * @param userType
     */
    public TaskSearchListAdapter(Context mContext, ArrayList<Task> taskArrayList, String userType, String userName) {
        this.mContext = mContext;
        this.taskArrayList = taskArrayList;
        this.userType = userType;
        this.userName = userName;
    }



    /**
     * Purpose:
     * gets the size of the task list
     *
     * @return int of how long the task list is
     */
    @Override
    public int getCount() {
        return taskArrayList.size();
    }

    /**
     * Purpose:
     * Gets the position of an object in the list view
     *
     * @param position int of what position the sub is at
     * @return subscription object at specified position
     */
    @Override
    public Object getItem(int position) {
        return taskArrayList.get(position);
    }

    /**
     * Purpose:
     * Gets the id of the position
     *
     * @param position int of where the object is in the list
     * @return long of where the object is
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Purpose:
     * Gets the view of task list and add the new layout to it.
     *
     * @param position
     * @param convertView
     * @param parent
     * @return new view with sub list item layout of the list view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        TextView taskMyBid = null;
        //Toast.makeText(mContext, userType, Toast.LENGTH_SHORT).show();

        // Change the layout based on the list.
        if (userType.equals("Provider") || userType.equals("Requester")) {
            //Toast.makeText(mContext, "Search Layout", Toast.LENGTH_SHORT).show();
            v = View.inflate(mContext, R.layout.search_task_list_item, null);
        }
        else{
            //Toast.makeText(mContext, "Bid Layout", Toast.LENGTH_SHORT).show();
            v = View.inflate(mContext, R.layout.bidded_task_list_item, null);
            taskMyBid = v.findViewById(R.id.textMyBid);
        }
        TextView taskTitle = v.findViewById(R.id.textTitle);
        TextView taskStatus = v.findViewById(R.id.textStatus);
        final TextView taskRequester = v.findViewById(R.id.textRequester);
        TextView taskLowestBid = v.findViewById(R.id.textBid);

        final Task task = taskArrayList.get(position);

        try {
            taskTitle.setText(taskArrayList.get(position).getName());
            taskStatus.setText(taskArrayList.get(position).getStatus());
            if (userType.equals("Provider")) {
                taskRequester.setText(taskArrayList.get(position).getTaker().getUsername());
            }
            else if (userType.equals("Requester")) {
                taskRequester.setText(taskArrayList.get(position).getRequester().getUsername());

            }
            else {
                taskRequester.setText(taskArrayList.get(position).getRequester().getUsername());
                if (taskMyBid  != null) {

                    for (Bid b: taskArrayList.get(position).getBidList()){
                        if (b.getBidderUsername().equals(userName)){
                            //if taskMyBid.getText().
                            taskMyBid.setText("$" + String.format("%.2f", b.getBid()));
                        }
                    }
                }
            }

            double lBid = taskArrayList.get(position).getLowestBid();
            if (lBid == 0){
                taskLowestBid.setText("No Bids");
            }
            else {
                taskLowestBid.setText("$" + String.format("%.2f", lBid));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        taskRequester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (task.getRequester() == null) {
                    Toast.makeText(mContext, "Username Not Defined", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(mContext, ProfileActivity.class);
                    if (task.getRequester().getUsername().equals(userName)) {
                        intent.putExtra("Check", true);
                    }
                    else {
                        intent .putExtra("Check", false);
                    }

                    intent.putExtra("username", task.getRequester().getUsername());
                    mContext.startActivity(intent);
                }


                //Log.d(TAG, "string: " + task.getName());
                //Toast.makeText(mContext, "username clicked: " + task.getRequester().getUsername(), Toast.LENGTH_SHORT).show();
            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "view clicked: " + task.getName(), Toast.LENGTH_SHORT).show();

                //String username = task.getRequester().getUsername();

                if (task.getRequester() == null) {
                    Toast.makeText(mContext, "Username Not Defined", Toast.LENGTH_SHORT).show();
                }
                else if (task.getRequester().getUsername().equals(userName)) {
                    Intent intent = new Intent(mContext, MyTaskDetailsActivity.class);
                    intent.putExtra("task ID", task.getTaskID());
                    intent.putExtra("username", task.getRequester().getUsername());
                    mContext.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(mContext, TaskDetailsActivity.class);
                    intent.putExtra("task ID", task.getTaskID());
                    intent.putExtra("username", task.getRequester().getUsername());
                    mContext.startActivity(intent);
                }
            }
        });

        v.setTag(taskArrayList.get(position).getName());
        return v;
    }

    public void swapItems(ArrayList<Task> tasks) {
        this.taskArrayList = tasks;
        notifyDataSetChanged();
    }
}

