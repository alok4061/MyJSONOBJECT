package com.project.alok4.myjsonobject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
String url= "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=28.5806201,77.3150729&radius=500&types=restaurant" +
        "&sensor=false&key=AIzaSyBYim7RY_8oUxXfBxnJCb0jjgPCDrFr790";
RequestQueue requestQueue;
    ListView listView;
    ArrayList arrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        listView= (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("results");
                    for (int i= 0;i<jsonArray.length();i++){
                        JSONObject obj= jsonArray.getJSONObject(i);
                        String name= obj.getString("name");
                        arrayList.add(name);

                      //  Toast.makeText(MainActivity.this," "+name,Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this," "+volleyError,Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
