package com.litosh.ilya.cubingtimeproj.globalmodels;

/**
 * LikeView
 * Базовый интерфейс Like
 *
 * @author Ilya Litosh
 */
public interface LikeView {

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
