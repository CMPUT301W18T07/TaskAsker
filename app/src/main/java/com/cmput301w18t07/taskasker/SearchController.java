/* Search Controller
 *
 * March 2018
 *
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by lucasgauk on 2018-03-12.
 * Ask me questions on slack for now, ill fill in more comments later.
 * Please do not modify, I barely have a grasp of it right now and I wrote it all
 */

public class SearchController {
    private String url;
    private Gson gson;

    public SearchController(String url){
        this.gson = new Gson();
        this.url = url;
    }

    /* SEARCH CONTROLLER METHODS */

    /**
     * Put a task into the elasticsearch cloud.
     * @param task
     */
    public void saveTask(Task task){
        String stringTask = gson.toJson(task);
        this.postRequest(this.url+"/task/",stringTask);
    }

    /**
     * Return a list of all tasks requested
     * by a user with a specific username
     * @param username
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getTaskByRequester(String username){
        String response = this.getRequest(this.url+"/task/"+"_search?q=requesterUsername:"+username);
        ArrayList<Task> taskList = new ArrayList<Task>();
        try{
            JSONObject reader = new JSONObject(response);
            JSONObject hits = reader.getJSONObject("hits");
            JSONArray hitArray = hits.getJSONArray("hits");
            for(int i = 0; i < hitArray.length(); i++) {
                JSONObject jsonTask = hitArray.getJSONObject(i);
                String jsonString = jsonTask.getJSONObject("_source").toString();
                taskList.add(gson.fromJson(jsonString,Task.class));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Returns a list of bids that a user with Username
     * has bid on.
     *
     * @param username
     * @return ArrayList of Task objects
     */
    public ArrayList<Task> getTaskByBidder(String username){
        String response = this.getRequest(this.url+"/task/"+"_search?q=bidderUsername:"+username);
        ArrayList<Task> taskList = new ArrayList<Task>();
        try{
            JSONObject reader = new JSONObject(response);
            JSONObject hits = reader.getJSONObject("hits");
            JSONArray hitArray = hits.getJSONArray("hits");
            for(int i = 0; i < hitArray.length(); i++) {
                JSONObject jsonTask = hitArray.getJSONObject(i);
                String jsonString = jsonTask.getJSONObject("_source").toString();
                taskList.add(gson.fromJson(jsonString,Task.class));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return taskList;
    }


    /**
     * Return a list of all tasks requested by a user
     * that also match a status.
     * @param username
     * @param status
     * @return
     */
    public ArrayList<Task> getTaskByRequester(String username, String status){
        String response = this.getRequest(this.url+"/task/"+"_search?q=requesterUsername:"+username);
        ArrayList<Task> taskList = new ArrayList();
        try{
            JSONObject reader = new JSONObject(response);
            JSONObject hits = reader.getJSONObject("hits");
            JSONArray hitArray = hits.getJSONArray("hits");
            for(int i = 0; i < hitArray.length(); i++) {
                JSONObject jsonTask = hitArray.getJSONObject(i);
                String jsonString = jsonTask.getJSONObject("_source").toString();
                taskList.add(gson.fromJson(jsonString,Task.class));
            }
            for(int i=0; i<taskList.size(); i++) {
                if (!taskList.get(i).getStatus().equals(status)) {
                    taskList.remove(taskList.get(i));
                    i--;
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Get all tasks that a user with a specific username
     * has taken.
     * @param username
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getTaskByTaker(String username){
        String response = this.getRequest(this.url+"/task/"+"_search?q=takerUsername:"+username);
        ArrayList<Task> taskList = new ArrayList<Task>();
        try{
            JSONObject reader = new JSONObject(response);
            JSONObject hits = reader.getJSONObject("hits");
            JSONArray hitArray = hits.getJSONArray("hits");
            for(int i = 0; i < hitArray.length(); i++){
                JSONObject jsonTask = hitArray.getJSONObject(i);
                String jsonString = jsonTask.getJSONObject("_source").toString();
                taskList.add(gson.fromJson(jsonString,Task.class));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Return all tasks that havent been assigned
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getOpenTasks(){
        String query = "{\"query\":{\"bool\":{\"must_not\":{\"match\":{\"status\":\"Assigned\"}}}}}";
        String response = this.getRequest(this.url+"/task/"+"_search",query);
        ArrayList<Task> taskList = new ArrayList<Task>();
        try{
            JSONObject reader = new JSONObject(response);
            JSONObject hits = reader.getJSONObject("hits");
            JSONArray hitArray = hits.getJSONArray("hits");
            for(int i = 0; i < hitArray.length(); i++){
                JSONObject jsonTask = hitArray.getJSONObject(i);
                String jsonString = jsonTask.getJSONObject("_source").toString();
                taskList.add(gson.fromJson(jsonString,Task.class));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Return all tasks that havent been assigned that
     * match one of the keywords in the name or description
     *
     * @param search
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getOpenTasks(String search){
        String query = "{\"query\":{\"bool\":{\"must_not\":{\"match\":{\"status\":\"Assigned\"}}}}}";
        String response = this.getRequest(this.url+"/task/"+"_search",query);
        ArrayList<Task> taskList = new ArrayList<Task>();
        try{
            JSONObject reader = new JSONObject(response);
            JSONObject hits = reader.getJSONObject("hits");
            JSONArray hitArray = hits.getJSONArray("hits");
            for(int i = 0; i < hitArray.length(); i++){
                JSONObject jsonTask = hitArray.getJSONObject(i);
                String jsonString = jsonTask.getJSONObject("_source").toString();
                taskList.add(gson.fromJson(jsonString,Task.class));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        String[] searches = search.trim().split("\\s+");
        for(int i = 0; i < taskList.size(); i++){
            Boolean taskContains = false;
            Task task = taskList.get(i);
            for(int j = 0; j < searches.length && taskContains == false; j++){
                if(task.getName().toLowerCase().contains(searches[j].toLowerCase())){
                    taskContains = true;
                }
                if(task.getDescription().toLowerCase().contains(searches[j].toLowerCase())){
                    taskContains = true;
                }
            }
            if(taskContains == false){
                taskList.remove(i);
                i--;
            }
        }
        return taskList;
    }

    /**
     * Put a new user into the elasticsearch cloud.
     * @param user
     */
    public void saveUser(User user){
        String stringUser = gson.toJson(user);
        this.postRequest(this.url+"/user/",stringUser);
    }

    /**
     * Push updated task to elasticsearch
     * @param task
     */
    public void updateTask(Task task){
        String query = "{\"query\":{\"match\":{\"taskID\":\""+Integer.toString(task.getTaskID())+"\"}}}";
        this.deleteRequest(this.url+"/task/_query",query);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.saveTask(task);
    }

    /**
     * Return a user that matches the username. <br>
     * Remember that usernames should be unique, we will need to
     * check that we arent adding two users with the same username. There may
     * exist more than one user with this username in the system, this function will only
     * return ONE. All bets are off as to which one.
     * @param name
     * @return User
     */
    public User getUserByUsername(String name){
        String response = this.getRequest(this.url+"/user/"+"_search?q=username:"+name);
        User responseUser = null;
        try {
            JSONObject reader = new JSONObject(response);
            JSONObject hits = reader.getJSONObject("hits");
            JSONArray hitArray = hits.getJSONArray("hits");
            if(hitArray.length() > 0) {
                JSONObject jsonUser = hitArray.getJSONObject(0);
                String jsonString = jsonUser.getJSONObject("_source").toString();
                responseUser = gson.fromJson(jsonString,User.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseUser;
    }

    /**
     * Return all tasks that are within a maxDistance of a location
     * (that are open)
     * @param location
     * @param maxDistance
     * @return
     */
    public ArrayList<Task> getTasksByLocation(Location location, int maxDistance){
        String query = "{\"query\":{\"bool\":{\"must_not\":{\"match\":{\"status\":\"Assigned\"}}}}}";
        String response = this.getRequest(this.url+"/task/"+"_search",query);
        ArrayList<Task> taskList = new ArrayList<Task>();
        try{
            JSONObject reader = new JSONObject(response);
            JSONObject hits = reader.getJSONObject("hits");
            JSONArray hitArray = hits.getJSONArray("hits");
            for(int i = 0; i < hitArray.length(); i++){
                JSONObject jsonTask = hitArray.getJSONObject(i);
                String jsonString = jsonTask.getJSONObject("_source").toString();
                taskList.add(gson.fromJson(jsonString,Task.class));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        for(int i = 0; i < taskList.size(); i++){
            try {
                if (taskList.get(i).getLocation() != null) {
                    if (location.distanceTo(taskList.get(i).getLocation()) > maxDistance) {
                        taskList.remove(i);
                        i--;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return taskList;
    }

    /**
     * Return a task with a matching Task ID. <br>
     * Remember that there should only be ONE task with a certain TaskID
     * this method will only return 1 Task even if multiple tasks are found
     * @param taskId
     * @return Task
     */
    public Task getTaskById(int taskId){
        String response = this.getRequest(this.url+"/task/"+"_search?q=taskID:"+Integer.toString(taskId));
        Task responseTask = null;
        try {
            JSONObject reader = new JSONObject(response);
            JSONObject hits = reader.getJSONObject("hits");
            JSONArray hitArray = hits.getJSONArray("hits");
            if(hitArray.length() > 0) {
                JSONObject jsonTask = hitArray.getJSONObject(0);
                String jsonString = jsonTask.getJSONObject("_source").toString();
                responseTask = gson.fromJson(jsonString,Task.class);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return responseTask;
    }

    /**
     * Returns the value of what the next task ID should be.
     * @return maxID
     */
    public int getMaxTaskId(){
        String response = this.getRequest(this.url+"/task/"+"_search?q=*");
        int max = 0;
        int temp;
        try {
            JSONObject reader = new JSONObject(response);
            JSONObject hits = reader.getJSONObject("hits");
            JSONArray hitArray = hits.getJSONArray("hits");
            for(int i = 0; i < hitArray.length(); i++){
                JSONObject hit = hitArray.getJSONObject(i).getJSONObject("_source");
                Log.i("HIT ------------------>",hit.toString());
                temp = Integer.parseInt(hit.get("taskID").toString());
                if (temp > max){
                    max = temp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return max + 1;
    }

    /**
     * Clear all users from the system.
     */
    public void deleteAllUsers(){
        this.deleteRequest(this.url+"/user",null);
    }

    /**
     * Delete all tasks from the system.
     */
    public void deleteAllTasks() {
        this.deleteRequest(this.url+"/task/",null);
    }

    /**
     * Delete a user that matches the username.
     * @param name
     */
    public void deleteUserByUsername(String name){
        String query = "{\"query\":{\"match\":{\"username\":\""+name+"\"}}}";
        this.deleteRequest(this.url+"/user/_query",query);
    }

    /**
     * Delete a task that has a matching taskID.
     * @param userID
     */
    public void deleteTaskById(int userID){
        String query = "{\"query\":{\"match\":{\"taskID\":\""+Integer.toString(userID)+"\"}}}";
        this.deleteRequest(this.url+"/task/_query",query);
    }


    /* SEARCH CONTROLLER HELPER CLASSES & METHODS*/

    private void postRequest(String url, String json){
        String[] request = {url,"POST",json};
        try {
            new RequestController().execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void deleteRequest(String url, String json){
        String[] request = {url,"DELETE",json};
        try {
            new RequestController().execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getRequest(String url){
        String[] request = {url,"GET",""};
        String response = null;
        try {
            response = new RequestController().execute(request).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    private String getRequest(String url,String query){
        String[] request = {url,"GET",query};
        String response = null;
        try {
            response = new RequestController().execute(request).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /*
    Class to help facilitate HTTP requests.
     */
    private class RequestController extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }
        @Override
        // surl convention 0: url 1: method 2: json object
        protected String doInBackground(String... surl){
            // Set URL
            URL url = null;
            try {
                url = new URL(surl[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            // Create new connection
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Set request method (POST, PUT, GET, DELETE) surl[1]
            try {
                connection.setRequestMethod(surl[1]);
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // Try connecting
            try {
                connection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // If we are making a post request
            if(surl[2] != null) {
                OutputStreamWriter out = null;
                try {
                    out = new OutputStreamWriter(
                            connection.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Write json if necessary
                if (out != null) {
                    try {
                        out.write(surl[2]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            // Create input reader
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Read response from server and return full response
            String content = "", line;
            if (in != null) {
                try {
                    while ((line = in.readLine()) != null) {
                        content += line + "\n";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return content;
        }
        @Override
        protected void onPostExecute(String result) {
        }
    }
}
