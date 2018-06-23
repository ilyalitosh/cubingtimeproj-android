package com.litosh.ilya.cubingtimeproj.myprofilefragment.presenters;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.litosh.ilya.ct_sdk.models.User;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.views.UserAvatarContainerView;
import com.squareup.picasso.Picasso;

/**
 * UserAvatarContainerPresenter
 *
 * Created by ilya_ on 23.06.2018.
 */

@InjectViewState
public class UserAvatarContainerPresenter extends MvpPresenter<UserAvatarContainerView> {

    public void setAvatar(User user) {
        if (!user.getUrlAvatar().contains("default")) {
            getViewState()
                    .setAvatar(Picasso.get().load(Uri.parse("https://" + user.getUrlAvatar())));
        }
    }

}
