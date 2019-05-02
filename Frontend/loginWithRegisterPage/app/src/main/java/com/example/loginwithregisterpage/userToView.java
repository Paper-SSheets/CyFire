package com.example.loginwithregisterpage;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class userToView extends AppCompatActivity {

    String name = null;
    String major = null;
    String classification = null;

    UserAccount displayedUser = new UserAccount();
    boolean liked = false;
    boolean likedBack = false;
    boolean matched = false;

    private TextView nameView;
    private TextView majorView;
    private TextView classView;
    private Button likeButton;
    private TextView likeSpace;
    private RequestQueue mQueue;
    private String testString = "This is to push to test CICD";

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_to_view);
        mQueue = Volley.newRequestQueue(this);
        builder = new AlertDialog.Builder(userToView.this);

        Bundle extras = getIntent().getExtras();

        getExtras(extras);
        initScreen();
        displayInfo();
        getLikes(MainActivity.currentUser);
        getLikes(displayedUser);

        getMatches(MainActivity.currentUser);
        getMatches(displayedUser);

        initLikesMatchesDisplay();



        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeThisUser(displayedUser.getNetID());

                // update the info because it has changed due to the user liking this user
                initLikesMatchesDisplay();

            }
        });

        // displays whether or not the user has liked the user they are currently viewing
        displayRelation(findIfLiked(), findIfMatched());

    }


    /*
    Initializes the textviews and button on screen
     */
    private void initScreen()
    {
        nameView = findViewById(R.id.nameSpace);
        majorView = findViewById(R.id.majorSpace);
        classView = findViewById(R.id.classificationSpace);
        likeButton = findViewById(R.id.theLikeButton);
        likeSpace = findViewById(R.id.likedSpace);
    }

    /*
    Grabs data about the use being viewed
     */
    private void getExtras(Bundle extras)
    {
        if (extras != null)
        {
            name = extras.getString("userName");
            major = extras.getString("userMajor");
            classification = extras.getString("userClassification");
            displayedUser.setNetID(extras.getString("userNetId"));
        }

    }

    /*
    Puts the information of the user being viewed on screen
     */
    private void displayInfo()
    {
        nameView.append(name);
        majorView.append(major);
        classView.append(classification);
    }

    /*
    Sends the netID of the person that has been liked to the server where it will be added to the list of people the current user has liked
     */
    private void likeThisUser(String netId)
    {
        // append the netId of the user on this page to the current users likes

        String url;

        if(MainActivity.currentUser.getLikes().equals(""))
            url = "http://cs309-vc-3.misc.iastate.edu:8080/likes/new";
        else
            url = "http://cs309-vc-3.misc.iastate.edu:8080/likes/" + MainActivity.currentUser.getNetID();

        JSONObject newLike = new JSONObject();
        try {
            newLike.put("net_id", MainActivity.currentUser.getNetID());
            newLike.put("likes", netId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, newLike,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Response :" + response);
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
    Gets the likes for the given user and sets it as their string of likes.
     */
    private void getLikes(final UserAccount user)
    {
        String url = "http://cs309-vc-3.misc.iastate.edu:8080/likes/" + user.getNetID();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    user.setLikes(response.getString("likes"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError e) {
                e.printStackTrace();
            }

        });

        mQueue.add(request);

    }

    /*
    Get request that checks the users likes to see if they have liked the person they are viewing
     */
    private boolean findIfLiked()
    {
        getLikes(MainActivity.currentUser);


        String[] likes = MainActivity.currentUser.getLikes().split(",");

        for(int i = 0; i < likes.length; i++)
        {
            if (likes[i].equals(displayedUser.getNetID())) {
                liked = true;
                return true;
            }

        }
        return false;
    }


    /*

     */
    private boolean findIfLikedBack()
    {
        getLikes(displayedUser);

        String[] likes = displayedUser.getLikes().split(",");

        for(int i = 0; i < likes.length; i++)
        {
            if(likes[i].equals(MainActivity.currentUser.getNetID()))
            {
                likedBack = true;
                return true;
            }
        }

        return false;
    }

    private void getMatches(final UserAccount user)
    {
        String url =  "http://cs309-vc-3.misc.iastate.edu:8080/matches/" + user.getNetID();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    user.setMatches(response.getString("matches"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError e) {
                e.printStackTrace();
            }

        });

        mQueue.add(request);


    }

    private boolean findIfMatched()
    {
        if((findIfLiked() || liked ) && ( likedBack || findIfLikedBack())) {
            matched = true;
            return true;
        }
        return false;
    }

    /*
    Checks each users string of likes to see if they have liked eachother
     */
    private void updateMatches(UserAccount user1, UserAccount user2)
    {
        getLikes(user1);
        getLikes(user2);

        String[] likes1 = user1.getLikes().split(",");
        String[] likes2 = user2.getLikes().split(",");

        for(int i = 0; i < likes1.length; i++) {

            // if user1 likes user2
            if (likes1[i].equals(user2.getNetID()))
            {

                for (int j = 0; j < likes2.length; j++) {

                    //if user2 likes user1
                    if (likes2[j].equals(user1.getNetID())) {
                        sendMatch(user1, user2);
                        sendMatch(user2, user1);
                        break;
                    }
                }
                break;
            }
        }
    }


    /*
    Displays whether user has liked the user they are currently viewing.
     */
    private void displayRelation(boolean liked, boolean matched)
    {
        if(matched)
            likeSpace.setText("You are matched with this user!");
        if(liked)
            likeSpace.setText("You have liked this user!");
        else
            likeSpace.setText("You have not liked this user.");
    }

    /*

    Takes in two user objects and appends the second one's netId to the first ones match field.
     */
    private void sendMatch(UserAccount user1, UserAccount user2)
    {
        String url = "http://cs309-vc-3.misc.iastate.edu:8080/matches";

        if(user1.getMatches().equals(""))
            url += "/new";
        else
            url += "/" +user1.getNetID();

        JSONObject newMatch = new JSONObject();
        try{
            newMatch.put("net_id", user1.getNetID());
            newMatch.put("match", user2.getNetID());
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, newMatch,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Response: " + response);
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
    Does all the needed stuff, makes it easier to update.
     */
    private void initLikesMatchesDisplay()
    {

        //get matches for current user
        getMatches(MainActivity.currentUser);
        getMatches(displayedUser);

        //get likes for the users
        getLikes(MainActivity.currentUser);
        getLikes(displayedUser);

        findIfLiked();
        findIfLikedBack();
        findIfMatched();

        updateMatches(MainActivity.currentUser, displayedUser);

        //display the relation
        displayRelation(liked, (matched || findIfMatched()));
    }

}
