package com.example.loginwithregisterpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//Volley Imports
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;


//JSON imports
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class MainActivity extends AppCompatActivity {

    RequestQueue mQueue;
    EditText emailEditText;
    EditText passwordEditText;
    Boolean verified = false;


    public static UserAccount currentUser = new UserAccount();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button regButton = findViewById(R.id.regBtn);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        mQueue = Volley.newRequestQueue(this);

    openProfilePage();
    }

    public void openRegisterPage()
    {
        Intent openIt = new Intent(this, registerPage.class);
        startActivity(openIt);
    }

    public void openProfilePage()
    {
        Intent i = new Intent(getApplicationContext(), profilePage.class);

        // send the users email to the home page
        i.putExtra("userEmail", emailEditText.getText().toString());

        // opens the profile if the account has been verified
        //if (verified) {

        if (!verified) {

            startActivity(i);
            emailEditText.setText("");
            passwordEditText.setText("");
        }
        else if (!verified){
            Toast.makeText(MainActivity.this, "Invalid Login Information", Toast.LENGTH_SHORT).show();
        }
    }


    // gets info and uses the email and password entered by the user to check if  account exists
    private void getInfoAndVerifyAccount() {
        String url = "http://cs309-vc-3.misc.iastate.edu:8080/users";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); ++i) {
                        JSONObject user = response.getJSONObject(i);

                        //grab email and password because that is all we need to check the account
                        String mail = user.getString("netID");
                        String thePassword = user.getString("userPassword");




                        // check if email and password match
                        if(mail.equals(emailEditText.getText().toString()) && thePassword.equals(passwordEditText.getText().toString())){
                            verified = true;
                            currentUser.setFirstName(user.getString("firstName"));
                            currentUser.setLastName(user.getString("lastName"));
                            currentUser.setPhoneNumber(user.getString("phoneNumber"));
                            currentUser.setGender(user.getString("gender"));
                            currentUser.setMajor(user.getString("major"));
                            currentUser.setPassword(user.getString("userPassword"));
                            currentUser.setClassification(user.getString("classification"));
                            currentUser.setNetID(user.getString("netID"));
                            currentUser.setAge(user.getString("age"));
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}
