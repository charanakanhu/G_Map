package com.kanhucharan.apimap.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kanhucharan.apimap.R;
import com.kanhucharan.apimap.CheckInternet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    String country;
    private LinearLayout home_btn,profile_btn,info_btn;
    private static final int ACCESS_FINE_LOCATION_CODE = 120;
    private static final String TAG = MapsActivity.class.getSimpleName();
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        home_btn=findViewById(R.id.home_btn);
        profile_btn=findViewById(R.id.profile_btn);
        info_btn=findViewById(R.id.info_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MapsActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MapsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MapsActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (!CheckInternet.isConnectedToInternet(this)) {
            Toast.makeText(this, "Please connect to internet!", Toast.LENGTH_LONG).show();
        }

        if (!CheckInternet.isLocationServiceEnabled(this)) {
            Toast.makeText(this, "Please enable location services!", Toast.LENGTH_LONG).show();
        }
        boolean permissionGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;

        if (permissionGranted) {
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case ACCESS_FINE_LOCATION_CODE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "onRequestPermissionsResult fine location granted now...");
                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        getJsonLocation();
    }
    private void getJsonLocation() {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        //LatLng sydney = new LatLng(-33.852, 151.211);
       /* googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        StringRequest stringRequest = new StringRequest(Request.Method.GET,"http://aryu.co.in/tracking/viewreport",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray heroArray = obj.getJSONArray("location");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                googleMap.addMarker(new MarkerOptions()
                                        .title(heroObject.getString("id"))
                                        .snippet("Lat:" + heroObject.getString("lat") + ",Lng:" + heroObject.getString("longg")
                                        +"\n"+"code :"+heroObject.getString("code")
                                        +"\n"+"created_at:"+heroObject.getString("created_at"))
                                        .position(new LatLng(
                                                Float.parseFloat(heroObject.getString("lat")),
                                                Float.parseFloat(heroObject.getString("longg"))
                                        ))
                                );
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(
                                        Float.parseFloat(heroObject.getString("lat")),
                                        Float.parseFloat(heroObject.getString("longg")))));

                            }
                            int durationMs = 3 * 1000;
                            googleMap.animateCamera(CameraUpdateFactory.zoomTo(3), durationMs, null);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
