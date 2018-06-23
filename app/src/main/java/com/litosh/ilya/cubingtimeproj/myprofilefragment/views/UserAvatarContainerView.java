package com.litosh.ilya.cubingtimeproj.myprofilefragment.views;

import com.arellomobile.mvp.MvpView;
import com.squareup.picasso.RequestCreator;

/**
 * UserAvatarContainerView
 *
 * Created by ilya_ on 23.06.2018.
 */

public interface UserAvatarContainerView extends MvpView {

    /**
     * Вызывается при установки аватара юзера
     *
     * @param requestCreator requestCreator
     */
    void setAvatar(RequestCreator requestCreator);

}
