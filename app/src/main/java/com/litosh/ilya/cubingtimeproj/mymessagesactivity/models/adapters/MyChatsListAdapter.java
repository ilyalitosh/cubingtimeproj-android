package com.litosh.ilya.cubingtimeproj.mymessagesactivity.models.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.litosh.ilya.ct_sdk.models.messages.Chat;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.baseactivity.ui.BaseActivity;
import com.litosh.ilya.cubingtimeproj.chatactivity.ui.ChatActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

/**
 * MyChatsListAdapter
 *
 * @author Ilya Litosh
 */
public class MyChatsListAdapter extends RecyclerView.Adapter<MyChatsListAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private LinkedList<Chat> mChatLinkedList;
    private Context mContext;

    public MyChatsListAdapter(Context context, LinkedList<Chat> chatLinkedList) {
        mLayoutInflater = LayoutInflater.from(context);
        mChatLinkedList = chatLinkedList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(
                R.layout.activity_my_messages_chat_item,
                parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.mUserAvatar.setTransitionName(holder.getTransitionIconName());
            holder.mUserName.setTransitionName(holder.getTransitionUserNameName());
        }
        holder.mUserMessageDate.setHint(
                mChatLinkedList.get(holder.getAdapterPosition()).getChatTime());
        holder.mUserMessage.setHint(
                mChatLinkedList.get(holder.getAdapterPosition()).getChatLastMessage());
        holder.mUserName.setHint(
                mChatLinkedList.get(holder.getAdapterPosition()).getChatName());
        Glide.with(mContext)
                .load(Uri.parse(mChatLinkedList.get(holder.getAdapterPosition()).getUrlChatImage()))
                .into(holder.mUserAvatar);
        if (mChatLinkedList.get(holder.getAdapterPosition()).isContainsNewMessage()) {
            holder.mNewMessageIndicator.setVisibility(View.VISIBLE);
        }
    }

    public void updateChat(Chat chat) {
        for (Chat chat1: mChatLinkedList) {
            if (chat1.getChatId().equals(chat.getChatId())) {
                chat1.setChatLastMessage(chat.getChatLastMessage());
                chat1.setChatTime(chat.getChatTime());
                chat1.setContainsNewMessage(chat.isContainsNewMessage());
                break;
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mChatLinkedList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView mUserName;
        RoundedImageView mUserAvatar;
        AppCompatTextView mUserMessage;
        AppCompatTextView mUserMessageDate;
        AppCompatImageView mNewMessageIndicator;

        ViewHolder(View itemView) {
            super(itemView);
            mUserName =
                    itemView.findViewById(R.id.fragment_my_messages_chat_item_user_name_title);
            mUserAvatar =
                    itemView.findViewById(R.id.fragment_my_messages_chat_item_user_avatar);
            mUserMessage =
                    itemView.findViewById(R.id.fragment_my_messages_chat_item_user_message);
            mUserMessageDate =
                    itemView.findViewById(R.id.fragment_my_messages_chat_item_message_date);
            mNewMessageIndicator =
                    itemView.findViewById(R.id.fragment_my_messages_chat_item_is_new_message);
            initListeners();
        }

        private void initListeners() {
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(mLayoutInflater.getContext(), ChatActivity.class);
                intent.putExtra(
                        "chatId",
                        mChatLinkedList.get(getAdapterPosition()).getChatId());
                intent.putExtra(
                        "chatName",
                        mChatLinkedList.get(getAdapterPosition()).getChatName());
                intent.putExtra(
                        "urlChatImage",
                        mChatLinkedList.get(getAdapterPosition()).getUrlChatImage());
                intent.putExtra("transition-user-name", getTransitionUserNameName());
                intent.putExtra("transition-user-avatar", getTransitionIconName());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Pair<View, String> userNamePair = Pair.create(mUserName, getTransitionUserNameName());
                    Pair<View, String> userIconPair = Pair.create(mUserAvatar, getTransitionIconName());
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            (AppCompatActivity) mContext, userIconPair, userNamePair);
                    ((AppCompatActivity) mContext).startActivityForResult(intent, BaseActivity.REQUEST_CODE_LIKES_CHANGES, options.toBundle());
                } else {
                    ((AppCompatActivity) mContext).startActivityForResult(intent, BaseActivity.REQUEST_CODE_LIKES_CHANGES);
                }
            });
        }

        String getTransitionUserNameName() {
            return "chats_list_user_name_transition_" + getAdapterPosition();
        }

        String getTransitionIconName() {
            return "chats_list_icon_transition_" + getAdapterPosition();
        }
    }

}
