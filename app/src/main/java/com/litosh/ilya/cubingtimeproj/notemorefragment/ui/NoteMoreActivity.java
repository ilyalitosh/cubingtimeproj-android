package com.litosh.ilya.cubingtimeproj.notemorefragment.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.litosh.ilya.ct_sdk.models.profile.Comment;
import com.litosh.ilya.ct_sdk.models.profile.Note;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.presenters.LikePostInProfilePresenter;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.views.LikePostInProfileView;
import com.litosh.ilya.cubingtimeproj.notemorefragment.models.adapters.CommentsListAdapter;
import com.litosh.ilya.cubingtimeproj.notemorefragment.presenters.CommentsListPresenter;
import com.litosh.ilya.cubingtimeproj.notemorefragment.views.CommentsListView;

import java.util.ArrayList;
import java.util.List;

/**
 * NoteMoreActivity
 *
 * @author Ilya Litosh
 */
public class NoteMoreActivity extends AppCompatActivity implements CommentsListView, LikePostInProfileView {

    private CommentsListPresenter mCommentsListPresenter;
    private LikePostInProfilePresenter mLikePostInProfilePresenter;
    private Note mNote;
    private int positionInList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_my_profile_wall_note_item);

        initializeNoteEntity();

        initializePresenters();

        initializeComponents();
        initializeListeners();
    }

    private AppCompatTextView mUserName;
    private AppCompatImageView mUserIcon;
    private AppCompatTextView mDate;
    private AppCompatTextView mText;
    private AppCompatTextView mLikesNumber;
    private AppCompatTextView mCommentsNumber;
    private AppCompatImageView mLikeImage;
    private LinearLayout mLikeButton;
    private AppCompatTextView mNoCommentsTitle;
    private RecyclerView mCommentsList;
    private void initializeComponents() {
        mUserName = findViewById(R.id.activity_more_info_my_profile_wall_note_item_user_name);
        mUserIcon = findViewById(R.id.activity_more_info_my_profile_wall_note_item_user_icon);
        mDate = findViewById(R.id.activity_more_info_my_profile_wall_note_item_date);
        mText = findViewById(R.id.activity_more_info_my_profile_wall_note_item_more_text);
        mLikesNumber = findViewById(R.id.activity_more_info_my_profile_wall_note_item_like_number);
        mCommentsNumber = findViewById(R.id.activity_more_info_my_profile_wall_note_item_comments_number);
        mLikeImage = findViewById(R.id.activity_more_info_my_profile_wall_note_item_like_image);
        mLikeButton = findViewById(R.id.activity_more_info_my_profile_wall_note_item_like_button);
        mNoCommentsTitle = findViewById(R.id.activity_more_info_my_profile_wall_note_item_no_comments_title);
        mCommentsList = findViewById(R.id.activity_more_info_my_profile_wall_note_item_comments_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mUserName.setTransitionName(getIntent().getExtras().getString("transition-user-name"));
            mUserIcon.setTransitionName(getIntent().getExtras().getString("transition-user-avatar"));
            mDate.setTransitionName(getIntent().getExtras().getString("transition-date"));
            mText.setTransitionName(getIntent().getExtras().getString("transition-text"));
            mLikesNumber.setTransitionName(getIntent().getExtras().getString("transition-likes-number"));
        }
        mUserName.setText(mNote.getUserName());
        Glide.with(this)
                .applyDefaultRequestOptions(RequestOptions.fitCenterTransform().centerCrop().circleCrop())
                .load(Uri.parse(mNote.getUrlUserAvatar()))
                .into(mUserIcon);
        mDate.setText(mNote.getDate());
        mText.setText(mNote.getText());
        mLikesNumber.setText(mNote.getLikesNumber() != 0 ? String.valueOf(mNote.getLikesNumber()) : "");
        mCommentsNumber.setText(mNote.getCommentsNumber() != 0 ? String.valueOf(mNote.getCommentsNumber()) : "");
        if (mNote.isLikedMe()) {
            mLikeImage.setBackgroundResource(R.drawable.ic_like_pressed);
        }
        mCommentsListPresenter.initializeAdapter(mNote);
    }

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
                mLikeImage.setScaleX(scale);
                mLikeImage.setScaleY(scale);
            }
        });
        mLikeButton.setOnClickListener(v -> {
            spring.setCurrentValue(1);
            spring.setEndValue(0);
            mLikePostInProfilePresenter.like(mNote);
        });
    }

    private void initializePresenters() {
        mLikePostInProfilePresenter = new LikePostInProfilePresenter(this);
        mCommentsListPresenter = new CommentsListPresenter(this);
    }

    private void initializeNoteEntity() {
        mNote = new Note();
        mNote.setNoteId(getIntent().getExtras().getLong("note-id"));
        mNote.setUserName(getIntent().getExtras().getString("note-user-name"));
        mNote.setUrlUserAvatar(getIntent().getExtras().getString("note-user-url-avatar"));
        mNote.setDate(getIntent().getExtras().getString("note-date"));
        mNote.setLikesNumber(getIntent().getExtras().getInt("note-likes-number"));
        mNote.setText(getIntent().getExtras().getString("note-text"));
        mNote.setUserId(getIntent().getExtras().getString("note-userid"));
        mNote.setUserOnline(getIntent().getExtras().getBoolean("note-is-user-online"));
        mNote.setCommentsNumber(getIntent().getExtras().getInt("note-comments-number"));
        mNote.setLikedMe(getIntent().getExtras().getBoolean("note-is-liked-me"));
        mNote.setComments((ArrayList<Comment>) getIntent().getExtras().getSerializable("note-comments"));
        positionInList = getIntent().getExtras().getInt("note-position-in-list");
    }

    @Override
    public void showNoCommentsTitle() {
        mNoCommentsTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoCommentsTitle() {
        mNoCommentsTitle.setVisibility(View.GONE);
    }

    private CommentsListAdapter mCommentsListAdapter;
    @Override
    public void initializeCommentsList(List<Comment> comments) {
        mCommentsListAdapter = new CommentsListAdapter(this, comments);
        mCommentsList.setLayoutManager(new LinearLayoutManager(this));
        mCommentsList.setAdapter(mCommentsListAdapter);
        mCommentsList.getAdapter().notifyDataSetChanged();
        mCommentsList.scheduleLayoutAnimation();
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
        mLikesNumber.setText(likeNumber == 0 ? "" : String.valueOf(likeNumber));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("note-data", mNote);
        intent.putExtra("note-position", positionInList);

        setResult(Activity.RESULT_OK, intent);

        super.onBackPressed();
    }
}
