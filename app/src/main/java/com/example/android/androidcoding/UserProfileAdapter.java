package com.example.android.androidcoding;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserProfileAdapter extends ArrayAdapter<UserProfile> {


    public UserProfileAdapter(Activity context, ArrayList<UserProfile> userProfile) {
        super(context, 0, userProfile);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        if(listItem == null){

            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        UserProfile current = getItem(position);

        TextView userName = (TextView) listItem.findViewById(R.id.full_name);
        ImageView avatar = (ImageView) listItem.findViewById(R.id.avatar);

        userName.setText(current.getFullName());


        return listItem;
    }
}
