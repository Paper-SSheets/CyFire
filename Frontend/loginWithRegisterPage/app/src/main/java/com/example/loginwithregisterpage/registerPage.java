package com.example.loginwithregisterpage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registerPage extends AppCompatActivity {

    //private RequestQueue mQueue;
    private EditText firstName, lastName, phoneNumber, gender, major, password, classification, netID, age;
    private Button theRegisterButton;
    AlertDialog.Builder builder;

    String server_url = "http://cs309-vc-3.misc.iastate.edu:8080/users/new";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        // gets edits texts and button from the screen
        initScreenStuff();

        builder = new AlertDialog.Builder(registerPage.this);

        theRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject newUser = new JSONObject();

                loadData(newUser);

                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, server_url, newUser,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                builder.setTitle("Server Response");
                                builder.setMessage("Response :" + response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        firstName.setText("");
                                        lastName.setText("");
                                        phoneNumber.setText("");
                                        gender.setText("");
                                        major.setText("");
                                        classification.setText("");
                                        netID.setText("");
                                        password.setText("");
                                        age.setText("");
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                    }
                });
                MySingleton.getInstance(registerPage.this).addToRequestQueue(stringRequest);
                openCodeConfirmationPage();
            }


        });



    }

    private void initScreenStuff()
    {
        // get edit texts and buttons from the screen
        firstName = findViewById(R.id.firstNameSpace);
        lastName = findViewById(R.id.lastNameSpace);
        phoneNumber = findViewById(R.id.numberSpace);
        gender = findViewById(R.id.genderSpace);
        major = findViewById(R.id.majorSpace);
        password = findViewById(R.id.thePasswordText);
        classification = findViewById(R.id.classificationSpace);
        netID = findViewById(R.id.netIdSpace);
        age = findViewById(R.id.ageSpace);
        theRegisterButton = findViewById(R.id.registerButton);
    }

    private void loadData(JSONObject toSend)
    {
        try {
            toSend.put("first_name", firstName.getText().toString());
            toSend.put("last_name", lastName.getText().toString());
            toSend.put("phone_number", phoneNumber.getText().toString());
            toSend.put("gender", gender.getText().toString());
            toSend.put("major", major.getText().toString());
            toSend.put("user_password", password.getText().toString());
            toSend.put("classification", classification.getText().toString());
            toSend.put("net_id", netID.getText().toString().toLowerCase() + "@iastate.edu");
            toSend.put("age", age.getText().toString());
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void openCodeConfirmationPage() {

        Intent i = new Intent(getApplicationContext(), codeConfirmationPage.class);
        startActivity(i);
    }

}
