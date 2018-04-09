/*
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
 * Represents a bid object.
 *
 * Design Rationale:
 * Needed to represent a task with its title and status
 *
 * @author Dylan
 * @version 1.5
 * @see AcceptBidActivity
 * @see Bid
 */
public class BidListAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Bid> bidArrayList;


    /**
     * Purpose:
     * Changes how a bid is represented in a list view.
     *
     * @param mContext
     * @param bidArrayList
     */
    public BidListAdapter(Context mContext, ArrayList<Bid> bidArrayList) {
        this.mContext = mContext;
        this.bidArrayList = bidArrayList;
    }


    /**
     * Purpose:
     * gets the size of the task list
     *
     * @return int of how long the bid list is
     */
    @Override
    public int getCount() {
        return bidArrayList.size();
    }

    /**
     * Purpose:
     * Gets the position of an object in the list view
     *
     * @param position int of what position the bid is at
     * @return bid object at specified position
     */
    @Override
    public Object getItem(int position) {
        return bidArrayList.get(position);
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
     * Gets the view of bid list and add the new layout to it.
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

        taskTitle.setText(bidArrayList.get(position).getBidderUsername());
        Double amount = bidArrayList.get(position).getBid();
        taskStatus.setText("$" + String.format("%.2f", amount));

        v.setTag(bidArrayList.get(position).getBidderUsername());
        return v;
    }
}

