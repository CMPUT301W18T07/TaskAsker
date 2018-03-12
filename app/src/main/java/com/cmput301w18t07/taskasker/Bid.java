package com.cmput301w18t07.taskasker;

/**
 * Created by Thomas Mackay on 2018-03-12.
 */

public class Bid {
    private User bidder;
    private double bid;
    private Task task;

    public User getBidder() {
        return bidder;
    }

    public double getBid() {
        return bid;
    }

    public Task getTask() {
        return task;
    }
}
