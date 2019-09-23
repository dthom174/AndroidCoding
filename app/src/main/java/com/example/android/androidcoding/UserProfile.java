package com.example.android.androidcoding;

public class UserProfile {

    private String mFirstName;
    private String mLastName;
    private String mImageUrl;

    public UserProfile(String firstName, String lastName, String imageUrl){

        mFirstName = firstName;
        mLastName = lastName;
        mImageUrl = imageUrl;
    }

    public String getImageUrl(){ return mImageUrl;}

    public String getFullName() {return mFirstName + " " + mLastName;}
}
