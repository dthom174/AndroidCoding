package com.example.android.androidcoding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private UserProfileAdapter mAdapter;
    private static final int PROFILE_LOADER_ID = 1;
    private static final String USG_URL = "https://reqres.in/api/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        ListView listOfUser = (ListView) findViewById(R.id.profile_list);

        ArrayList<UserProfile> dummy = new ArrayList<>();
        dummy.add(new UserProfile("Dwayne", "Thomas", ""));
        dummy.add(new UserProfile("Dwayne", "Thomas", ""));
        dummy.add(new UserProfile("Dwayne", "Thomas", ""));
        dummy.add(new UserProfile("Dwayne", "Thomas", ""));
        dummy.add(new UserProfile("Dwayne", "Thomas", ""));
        dummy.add(new UserProfile("Dwayne", "Thomas", ""));

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        mAdapter = new UserProfileAdapter(this, dummy);
        listOfUser.setAdapter(mAdapter);
    }
}
