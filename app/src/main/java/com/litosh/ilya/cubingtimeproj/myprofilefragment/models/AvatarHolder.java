package com.litosh.ilya.cubingtimeproj.myprofilefragment.models;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.litosh.ilya.cubingtimeproj.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class AvatarHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView mProfileName;
    private RoundedImageView mAvatarContainer;
    private AppCompatTextView mActivity;

    public AvatarHolder(View itemView) {
        super(itemView);
        mProfileName = itemView.findViewById(R.id.activity_profile_profilename_title);
        mActivity = itemView.findViewById(R.id.activity_profile_activity_title);
        mAvatarContainer = itemView.findViewById(R.id.activity_profile_avatar_container);
    }

    public AppCompatTextView getProfileName() {
        return mProfileName;
    }

    public RoundedImageView getAvatarContainer() {
        return mAvatarContainer;
    }

    public AppCompatTextView getActivity() {
        return mActivity;
    }

}
