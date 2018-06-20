package com.litosh.ilya.cubingtimeproj.authactivity.views;

import com.arellomobile.mvp.MvpView;

/**
 * SignInButtonView
 *
 * Created by ilya_ on 19.06.2018.
 */

public interface SignInButtonView extends MvpView {

    /**
     * Вызывается в случае успеха авторизации
     * и запускает активность с профайлом пользователя
     *
     */
    void startProfileActivity();

    /**
     * Вызывается для отображения тост-сообщения в
     * случае неудачи авотризации
     *
     * @param message сообщение
     */
    void showSignInButtonToast(String message);

    /**
     * Вызывается для отображения ProgressDialog
     *
     */
    void showProgressDialog();

    /**
     * Вызывается для закрытия ProgressDialog
     *
     */
    void hideProgressDialog();

}
