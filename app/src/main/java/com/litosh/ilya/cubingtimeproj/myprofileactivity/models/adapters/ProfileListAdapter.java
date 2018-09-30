package com.litosh.ilya.cubingtimeproj.myprofileactivity.models.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.litosh.ilya.ct_sdk.models.profile.Note;
import com.litosh.ilya.ct_sdk.models.profile.User;
import com.litosh.ilya.ct_sdk.models.profile.Wall;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.models.AvatarHolder;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.models.FriendsHolder;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.models.NoteHolder;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.models.ProfileListData;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.models.UserInfoHolder;

import java.io.Serializable;

/**
 * ProfileListAdapter
 *
 * @author Ilya Litosh
 */
public class ProfileListAdapter extends RecyclerView.Adapter {

    private LayoutInflater mLayoutInflater;
    private Wall mWall;
    private User mUser;

    public ProfileListAdapter(Context context, Wall wall, User user) {
        mLayoutInflater = LayoutInflater.from(context);
        mWall = wall;
        mUser = user;
    }

    public Wall getWall() {
        return mWall;
    }

    public User getUser() {
        return mUser;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == ProfileListData.AVATAR_ITEM) {
            view = mLayoutInflater.inflate(
                    R.layout.activity_my_profile_avatar_item, parent, false);
            return new AvatarHolder(view, mLayoutInflater.getContext(), mUser);
        } else if (viewType == ProfileListData.USER_INFO_ITEM) {
            view = mLayoutInflater.inflate(
                    R.layout.activity_my_profile_user_info, parent, false);
            return new UserInfoHolder(view);
        } else if (viewType == ProfileListData.FRIENDS_ITEM) {
            view = mLayoutInflater.inflate(
                    R.layout.activity_my_profile_friends, parent, false);
            return new FriendsHolder(view);
        } else {
            view = mLayoutInflater.inflate(
                    R.layout.activity_my_profile_wall_note_item, parent, false);
            return new NoteHolder(view, mLayoutInflater.getContext(), mWall.get(viewType - ProfileListData.NUMBER_STATIC_ITEMS));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NoteHolder) {
            initializeNoteItem(
                    (NoteHolder) holder,
                    holder.getAdapterPosition() - ProfileListData.NUMBER_STATIC_ITEMS);
        } else if (holder instanceof AvatarHolder) {
            initializeAvatarItem((AvatarHolder) holder);
        } else if (holder instanceof UserInfoHolder) {
            initializeUserInfoItem((UserInfoHolder) holder);
        } else if (holder instanceof FriendsHolder) {
            initializeFriendsItem((FriendsHolder) holder);
        }
    }

    /**
     * Обновление профайл-айтема в списке
     *
     * @param note сущность Note
     * @param position позиция в списке
     */
    public void updateItem(Note note, int position) {
        mWall.set(position, note);
        notifyItemChanged(position + ProfileListData.NUMBER_STATIC_ITEMS);
    }

    private void initializeNoteItem(NoteHolder holder, int position) {
        // Update Note
        holder.setNote(mWall.get(position));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.getUserIcon().setTransitionName(holder.getTransitionIconName());
            holder.getUserName().setTransitionName(holder.getTransitionUserNameName());
            holder.getDate().setTransitionName(holder.getTransitionDate());
            holder.getText().setTransitionName(holder.getTransitionText());
        }

        holder.getUserName().setText(mWall.get(position).getUserName());

        String text = mWall.get(position).getText();
        holder.getText().setText(text.length() > 100 ? getStringOver100Chars(text) : text);
        if (text.length() > 100) {
            holder.getShowAll().setVisibility(View.VISIBLE);
        } else {
            holder.getShowAll().setVisibility(View.GONE);
        }

        holder.getDate().setText(mWall.get(position).getDate());

        Glide.with(mLayoutInflater.getContext())
                .applyDefaultRequestOptions(RequestOptions.fitCenterTransform().centerCrop().circleCrop())
                .load(Uri.parse(mWall.get(position).getUrlUserAvatar()))
                .into(holder.getUserIcon());

        if (mWall.get(position).isLikedMe()) {
            holder.getLikeImage().setBackgroundResource(R.drawable.ic_like_pressed);
        } else {
            holder.getLikeImage().setBackgroundResource(R.drawable.ic_like_unpressed);
        }

        int likesNumber = mWall.get(position).getLikesNumber();
        if (likesNumber > 0) {
            holder.getLikeNumber().setText(
                    String.valueOf(likesNumber));
        } else {
            holder.getLikeNumber().setText("");
        }

        int commentsNumber = mWall.get(position).getCommentsNumber();
        holder.getCommentsNumber().setText(commentsNumber > 0 ? String.valueOf(commentsNumber) : "");

        holder.itemView.setAnimation(
                AnimationUtils.loadAnimation(mLayoutInflater.getContext(), R.anim.anim_show_itemlist_down_to_up));
    }

    private String getStringOver100Chars(String s) {
        return s.substring(0, 100);
    }

    private void initializeAvatarItem(AvatarHolder holder) {
        holder.getActivity().setHint(mUser.getActivity());

        holder.getProfileName().setHint(mUser.getProfileName());

        if (!mUser.getUrlAvatar().contains("default")) {
            Glide.with(mLayoutInflater.getContext())
                    .load(Uri.parse(mUser.getUrlAvatar()))
                    .into(holder.getAvatarContainer());
        }

        holder.itemView.setAnimation(
                AnimationUtils.loadAnimation(mLayoutInflater.getContext(), R.anim.anim_show_itemlist_down_to_up));
    }

    private void initializeUserInfoItem(UserInfoHolder holder) {
        holder.getCity().setText(mUser.getCity());

        holder.getCountry().setText(mUser.getCountry());

        holder.getSex().setText(mUser.getSex());

        holder.getWca().setText(mUser.getWca());

        holder.itemView.setAnimation(
                AnimationUtils.loadAnimation(mLayoutInflater.getContext(), R.anim.anim_show_itemlist_down_to_up));
    }

    private void initializeFriendsItem(FriendsHolder holder) {
        holder.getFriends().setText(mUser.getFriendsCount());

        holder.itemView.setAnimation(
                AnimationUtils.loadAnimation(mLayoutInflater.getContext(), R.anim.anim_show_itemlist_down_to_up));
    }

    @Override
    public int getItemCount() {
        return mWall.size() + ProfileListData.NUMBER_STATIC_ITEMS;
    }

}