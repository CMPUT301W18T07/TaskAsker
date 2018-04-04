/* Bid
 *
 * March 2018
 *
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

/**
 * Purpose:
 * Represents a bid object.
 *
 * Design Rationale:
 * Needed to represent a bid placed on a task
 *
 * @author Chris
 * @version 1.5
 * @see Task
 */
public class Bid {
    private User bidder;
    private double bid;

    /**
     * Purpose:
     * Constructor for a User
     *
     * @param bidder User who made the bid
     * @param bid Double for the value of the bid
     */
    public Bid(User bidder, double bid){
        this.bidder = bidder;
        this.bid = bid;
    }

    /**
     * Purpose:
     * Gets the bidder of the bid
     *
     * @return User of the bid
     */
    public User getBidder() {
        return bidder;
    }

    /**
     * Purpose:
     * Gets the monetary value of the bid
     *
     * @return double of the bid amount
     */
    public double getBid() {
        return bid;
    }

    /**
     * Purpose:
     * Sets the value of the bid
     *
     * @param bid New value of the bid
     */
    public void setBid(double bid){
        this.bid = bid;
    }
}
