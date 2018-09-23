package com.litosh.ilya.cubingtimeproj.myprofilefragment.models;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
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
import com.litosh.ilya.ct_sdk.models.profile.Note;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.ui.MyProfileActivity;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.presenters.LikePostInProfilePresenter;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.ui.MoreFeaturesListItemMenu;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.views.LikePostInProfileView;
import com.litosh.ilya.cubingtimeproj.notemorefragment.ui.NoteMoreActivity;

import java.util.ArrayList;

/**
 * NoteHolder
 *
 * @author Ilya Litosh
 */
public class NoteHolder extends RecyclerView.ViewHolder implements LikePostInProfileView {

    private Context mContext;
    private Note mNote;

    public NoteHolder(View itemView, Context context, Note note) {
        super(itemView);
        mContext = context;
        mNote = note;
        mLikePostInProfilePresenter = new LikePostInProfilePresenter(this);
        initializeComponents(itemView);
        initializeListeners();
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

    public AppCompatTextView getCommentsNumber() {
        return mCommentsNumber;
    }

    public LinearLayout getLikeButton() {
        return mLikeButton;
    }

    public RelativeLayout getPopupMenuButton() {
        return mPopupMenuButton;
    }

    public AppCompatTextView getShowAll() {
        return mShowAll;
    }

    private AppCompatImageView mUserIcon;
    private AppCompatTextView mUserName;
    private AppCompatTextView mText;
    private AppCompatTextView mDate;
    private AppCompatImageView mLikeImage;
    private AppCompatTextView mLikeNumber;
    private AppCompatTextView mCommentsNumber;
    private LinearLayout mLikeButton;
    private RelativeLayout mPopupMenuButton;
    private AppCompatTextView mShowAll;
    private void initializeComponents(View itemView) {
        mUserIcon = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_user_icon);
        mUserName = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_user_name);
        mText = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_text);
        mDate = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_date);
        mLikeImage = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_like_image);
        mLikeNumber = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_like_number);
        mCommentsNumber = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_comments_number);
        mLikeButton = itemView.findViewById(R.id.activity_more_info_my_profile_wall_note_item_comments_list_comment_item_like_button);
        mPopupMenuButton = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_popup_menu_button);
        mShowAll = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_show_all);
    }

    private LikePostInProfilePresenter mLikePostInProfilePresenter;
    @RestrictTo(RestrictTo.Scope.SUBCLASSES)
    private void initializeListeners() {
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, NoteMoreActivity.class);
            addDataToIntent(intent);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Pair<View, String> userNamePair = Pair.create(getUserName(), getTransitionUserNameName());
                Pair<View, String> userIconPair = Pair.create(getUserIcon(), getTransitionIconName());
                Pair<View, String> datePair = Pair.create(getDate(), getTransitionDate());
                Pair<View, String> textPair = Pair.create(getText(), getTransitionText());
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (AppCompatActivity) mContext, userIconPair, userNamePair, datePair, textPair);
                ((AppCompatActivity) mContext).startActivityForResult(intent, MyProfileActivity.REQUEST_CODE_LIKES_CHANGES, options.toBundle());
            } else {
                ((AppCompatActivity) mContext).startActivityForResult(intent, MyProfileActivity.REQUEST_CODE_LIKES_CHANGES);
            }
        });
        initSpringListenerWithLikeButton();
        mPopupMenuButton.setOnClickListener(v -> {
            MoreFeaturesListItemMenu moreFeaturesListItemMenu = new MoreFeaturesListItemMenu(mContext, v);
            moreFeaturesListItemMenu.show();
        });
    }

    private void initSpringListenerWithLikeButton() {
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
        getLikeButton().setOnClickListener(v -> {
            spring.setCurrentValue(1);
            spring.setEndValue(0);
            mLikePostInProfilePresenter.like(mNote);
        });
    }

    private void addDataToIntent(Intent intent) {
        intent.putExtra("transition-user-name", getTransitionUserNameName());
        intent.putExtra("transition-user-avatar", getTransitionIconName());
        intent.putExtra("transition-date", getTransitionDate());
        intent.putExtra("transition-text", getTransitionText());

        intent.putExtra("note-id", mNote.getNoteId());
        intent.putExtra("note-user-name", mNote.getUserName());
        intent.putExtra("note-user-url-avatar", mNote.getUrlUserAvatar());
        intent.putExtra("note-date", mNote.getDate());
        intent.putExtra("note-likes-number", mNote.getLikesNumber());
        intent.putExtra("note-text", mNote.getText());
        intent.putExtra("note-userid", mNote.getUserId());
        intent.putExtra("note-is-user-online", mNote.isUserOnline());
        intent.putExtra("note-comments-number", mNote.getCommentsNumber());
        intent.putExtra("note-is-liked-me", mNote.isLikedMe());
        intent.putExtra("note-comments", (ArrayList) mNote.getComments());
        intent.putExtra("note-position-in-list", getAdapterPosition() - ProfileListData.NUMBER_STATIC_ITEMS);
    }

    public String getTransitionIconName() {
        return "note_list_icon_transition_" + getAdapterPosition();
    }

    public String getTransitionUserNameName() {
        return "note_list_user_name_transition_" + getAdapterPosition();
    }

    public String getTransitionDate() {
        return "note_list_date_transition_" + getAdapterPosition();
    }

    public String getTransitionText() {
        return "note_list_text_transition_" + getAdapterPosition();
    }

    public void setNote(Note mNote) {
        this.mNote = mNote;
    }

    @Override
    public void changeLikeButtonAsLiked() {
        getLikeImage().setBackgroundResource(R.drawable.ic_like_pressed);
    }

    @Override
    public void changeLikeButtonAsNotLiked() {
        getLikeImage().setBackgroundResource(R.drawable.ic_like_unpressed);
    }

    @Override
    public void changeLikeNumber(long likeNumber) {
        getLikeNumber().setText(likeNumber == 0 ? "" : String.valueOf(likeNumber));
    }
}
