package com.litosh.ilya.cubingtimeproj.notemorefragment.models;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.litosh.ilya.ct_sdk.models.profile.Comment;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.ui.MoreFeaturesListItemMenu;
import com.litosh.ilya.cubingtimeproj.notemorefragment.presenters.LikeCommentPresenter;
import com.litosh.ilya.cubingtimeproj.notemorefragment.views.LikeCommentView;

/**
 * CommentViewHolder
 *
 * @author Ilya Litosh
 */
public class CommentViewHolder extends RecyclerView.ViewHolder implements LikeCommentView {

    private Context mContext;
    private Comment mComment;

    public CommentViewHolder(View itemView, Context context, Comment comment) {
        super(itemView);
        mContext = context;
        mComment = comment;
        mLikeCommentPresenter = new LikeCommentPresenter(this);
        initializeComponents();
        initializeListeners();
    }

    private AppCompatImageView mUserIcon;
    private AppCompatTextView mUserName;
    private AppCompatTextView mText;
    private AppCompatTextView mDate;
    private AppCompatImageView mLikeImage;
    private AppCompatTextView mLikeNumber;
    private LinearLayout mLikeButton;
    private RelativeLayout mPopupMenuButton;
    private void initializeComponents() {
        mUserIcon = itemView.findViewById(
                R.id.activity_more_info_my_profile_wall_note_item_comments_list_comment_item_user_icon);
        mUserName = itemView.findViewById(
                R.id.activity_more_info_my_profile_wall_note_item_comments_list_comment_item_user_name);
        mText = itemView.findViewById(
                R.id.activity_more_info_my_profile_wall_note_item_comments_list_comment_item_text);
        mDate = itemView.findViewById(
                R.id.activity_more_info_my_profile_wall_note_item_comments_list_comment_item_date);
        mLikeImage = itemView.findViewById(
                R.id.activity_more_info_my_profile_wall_note_item_comments_list_comment_item_like_image);
        mLikeNumber = itemView.findViewById(
                R.id.activity_more_info_my_profile_wall_note_item_comments_list_comment_item_like_number);
        mLikeButton = itemView.findViewById(
                R.id.activity_more_info_my_profile_wall_note_item_comments_list_comment_item_like_button);
        mPopupMenuButton = itemView.findViewById(
                R.id.activity_more_info_my_profile_wall_note_item_comments_list_comment_item_popup_menu_button);
    }

    private LikeCommentPresenter mLikeCommentPresenter;
    private void initializeListeners() {
        SpringSystem springSystem = SpringSystem.create();
        SpringConfig springConfig = new SpringConfig(400, 10);
        Spring spring = springSystem.createSpring();
        spring.setSpringConfig(springConfig);
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float) spring.getCurrentValue();
                float scale = 1f - (value * 0.5f);
                getLikeImage().setScaleX(scale);
                getLikeImage().setScaleY(scale);
            }
        });
        mLikeButton.setOnClickListener(v -> {
            spring.setCurrentValue(1);
            spring.setEndValue(0);
            mLikeCommentPresenter.like(mComment);
        });
        mPopupMenuButton.setOnClickListener(v -> {
            MoreFeaturesListItemMenu moreFeaturesListItemMenu = new MoreFeaturesListItemMenu(mContext, v);
            moreFeaturesListItemMenu.show();
        });
    }

    public AppCompatImageView getUserIcon() {
        return mUserIcon;
    }

    public AppCompatTextView getUserName() {
        return mUserName;
    }

    public AppCompatTextView getText() {
        return mText;
    }

    public AppCompatTextView getDate() {
        return mDate;
    }

    public AppCompatImageView getLikeImage() {
        return mLikeImage;
    }

    public AppCompatTextView getLikeNumber() {
        return mLikeNumber;
    }

    public LinearLayout getLikeButton() {
        return mLikeButton;
    }

    @Override
    public void changeLikeButtonAsLiked() {
        mLikeImage.setBackgroundResource(R.drawable.ic_like_pressed);
    }

    @Override
    public void changeLikeButtonAsNotLiked() {
        mLikeImage.setBackgroundResource(R.drawable.ic_like_unpressed);
    }

    @Override
    public void changeLikeNumber(long likeNumber) {
        mLikeNumber.setText(likeNumber == 0 ? "" : String.valueOf(likeNumber));
    }
}
