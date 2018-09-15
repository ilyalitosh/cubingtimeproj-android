package com.litosh.ilya.cubingtimeproj.notemorefragment.ui;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.litosh.ilya.ct_sdk.models.profile.Note;
import com.litosh.ilya.cubingtimeproj.R;

/**
 * NoteMoreActivity
 *
 * @author Ilya Litosh
 */
public class NoteMoreActivity extends AppCompatActivity {

    private Note mNote;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_profile_wall_note_item_more);

        initializeNoteEntity();

        initializeComponents();
    }

    private AppCompatTextView mUserName;
    private AppCompatImageView mUserIcon;
    private AppCompatTextView mDate;
    private AppCompatTextView mText;
    private AppCompatTextView mLikesNumber;
    private AppCompatTextView mCommentsNumber;
    private AppCompatImageView mLikeImage;
    private void initializeComponents() {
        mUserName = findViewById(R.id.fragment_my_profile_wall_note_item_more_user_name);
        mUserIcon = findViewById(R.id.fragment_my_profile_wall_note_item_more_user_icon);
        mDate = findViewById(R.id.fragment_my_profile_wall_note_item_more_date);
        mText = findViewById(R.id.fragment_my_profile_wall_note_item_more_text);
        mLikesNumber = findViewById(R.id.fragment_my_profile_wall_note_item_more_like_number);
        mCommentsNumber = findViewById(R.id.fragment_my_profile_wall_note_item_more_comments_number);
        mLikeImage = findViewById(R.id.fragment_my_profile_wall_note_item_more_like_image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mUserName.setTransitionName(getIntent().getExtras().getString("transition-user-name"));
            mUserIcon.setTransitionName(getIntent().getExtras().getString("transition-user-avatar"));
            mDate.setTransitionName(getIntent().getExtras().getString("transition-date"));
            mText.setTransitionName(getIntent().getExtras().getString("transition-text"));
            mLikesNumber.setTransitionName(getIntent().getExtras().getString("transition-likes-number"));
        }
        mUserName.setText(mNote.getUserName());
        Glide.with(this)
                .load(Uri.parse(mNote.getUrlUserAvatar()))
                .into(mUserIcon);
        mDate.setText(mNote.getDate());
        mText.setText(mNote.getText());
        mLikesNumber.setText(mNote.getLikesNumber() != 0 ? String.valueOf(mNote.getLikesNumber()) : "");
        mCommentsNumber.setText(mNote.getCommentsNumber() != 0 ? String.valueOf(mNote.getCommentsNumber()) : "");
        if (mNote.isLikedMe()) {
            mLikeImage.setBackgroundResource(R.drawable.ic_like_pressed);
        }
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
    }
}
