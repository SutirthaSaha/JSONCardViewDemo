package com.example.dell.jsoncardviewdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> imageDataset=new ArrayList<String>();
    ArrayList<String> titleDataset=new ArrayList<String>();
    String url="https://simplifiedcoding.net/demos/view-flipper/heroes.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        loadRecyclerViewData();

    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String Sresponse) {
                Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_LONG).show();
                //String result = response.toString();
                progressDialog.dismiss();
                try {
                JSONObject response=new JSONObject(Sresponse);

                    JSONObject arrayObject;
                    JSONArray parentArray=response.getJSONArray("heroes");
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataset.add(arrayObject.getString("name"));
                        imageDataset.add(arrayObject.getString("imageurl"));
                        //result=result+arrayObject.getString("name")+":"+arrayObject.getString("imageurl")+"\n";
                    }
                    adapter=new MainAdapter(MainActivity.this,titleDataset,imageDataset);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Something Went Wrong",Toast.LENGTH_LONG).show();

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setValues() {
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_LONG).show();

                String result = response.toString();
                try {
                    JSONObject arrayObject;
                    JSONArray parentArray=response.getJSONArray("heroes");
                    for(int i=0;i<parentArray.length();i++){
                        arrayObject=parentArray.getJSONObject(i);
                        titleDataset.add(arrayObject.getString("name"));
                        imageDataset.add(arrayObject.getString("imageurl"));
                        //result=result+arrayObject.getString("name")+":"+arrayObject.getString("imageurl")+"\n";
                    }
                    adapter=new MainAdapter(MainActivity.this,titleDataset,imageDataset);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Something Went Wrong",Toast.LENGTH_LONG).show();

            }
        });
        MySingleton.getmInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);
    }


}
