package com.litosh.ilya.cubingtimeproj.authactivity.views;

import com.arellomobile.mvp.MvpView;

/**
 * SignInButtonView
 *
 * @author Ilya Litosh
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

    /**
     * Вызывается для скрытия формы авторизации
     *
     */
    void hideAuthorizationForm();

    /**
     * Вызывается для отображения формы авторизации
     *
     */
    void showAuthorizationForm();

}
