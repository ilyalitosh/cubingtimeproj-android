package com.litosh.ilya.cubingtimeproj.myprofileactivity.presenters;

import android.util.Log;

import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.ct_sdk.callbacks.OnLikePostCallback;
import com.litosh.ilya.ct_sdk.models.Like;
import com.litosh.ilya.ct_sdk.models.profile.Note;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.views.LikePostInProfileView;

/**
 * LikePostInProfilePresenter
 *
 * @author Ilya Litosh
 */
public class LikePostInProfilePresenter {

    private static final String LOG_TAG = "LikePostInProfilePrsntr";
    private LikePostInProfileView mLikePostInProfileView;

    public LikePostInProfilePresenter(LikePostInProfileView likePostInProfileView) {
        mLikePostInProfileView = likePostInProfileView;
    }

    private boolean isLikeAvailable = true;
    public void like(Note note) {
        if (isLikeAvailable) {
            doLike(note);
            isLikeAvailable = false;
        }
    }

    private void doLike(Note note) {
        int likesNumber = note.getLikesNumber();
        boolean isLikedMe = note.isLikedMe();
        ApiService.likePost(
                new UserCookie(),
                String.valueOf(note.getNoteId()),
                Like.Type.POST_LIKE,
                isLikedMe ? Like.Act.REMOVE_LIKE : Like.Act.PUT_LIKE,
                new OnLikePostCallback() {
                    @Override
                    public void onLike() {
                        if (isLikedMe) {
                            int newLikesNumber = likesNumber - 1;
                            note.setLikesNumber(newLikesNumber);
                            note.setLikedMe(false);
                            mLikePostInProfileView.changeLikeButtonAsNotLiked();
                            mLikePostInProfileView.changeLikeNumber(newLikesNumber);
                        } else {
                            int newLikesNumber = likesNumber + 1;
                            note.setLikesNumber(newLikesNumber);
                            note.setLikedMe(true);
                            mLikePostInProfileView.changeLikeButtonAsLiked();
                            mLikePostInProfileView.changeLikeNumber(newLikesNumber);
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
