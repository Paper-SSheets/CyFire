package com.example.loginwithregisterpage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FeedPage extends AppCompatActivity {

    RequestQueue mQueue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_page);


        mQueue = Volley.newRequestQueue(this);

        // get users from server and displays them on screen
        getUsers();


    }


    /*
    Loads the users from the server into the array of type UserAccount users. Will then use this array to display users on screen.
     */
    public void getUsers() {
        String url = "http://cs309-vc-3.misc.iastate.edu:8080/users";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i < response.length(); ++i) {

                        JSONObject user = response.getJSONObject(i);
                        UserAccount newUser = new UserAccount();

                        // get information from the JSON object
                        newUser.setFirstName(user.getString("firstName"));
                        newUser.setLastName(user.getString("lastName"));
                        newUser.setPhoneNumber(user.getString("phoneNumber"));
                        newUser.setGender(user.getString("gender"));
                        newUser.setMajor(user.getString("major"));
                        newUser.setPassword(user.getString("userPassword"));
                        newUser.setClassification(user.getString("classification"));
                        newUser.setNetID(user.getString("netID"));
                        newUser.setAge(user.getString("age"));

                        displayUser(newUser);

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

    /*
    Takes in a user and displays in on the screen as a button
     */
    private void displayUser(final UserAccount userToDisplay) {

        LinearLayout layout = findViewById(R.id.linearLayout);
        layout.setOrientation(LinearLayout.VERTICAL);

        Button button = new Button(this);

        if (userToDisplay != null)
            button.setText(userToDisplay.getFirstName() + " " + userToDisplay.getLastName());

        else {
            Toast.makeText(FeedPage.this, "No info to display", Toast.LENGTH_SHORT).show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserToView(userToDisplay);
            }
        });

        layout.addView(button);
    }

    /*
    Opens when user clicks on another users button, sends name, major, class and netID
     */
    public void openUserToView(UserAccount user) {
        Intent i = new Intent(getApplicationContext(), userToView.class);

        i.putExtra("userName", user.getFirstName() + " " + user.getLastName());
        i.putExtra("userMajor", user.getMajor());
        i.putExtra("userClassification", user.getClassification());
        i.putExtra("userNetId", user.getNetID());

        startActivity(i);
    }

}