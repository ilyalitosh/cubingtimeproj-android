package com.litosh.ilya.cubingtimeproj.myprofilefragment.models.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.litosh.ilya.ct_sdk.models.profile.Wall;
import com.litosh.ilya.cubingtimeproj.R;
import com.squareup.picasso.Picasso;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NoteHolder> {

    private LayoutInflater mLayoutInflater;
    private Wall mWall;

    public NotesListAdapter(Context context, Wall wall) {
        mLayoutInflater = LayoutInflater.from(context);
        mWall = wall;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(
                R.layout.fragment_my_profile_wall_note_item, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        initializeUserName(holder);
        initializeUserIcon(holder);
        initializeText(holder);
        initializeDate(holder);
        initializeLikesButton(holder);
        initializeLikesNumber(holder);
    }

    private void initializeUserName(NoteHolder holder) {
        holder.mUserName.setText(mWall.get(holder.getAdapterPosition()).getUserName());
    }

    private void initializeText(NoteHolder holder) {
        holder.mText.setText(mWall.get(holder.getAdapterPosition()).getText());
    }

    private void initializeDate(NoteHolder holder) {
        holder.mDate.setText(mWall.get(holder.getAdapterPosition()).getDate());
    }

    private void initializeUserIcon(NoteHolder holder) {
        Picasso.get()
                .load(Uri.parse(mWall.get(holder.getAdapterPosition()).getUrlUserAvatar()))
                .into(holder.mUserIcon);
    }

    private void initializeLikesButton(NoteHolder holder) {
        int likesNumber = mWall.get(holder.getAdapterPosition()).getLikesNumber();
        if (likesNumber > 0) {
            holder.mUserIcon.setBackgroundResource(R.drawable.ic_like_pressed);
        }
    }

    private void initializeLikesNumber(NoteHolder holder) {
        int likesNumber = mWall.get(holder.getAdapterPosition()).getLikesNumber();
        if (likesNumber > 0) {
            holder.mLikeNumber.setText(
                    String.valueOf(mWall.get(holder.getAdapterPosition()).getLikesNumber()));
        } else {
            holder.mLikeNumber.setText("");
        }
    }

    @Override
    public int getItemCount() {
        if (mWall != null) {
            return mWall.size();
        } else {
            return 0;
        }
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        AppCompatImageView mUserIcon;
        AppCompatTextView mUserName;
        AppCompatTextView mText;
        AppCompatTextView mDate;
        AppCompatImageView mLikeButton;
        AppCompatTextView mLikeNumber;

        NoteHolder(View itemView) {
            super(itemView);
            mUserIcon = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_user_icon);
            mUserName = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_user_name);
            mText = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_text);
            mDate = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_date);
            mLikeButton = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_like_button);
            mLikeNumber = itemView.findViewById(R.id.fragment_my_profile_wall_note_item_like_number);
        }
    }

}
