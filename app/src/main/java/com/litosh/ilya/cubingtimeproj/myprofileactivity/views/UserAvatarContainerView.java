package com.litosh.ilya.cubingtimeproj.myprofileactivity.views;

import com.squareup.picasso.RequestCreator;

/**
 * UserAvatarContainerView
 *
 * @author Ilya Litosh
 */
public interface UserAvatarContainerView {

    /**
     * Вызывается при установки аватара юзера
     *
     * @param requestCreator requestCreator
     */
    void setAvatar(RequestCreator requestCreator);

}
