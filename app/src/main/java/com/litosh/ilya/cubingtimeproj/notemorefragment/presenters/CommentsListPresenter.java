package com.litosh.ilya.cubingtimeproj.notemorefragment.presenters;

import com.litosh.ilya.ct_sdk.models.profile.Note;
import com.litosh.ilya.cubingtimeproj.notemorefragment.views.CommentsListView;

/**
 * CommentsListPresenter
 * Презентер для инициализации
 * списка комментариев
 *
 * @author Ilya Litosh
 */
public class CommentsListPresenter {

    private CommentsListView mCommentsListView;

    public CommentsListPresenter(CommentsListView commentsListView) {
        mCommentsListView = commentsListView;
    }

    public void initializeAdapter(Note note) {
        if (note.getComments().size() == 0) {
            mCommentsListView.showNoCommentsTitle();
        } else {
            mCommentsListView.hideNoCommentsTitle();
            mCommentsListView.initializeCommentsList(note.getComments());
        }
    }

}
