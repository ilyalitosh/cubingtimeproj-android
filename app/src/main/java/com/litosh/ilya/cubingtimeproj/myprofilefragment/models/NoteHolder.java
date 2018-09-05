package com.litosh.ilya.cubingtimeproj.myprofilefragment.models;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.litosh.ilya.cubingtimeproj.R;

public class NoteHolder extends RecyclerView.ViewHolder {

    private AppCompatImageView mUserIcon;
    private AppCompatTextView mUserName;
    private AppCompatTextView mText;
    private AppCompatTextView mDate;
    private AppCompatImageView mLikeButton;
    private AppCompatTextView mLikeNumber;

    public NoteHolder(View itemView) {
        super(itemView);
        mUserIcon = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_user_icon);
        mUserName = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_user_name);
        mText = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_text);
        mDate = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_date);
        mLikeButton = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_like_button);
        mLikeNumber = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_like_number);
    }

    public AppCompatImageView getUserIcon() {
        return mUserIcon;
    }

    public AppCompatTextView getUserName() {
        return mUserName;
    }

    public AppCompatTextView getText() {
        return mText;
    }

    public AppCompatTextView getDate() {
        return mDate;
    }

    public AppCompatImageView getLikeButton() {
        return mLikeButton;
    }

    public AppCompatTextView getLikeNumber() {
        return mLikeNumber;
    }
}
