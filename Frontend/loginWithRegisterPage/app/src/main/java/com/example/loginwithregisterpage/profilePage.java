package com.example.loginwithregisterpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class profilePage extends AppCompatActivity {
    String userEmailSelect = null;
    private RequestQueue mQueue;
    private TextView email;
    private TextView name;
    private TextView age;
    private TextView userPassword;
    private TextView likeSpace;
    private TextView matchSpace;
    private BottomNavigationView mProfileNav;       /* Bottom Nav Bar */
    private FrameLayout mProfileFrame;              /* Frame where nav bar is located */

    private HomeFragment homeFragment;              /* Private variables for fragments */
    private SettingsFragment settingsFragment;
    private MessagesFragment messagesFragment;
    private FeedFragment feedFragment;

    /**
     * Creates the profile page activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);     /* This layout is hosting
                                                               the nav bar */

        /* Creating the instances of the fragments */

        homeFragment = new HomeFragment();
        settingsFragment = new SettingsFragment();
        messagesFragment = new MessagesFragment();
        feedFragment = new FeedFragment();

        /* Creating instances of the layout and Nav Bar */

        mProfileFrame = (FrameLayout) findViewById(R.id.profFrame);
        mProfileNav = (BottomNavigationView) findViewById(R.id.b_nav);

        /* Gets the users email from the homepage */

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            userEmailSelect = extras.getString("userEmail");

        name = findViewById(R.id.nameText);
        email = findViewById(R.id.emailText);
        age = findViewById(R.id.ageText);
        userPassword = findViewById(R.id.passwordText);

        mQueue = Volley.newRequestQueue(this);

        getAndDisplayInfo();        /* Displays the current users info */
        setFragment(homeFragment);  /* Set the home fragment as the first one that opens */

        /* Select from the nav bar, which fragment you want displayed */

        mProfileNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_feed:
                        setFragment(feedFragment);
                        return true;
                    case R.id.nav_messages:
                        setFragment(messagesFragment);
                        return true;
                    case R.id.nav_settings:
                        setFragment(settingsFragment);
                        return true;
                }
                return false;
            }
        });
        userPassword = findViewById(R.id.passwordText);

        mQueue = Volley.newRequestQueue(this);

        //displays the current users info
        getAndDisplayInfo();
    }

    /**
     * Loads and replaces a fragment in place of another
     *
     * @param fragment
     */
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.profFrame, fragment);
        fragmentTransaction.commit();
    }


    /**
     * Makes post and get requests to VC_3:
     * http://cs309-vc-3.misc.iastate.edu:8080/users
     */
    private void getAndDisplayInfo() {
        String url = "http://cs309-vc-3.misc.iastate.edu:8080/users";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); ++i) {
                        JSONObject user = response.getJSONObject(i);
                        String firstName = user.getString("firstName");
                        MainActivity.currentUser.setFirstName(user.getString("firstName"));
                        String lastName = user.getString("lastName");
                        MainActivity.currentUser.setLastName(user.getString("lastName"));
                        String theAge = user.getString("age");
                        String mail = user.getString("netID");
                        MainActivity.currentUser.setNetID(user.getString("netID"));
                        String thePassword = user.getString("userPassword");

                        /* Logic that matches with the JSON array */
                        if (mail.equals(userEmailSelect)) {
                            name.append("Name: " + firstName + " " + lastName);
                            email.append("Email: " + mail);
                            age.append("Age: " + theAge);
                            userPassword.append("Password: " + thePassword);
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


    /*
    Opens the feed page
     */
    private void openFeedPage() {
        Intent i = new Intent(getApplicationContext(), FeedPage.class);
        startActivity(i);
    }
}


