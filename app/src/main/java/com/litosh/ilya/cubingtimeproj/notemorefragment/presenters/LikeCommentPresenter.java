package com.litosh.ilya.cubingtimeproj.notemorefragment.presenters;

import android.util.Log;

import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.ct_sdk.callbacks.OnLikePostCallback;
import com.litosh.ilya.ct_sdk.models.Like;
import com.litosh.ilya.ct_sdk.models.profile.Comment;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;
import com.litosh.ilya.cubingtimeproj.notemorefragment.views.LikeCommentView;

/**
 * LikeCommentPresenter
 *
 * @author Ilay Litosh
 */
public class LikeCommentPresenter {

    private static final String LOG_TAG = "LikeCommentPresenter";
    private LikeCommentView mLikeCommentView;

    public LikeCommentPresenter(LikeCommentView likeCommentView) {
        mLikeCommentView = likeCommentView;
    }

    private boolean isLikeAvailable = true;
    public void like(Comment comment) {
        if (isLikeAvailable) {
            doLike(comment);
            isLikeAvailable = false;
        }
    }

    private void doLike(Comment comment) {
        int likesNumber = comment.getLikesNumber();
        boolean isLikedMe = comment.isLikedMe();
        ApiService.likePost(
                new UserCookie(),
                String.valueOf(comment.getCommentId()),
                Like.Type.COMMENT_LIKE,
                isLikedMe ? Like.Act.REMOVE_LIKE : Like.Act.PUT_LIKE,
                new OnLikePostCallback() {
                    @Override
                    public void onLike() {
                        if (isLikedMe) {
                            int newLikesNumber = likesNumber - 1;
                            comment.setLikesNumber(newLikesNumber);
                            comment.setLikedMe(false);
                            mLikeCommentView.changeLikeButtonAsNotLiked();
                            mLikeCommentView.changeLikeNumber(newLikesNumber);
                        } else {
                            int newLikesNumber = likesNumber + 1;
                            comment.setLikesNumber(newLikesNumber);
                            comment.setLikedMe(true);
                            mLikeCommentView.changeLikeButtonAsLiked();
                            mLikeCommentView.changeLikeNumber(newLikesNumber);
                        }
                        isLikeAvailable = true;
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.i(LOG_TAG, t.toString());
                        isLikeAvailable = true;
                    }
                });
    }

}
