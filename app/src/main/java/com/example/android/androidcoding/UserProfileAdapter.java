package com.example.android.androidcoding;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
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
//        new DownloadImageTask(avatar).execute(current.getImageID());
        Picasso.with(getContext()).load(current.getImageUrl()).into(avatar);

        return listItem;
    }
}
