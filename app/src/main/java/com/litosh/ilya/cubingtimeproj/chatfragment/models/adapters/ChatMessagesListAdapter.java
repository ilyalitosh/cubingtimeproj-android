package com.litosh.ilya.cubingtimeproj.chatfragment.models.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.litosh.ilya.ct_sdk.models.messages.Message;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.chatfragment.models.ChatMessagesData;

import java.util.LinkedList;

/**
 * ChatMessagesListAdapter
 *
 * Created by ilya_ on 25.06.2018.
 */

public class ChatMessagesListAdapter extends RecyclerView.Adapter {

    private LayoutInflater mLayoutInflater;
    private LinkedList<Message> mMessages;

    public ChatMessagesListAdapter(Context context, LinkedList<Message> messages) {
        mLayoutInflater = LayoutInflater.from(context);
        mMessages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        if (mMessages.get(position).getUserName().equals("Вы")
                || mMessages.get(position).getUserName().equals("You")) {
            return ChatMessagesData.MESSAGE_ME;
        } else {
            return ChatMessagesData.MESSAGE_OTHER_USER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == ChatMessagesData.MESSAGE_OTHER_USER) {
            view = mLayoutInflater.inflate(
                    R.layout.fragment_chat_room_message_item_other_user,
                    parent,
                    false);
            return new MessagesOtherUserViewHolder(view);
        } else if (viewType == ChatMessagesData.MESSAGE_ME) {
            view = mLayoutInflater.inflate(
                    R.layout.fragment_chat_room_message_item_me,
                    parent,
                    false);
            return new MessagesMeViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MessagesMeViewHolder) {
            ((MessagesMeViewHolder) holder)
                    .mMeMessage
                    .setHint(mMessages.get(holder.getAdapterPosition()).getMessageText());
        } else if (holder instanceof MessagesOtherUserViewHolder) {
            ((MessagesOtherUserViewHolder) holder)
                    .mOtherUserMessage
                    .setHint(mMessages.get(holder.getAdapterPosition()).getMessageText());
        }
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }


    class MessagesMeViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView mMeMessage;

        MessagesMeViewHolder(View itemView) {
            super(itemView);
            mMeMessage =
                    itemView.findViewById(R.id.fragment_chat_room_message_item_me_message_text);
        }
    }

    class MessagesOtherUserViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView mOtherUserMessage;

        MessagesOtherUserViewHolder(View itemView) {
            super(itemView);
            mOtherUserMessage =
                    itemView.findViewById(R.id.fragment_chat_room_message_item_other_user_message_text);
        }
    }

}
