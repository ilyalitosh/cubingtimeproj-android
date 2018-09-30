package com.litosh.ilya.cubingtimeproj.myprofileactivity.presenters;

import android.net.Uri;

import com.litosh.ilya.ct_sdk.models.profile.User;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.views.UserAvatarContainerView;
import com.squareup.picasso.Picasso;

/**
 * UserAvatarContainerPresenter
 *
 * @author Ilya Litosh
 */
public class UserAvatarContainerPresenter {

    private UserAvatarContainerView mUserAvatarContainerView;

    public UserAvatarContainerPresenter(UserAvatarContainerView userAvatarContainerView) {
        mUserAvatarContainerView = userAvatarContainerView;
    }

    /**
     * Устанавливает аватар в профилде пользователя
     *
     * @param user сущность User
     */
    public void setAvatar(User user) {
        if (!user.getUrlAvatar().contains("default")) {
            mUserAvatarContainerView
                    .setAvatar(Picasso.get().load(Uri.parse("https://" + user.getUrlAvatar())));
        }
    }

}
