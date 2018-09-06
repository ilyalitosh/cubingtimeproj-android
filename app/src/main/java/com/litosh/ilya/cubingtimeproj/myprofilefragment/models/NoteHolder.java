package com.litosh.ilya.cubingtimeproj.myprofilefragment.models;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.transition.Fade;
import android.support.transition.TransitionInflater;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.litosh.ilya.ct_sdk.models.profile.Note;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.notemorefragment.ui.NoteMoreFragment;

public class NoteHolder extends RecyclerView.ViewHolder {

    private AppCompatImageView mUserIcon;
    private AppCompatTextView mUserName;
    private AppCompatTextView mText;
    private AppCompatTextView mDate;
    private AppCompatImageView mLikeButton;
    private AppCompatTextView mLikeNumber;
    private Context mContext;
    private Note mNote;

    public NoteHolder(View itemView, Context context, Note note) {
        super(itemView);
        mContext = context;
        mNote = note;
        mUserIcon = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_user_icon);
        mUserName = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_user_name);
        mText = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_text);
        mDate = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_date);
        mLikeButton = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_like_button);
        mLikeNumber = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_like_number);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mUserIcon.setTransitionName(getTransitionIconName());
            mUserName.setTransitionName(getTransitionUserNameName());
        }
        initializeListeners();
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

    private void initializeListeners() {
        itemView.setOnClickListener(v -> {
            FragmentTransaction ft =
                    ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction();
            NoteMoreFragment noteMoreFragment = new NoteMoreFragment();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Bundle bundle = new Bundle();
                bundle.putString("transition-user-icon", getTransitionIconName());
                bundle.putString("transition-user-name", getTransitionUserNameName());
                bundle.putString("note-user-name", mNote.getUserName());
                bundle.putString("note-user-icon-url", mNote.getUrlUserAvatar());
                noteMoreFragment.setArguments(bundle);
                ft.addSharedElement(mUserIcon, getTransitionIconName());
                ft.addSharedElement(mUserName, getTransitionUserNameName());
            }
            ft.addToBackStack("NoteMoreFragment");
            ft.replace(R.id.activity_base_fragment_container, noteMoreFragment);
            ft.commit();
        });
    }

    private String getTransitionIconName() {
        return "note_list_icon_transition_" + getAdapterPosition();
    }

    private String getTransitionUserNameName() {
        return "note_list_user_name_transition_" + getAdapterPosition();
    }

}
