package com.litosh.ilya.cubingtimeproj.mymessagesfragment.models.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.litosh.ilya.ct_sdk.models.messages.Chat;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.chatactivity.ui.ChatActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

/**
 * MyChatsListAdapter
 *
 * Created by ilya_ on 24.06.2018.
 */

public class MyChatsListAdapter extends RecyclerView.Adapter<MyChatsListAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private LinkedList<Chat> mChatLinkedList;

    public MyChatsListAdapter(Context context, LinkedList<Chat> chatLinkedList) {
        mLayoutInflater = LayoutInflater.from(context);
        mChatLinkedList = chatLinkedList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(
                R.layout.fragment_my_messages_chat_item,
                parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mUserMessageDate.setHint(
                mChatLinkedList.get(holder.getAdapterPosition()).getChatTime());
        holder.mUserMessage.setHint(
                mChatLinkedList.get(holder.getAdapterPosition()).getChatLastMessage());
        holder.mUserName.setHint(
                mChatLinkedList.get(holder.getAdapterPosition()).getChatName());
        Picasso.get()
                .load(Uri.parse(mChatLinkedList.get(holder.getAdapterPosition()).getUrlChatImage()))
                .into(holder.mUserAvatar);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mLayoutInflater.getContext(), ChatActivity.class);
            mLayoutInflater.getContext().startActivity(intent);
        });
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
        }
    }

}
