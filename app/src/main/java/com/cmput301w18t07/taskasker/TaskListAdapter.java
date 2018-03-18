package com.cmput301w18t07.taskasker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dylan's Compooter on 2018-03-17.
 */

public class TaskListAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Task> taskArrayList;


    /**
     * Purpose:
     *
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
        taskStatus.setText(taskArrayList.get(position).getStatus());

        v.setTag(taskArrayList.get(position).getName());
        return v;
    }
}

