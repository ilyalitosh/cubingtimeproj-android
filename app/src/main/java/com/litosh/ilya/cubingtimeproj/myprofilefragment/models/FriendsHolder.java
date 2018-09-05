package com.litosh.ilya.cubingtimeproj.myprofilefragment.models;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.litosh.ilya.cubingtimeproj.R;

public class FriendsHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView mFriends;

    public FriendsHolder(View itemView) {
        super(itemView);
        mFriends = itemView.findViewById(R.id.activity_profile_user_info_friends_value);
    }

    public AppCompatTextView getFriends() {
        return mFriends;
    }
}
