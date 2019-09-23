package com.example.android.androidcoding;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.app.LoaderManager.LoaderCallbacks;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  LoaderCallbacks<List<UserProfile>>{

    private UserProfileAdapter mAdapter;
    private static final int PROFILE_LOADER_ID = 1;
    private static final String USG_URL = "https://reqres.in/api/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        ListView listOfUser = (ListView) findViewById(R.id.profile_list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        mAdapter = new UserProfileAdapter(this, new ArrayList<UserProfile>());
        listOfUser.setAdapter(mAdapter);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(PROFILE_LOADER_ID,null,this);


    }

    //when the plus button is press create a new user
    public void userInfo(View view){
        Toast.makeText(getApplicationContext(),"Creating new user",Toast.LENGTH_SHORT).show();
        new CreateUserAsync().execute(USG_URL);
    }

    @Override
    public Loader<List<UserProfile>> onCreateLoader(int i, Bundle bundle) {

        return new UserProfileAsyncTask(this, USG_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<UserProfile>> loader, List<UserProfile> data) {
        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if(data != null && !data.isEmpty()){

            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<UserProfile>> loader) {
        mAdapter.clear();
    }
}
