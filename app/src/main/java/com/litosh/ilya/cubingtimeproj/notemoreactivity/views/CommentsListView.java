package com.litosh.ilya.cubingtimeproj.notemoreactivity.views;

import com.litosh.ilya.ct_sdk.models.profile.Comment;

import java.util.List;

/**
 * CommentsListView
 *
 * @author Ilya Litosh
 */
public interface CommentsListView {

    /**
     * Вызывается, чтобы показать надпись
     * свидетельстующей об отсутствии
     * комментариев
     *
     */
    void showNoCommentsTitle();

    /**
     * Вызывается, чтобы убрать надпись
     * свидетельстующей об отсутствии
     * комментариев
     *
     */
    void hideNoCommentsTitle();

    /**
     * Вызывается для инициализации адаптера
     * списка комментариев
     *
     * @param comments комментарии
     */
    void initializeCommentsList(List<Comment> comments);

}
