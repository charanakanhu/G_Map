package com.kanhucharan.apimap.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kanhucharan.apimap.Model.ProfileModel;
import com.kanhucharan.apimap.Adapter.ProfileAdapter;
import com.kanhucharan.apimap.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private ProfileAdapter profileAdapter;
    private List<ProfileModel> profileModelList = new ArrayList<>();
    private RecyclerView recyclerView_viewall;
    private Dialog loadingDialog;
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");
        profileAdapter = new ProfileAdapter(profileModelList,getApplicationContext());
        recyclerView_viewall = findViewById(R.id.recycler_view);
        recyclerView_viewall.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView_viewall.setLayoutManager(mLayoutManager);
        recyclerView_viewall.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView_viewall.setItemAnimator(new DefaultItemAnimator());
        recyclerView_viewall.setAdapter(profileAdapter);

        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();

        profileGetData();
    }
    private void profileGetData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,"http://aryu.co.in/tracking/viewreport",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            loadingDialog.dismiss();
                            JSONObject obj = new JSONObject(response);
                            JSONArray heroArray = obj.getJSONArray("success");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                JSONObject heroObject = heroArray.getJSONObject(i);
                                ProfileModel hero = new ProfileModel(heroObject.getString("id"),
                                        heroObject.getString("name")
                                        ,heroObject.getString("address"),
                                        heroObject.getString("categoryid"),
                                        heroObject.getString("description"),
                                        heroObject.getString("contact"),
                                        heroObject.getString("empcode"));
                                profileModelList.add(hero);
                            }

                           /* //creating custom adapter object
                            ListMonth adapter = new ListMonth(heroList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);*/
                            profileAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loadingDialog.dismiss();
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}