package com.litosh.ilya.cubingtimeproj.myprofilefragment.views;

/**
 * LikePostInProfileView
 *
 * @author Ilya Litosh
 */
public interface LikePostInProfileView {

    /**
     * Вызывается для изменения лайк-кнопки
     * как лайкнутой
     *
     */
    void changeLikeButtonAsLiked();

    /**
     * Вызывается для изменения лайк-кнопки
     * как не лайкнутой
     *
     */
    void changeLikeButtonAsNotLiked();

    /**
     * Вызывается для установки нового
     * кол-ва лайков
     *
     * @param likeNumber новое кол-во лайков
     */
    void changeLikeNumber(long likeNumber);

}
