package com.litosh.ilya.cubingtimeproj.notemorefragment.ui;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.Fade;
import android.transition.TransitionInflater;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.litosh.ilya.cubingtimeproj.R;

public class NoteMoreFragment extends MvpAppCompatFragment {

    public NoteMoreFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(
                    TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.move));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile_wall_note_item_more, null);

        initializeComponents(view);
        initializeTransition();
        initializeListeners();

        return view;
    }

    private AppCompatImageView mUserIcon;
    private AppCompatTextView mUserName;
    private AppCompatTextView mText;
    private AppCompatTextView mDate;
    private AppCompatTextView mLikesNumber;
    private AppCompatImageView mLikeButton;
    private RecyclerView mCommentsList;
    private void initializeComponents(View view) {
        mUserIcon = view.findViewById(R.id.fragment_my_profile_wall_note_item_more_user_icon);
        mUserName = view.findViewById(R.id.fragment_my_profile_wall_note_item_more_user_name);
        mText = view.findViewById(R.id.fragment_my_profile_wall_note_item_more_text);
        mDate = view.findViewById(R.id.fragment_my_profile_wall_note_item_more_date);
        mLikesNumber = view.findViewById(R.id.fragment_my_profile_wall_note_item_more_like_number);
        mLikeButton = view.findViewById(R.id.fragment_my_profile_wall_note_item_more_like_button);
        mCommentsList = view.findViewById(R.id.fragment_my_profile_wall_note_item_more_comments_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && getArguments() != null) {
            mUserIcon.setTransitionName(getArguments().getString("transition-user-icon"));
            mUserName.setTransitionName(getArguments().getString("transition-user-name"));
        }
    }

    private void initializeTransition() {
        mUserName.setText(getArguments().getString("note-user-name"));
        Glide.with(getActivity())
                .load(Uri.parse(getArguments().getString("note-user-icon-url")))
                .into(mUserIcon);
    }

    private void initializeListeners() {

    }
}
