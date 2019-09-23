package com.example.android.androidcoding;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class UserProfileAsyncTask extends AsyncTaskLoader<List<UserProfile>> {

    private String mUrl;

    public UserProfileAsyncTask(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }

    @Override
    public List<UserProfile> loadInBackground() {

        if(mUrl == null){

            return null;
        }

        // Perform the network request, parse the response, and extract a list of profiles.
        List<UserProfile> result = ProfileUtils.fetchData(mUrl);
        return result;
    }
}
