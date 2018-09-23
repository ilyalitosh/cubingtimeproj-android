package com.litosh.ilya.cubingtimeproj.myprofilefragment.models;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.litosh.ilya.ct_sdk.models.profile.User;
import com.litosh.ilya.cubingtimeproj.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.util.ArrayList;
import java.util.List;

public class AvatarHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView mProfileName;
    private RoundedImageView mAvatarContainer;
    private AppCompatTextView mActivity;
    private User mUser;
    private Context mContext;

    public AvatarHolder(View itemView, Context context, User user) {
        super(itemView);
        mProfileName = itemView.findViewById(R.id.activity_profile_profilename_title);
        mActivity = itemView.findViewById(R.id.activity_profile_activity_title);
        mAvatarContainer = itemView.findViewById(R.id.activity_profile_avatar_container);
        mUser = user;
        mContext = context;
        initializeListeners();
    }

    private void initializeListeners() {
        mAvatarContainer.setOnClickListener(v -> {
            List<String> imageUrls = new ArrayList<>();
            imageUrls.add(mUser.getUrlAvatar());
            new ImageViewer.Builder(mContext, imageUrls)
                    .setStartPosition(0)
                    .show();
        });
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
