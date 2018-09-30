package com.litosh.ilya.cubingtimeproj.notemoreactivity.models.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.litosh.ilya.ct_sdk.models.profile.Comment;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.notemoreactivity.models.CommentViewHolder;

import java.util.List;

/**
 * CommentsListAdapter
 * Адаптер списка с комментариями
 *
 * @author Ilya Litosh
 */
public class CommentsListAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Comment> mComments;

    public CommentsListAdapter(Context context, List<Comment> comments) {
        mLayoutInflater = LayoutInflater.from(context);
        mComments = comments;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(
                R.layout.activity_more_info_my_profile_wall_note_item_comments_list_comment_item,
                parent,
                false);
        return new CommentViewHolder(view, mLayoutInflater.getContext(), mComments.get(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = mComments.get(holder.getAdapterPosition());
        holder.getUserName().setText(comment.getUserName());
        Glide.with(mLayoutInflater.getContext())
                .applyDefaultRequestOptions(RequestOptions.fitCenterTransform().centerCrop().circleCrop())
                .load(Uri.parse(comment.getUrlUserAvatar()))
                .into(holder.getUserIcon());
        holder.getDate().setText(comment.getDate());
        holder.getText().setText(comment.getCommentText());
        int likesNumber = comment.getLikesNumber();
        holder.getLikeNumber().setText(likesNumber == 0 ? "" : String.valueOf(likesNumber));
        holder.getLikeImage().setBackgroundResource(comment.isLikedMe()
                ? R.drawable.ic_like_pressed : R.drawable.ic_like_unpressed);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }
}