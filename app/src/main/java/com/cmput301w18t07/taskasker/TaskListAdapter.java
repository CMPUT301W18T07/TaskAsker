/* Task List Adapter
 *
 * March 2018
 *
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
public class TaskListAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Task> taskArrayList;


    /**
     * Purpose:
     * Changes how a task is represented in a list view.
     *
     * @param mContext
     * @param taskArrayList
     */
    public TaskListAdapter(Context mContext, ArrayList<Task> taskArrayList) {
        this.mContext = mContext;
        this.taskArrayList = taskArrayList;
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
        View v = View.inflate(mContext, R.layout.task_list_item, null);
        TextView taskTitle = v.findViewById(R.id.textTitle);
        TextView taskStatus = v.findViewById(R.id.textStatus);

        taskTitle.setText(taskArrayList.get(position).getName());
        String status = taskArrayList.get(position).getStatus();
        taskStatus.setText(status);
        if (status.equals("Bidded")) {
            v.setBackgroundResource(R.color.bidded_background);
        }

        v.setTag(taskArrayList.get(position).getName());
        return v;
    }

    public void swapItems(ArrayList<Task> tasks) {
        this.taskArrayList = tasks;
        notifyDataSetChanged();
    }
}

