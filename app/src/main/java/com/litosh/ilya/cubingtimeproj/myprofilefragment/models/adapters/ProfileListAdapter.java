package com.litosh.ilya.cubingtimeproj.myprofilefragment.models.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.transition.TransitionInflater;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.litosh.ilya.ct_sdk.models.profile.User;
import com.litosh.ilya.ct_sdk.models.profile.Wall;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.models.AvatarHolder;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.models.FriendsHolder;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.models.NoteHolder;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.models.UserInfoHolder;
import com.litosh.ilya.cubingtimeproj.notemorefragment.ui.NoteMoreFragment;
import com.squareup.picasso.Picasso;

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

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == ProfileListData.AVATAR_ITEM) {
            view = mLayoutInflater.inflate(
                    R.layout.fragment_my_profile_avatar_item, parent, false);
            return new AvatarHolder(view);
        } else if (viewType == ProfileListData.USER_INFO_ITEM) {
            view = mLayoutInflater.inflate(
                    R.layout.fragment_my_profile_user_info, parent, false);
            return new UserInfoHolder(view);
        } else if (viewType == ProfileListData.FRIENDS_ITEM) {
            view = mLayoutInflater.inflate(
                    R.layout.fragment_my_profile_friends, parent, false);
            return new FriendsHolder(view);
        } else {
            view = mLayoutInflater.inflate(
                    R.layout.fragment_my_profile_wall_note_item, parent, false);
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

    private void initializeNoteItem(NoteHolder holder, int position) {
        holder.getUserName().setText(mWall.get(position).getUserName());

        String text = mWall.get(position).getText();
        holder.getText().setText(text.length() > 100 ? text.substring(0, 100) : text);

        holder.getDate().setText(mWall.get(position).getDate());

        Glide.with(mLayoutInflater.getContext())
                .load(Uri.parse(mWall.get(position).getUrlUserAvatar()))
                .into(holder.getUserIcon());

        int likesNumber = mWall.get(position).getLikesNumber();
        if (likesNumber > 0) {
            holder.getLikeButton().setBackgroundResource(R.drawable.ic_like_pressed);
        }

        if (likesNumber > 0) {
            holder.getLikeNumber().setText(
                    String.valueOf(mWall.get(position).getLikesNumber()));
        } else {
            holder.getLikeNumber().setText("");
        }

        holder.itemView.setAnimation(
                AnimationUtils.loadAnimation(mLayoutInflater.getContext(), R.anim.anim_show_itemlist_down_to_up));
    }

    private void initializeAvatarItem(AvatarHolder holder) {
        holder.getActivity().setText(mUser.getActivity());

        holder.getProfileName().setText(mUser.getProfileName());

        if (!mUser.getUrlAvatar().contains("default")) {
            Picasso.get()
                    .load("https://" + Uri.parse(mUser.getUrlAvatar()))
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

    private class ProfileListData {

        private static final int AVATAR_ITEM = 0;
        private static final int USER_INFO_ITEM = 1;
        private static final int FRIENDS_ITEM = 2;
        private static final int NUMBER_STATIC_ITEMS = 3;

    }

}
