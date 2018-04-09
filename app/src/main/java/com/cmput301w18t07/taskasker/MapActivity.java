/*
 * Copyright (c) 2018 Brendan Bartok, Christopher Wood, Dylan Alcock, Lucas Gauk, Thomas Mackay,
 * Tyler Strembitsky, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Student Behaviour
 *  at University of Alberta. You can find a copy of the license on this project.
 */

package com.cmput301w18t07.taskasker;

import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Purpose:
 * Brings up a map to choose a location
 *
 * Design Rationale:
 * Needed to show a map.
 *
 * @author Brendan
 * @version 1.5
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_layout);
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        double[] location = getGPS();
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(location[0], location[1]);
        gMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private double[] getGPS(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);

        Location l = null;

        for(int i=providers.size()-1;i>=0;i--){
            l = locationManager.getLastKnownLocation(providers.get(i));
            if(l!=null){
                break;
            }
        }
        double[] gps = new double[2];
        if(l != null){
            gps[0] = l.getLatitude();
            gps[1] = l.getLongitude();
        }

        return gps;
    }
}
