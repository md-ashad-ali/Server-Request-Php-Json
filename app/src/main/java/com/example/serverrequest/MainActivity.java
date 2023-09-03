package com.example.serverrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView php_textview,json_textview,nameTV,adressTV,collageTV,ageTV;
    Button button;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Find_ID();




        //==============================================================
        //php mathodi...
        //==============================================================


        RequestQueue queue_php = Volley.newRequestQueue(MainActivity.this);
        String url_php = "https://outstandingacademy.000webhostapp.com/Server/userlist.php";
        StringRequest stringRequest_php = new StringRequest(Request.Method.GET, url_php, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                php_textview.append(""+response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                php_textview.setText(""+error);

            }
        });


        queue_php.add(stringRequest_php);

        //==============================================================
        //php mathodi...
        //==============================================================



        //==============================================================
        //json mathodi...
        //==============================================================


        RequestQueue queue2 = Volley.newRequestQueue(MainActivity.this);
        String url2 = "https://outstandingacademy.000webhostapp.com/Server/new.json";
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String name = jsonObject.getString("name");
                    String adress = jsonObject.getString("adress");
                    String colage = jsonObject.getString("colage");
                    String age = jsonObject.getString("age");

                    json_textview.append(""+name+"\n"+adress+"\n"+colage+"\n"+age);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                json_textview.setText(""+error);

            }
        });


        queue2.add(stringRequest2);


        //==============================================================
        //json mathodi...
        //==============================================================

        //==============================================================
        //jsonArray
        //==============================================================


      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              progressBar.setVisibility(View.VISIBLE);


              RequestQueue queue3 = Volley.newRequestQueue(MainActivity.this);
              String url3 = "https://outstandingacademy.000webhostapp.com/Server/user.json";

              JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url3, null, new Response.Listener<JSONArray>() {
                  @Override
                  public void onResponse(JSONArray response) {

                      for(int i=0; i<response.length(); i++)
                      {
                          try {
                              progressBar.setVisibility(View.GONE);

                              JSONObject jsonObject = response.getJSONObject(i);

                              String name = jsonObject.getString("name");
                              String adress = jsonObject.getString("adress");
                              String collage = jsonObject.getString("colage");
                              String age = jsonObject.getString("age");

                              nameTV.setText(name);
                              adressTV.setText(adress);
                              collageTV.setText(collage);
                              ageTV.setText(age);


                          } catch (JSONException e) {
                              throw new RuntimeException(e);
                          }

                      }


                  }
              }, new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError error) {
                      progressBar.setVisibility(View.GONE);
                      nameTV.setText(""+error);
                  }
              });

              queue3.add(jsonArrayRequest);



          }
      });


        //==============================================================
        //jsonArray
        //==============================================================




    }

    private void Find_ID() {
        php_textview = findViewById(R.id.textview);
        json_textview = findViewById(R.id.jsonTv);
        nameTV = findViewById(R.id.name);
        adressTV = findViewById(R.id.adress);
        collageTV = findViewById(R.id.collage);
        ageTV = findViewById(R.id.age);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);

    }


}