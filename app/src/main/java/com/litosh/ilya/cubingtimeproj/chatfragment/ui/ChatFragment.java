package com.litosh.ilya.cubingtimeproj.chatfragment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.litosh.ilya.ct_sdk.models.messages.Message;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.chatfragment.models.adapters.ChatMessagesListAdapter;
import com.litosh.ilya.cubingtimeproj.chatfragment.presenters.ChatMessagesListPresenter;
import com.litosh.ilya.cubingtimeproj.chatfragment.views.ChatMessagesListView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.LinkedList;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * ChatFragment
 *
 * Created by ilya_ on 24.06.2018.
 */

public class ChatFragment extends MvpAppCompatFragment implements ChatMessagesListView {

    @InjectPresenter
    ChatMessagesListPresenter mChatMessagesListPresenter;
    private RoundedImageView mChatImage;
    private AppCompatTextView mChatName;
    private AppCompatTextView mChatActivity;
    private AppCompatEditText mInputMessage;
    private RelativeLayout mSendMessageButtonSpace;
    private AppCompatButton mSendMessageButton;
    private RecyclerView mMessagesList;
    private ChatMessagesListAdapter mChatMessagesListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_room, null);

        initComponents(view);
        initListeners();

        return view;
    }

    private void initComponents(View view) {
        mChatImage =
                view.findViewById(R.id.fragment_chat_room_header_space_chat_avatar);
        mChatName =
                view.findViewById(R.id.fragment_chat_room_header_space_chat_name);
        mChatActivity =
                view.findViewById(R.id.fragment_chat_room_header_space_chat_activity);
        mInputMessage =
                view.findViewById(R.id.fragment_chat_room_footer_space_input_message_text);
        mSendMessageButton =
                view.findViewById(R.id.fragment_chat_room_footer_space_button_send);
        mSendMessageButtonSpace =
                view.findViewById(R.id.fragment_chat_room_footer_space_button_send_space);
        mMessagesList =
                view.findViewById(R.id.fragment_chat_room_messages_list);
        mChatMessagesListPresenter.initChatMessagesList("11329");
    }

    private void initListeners() {
        mInputMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    mSendMessageButtonSpace.startAnimation(AnimationUtils.loadAnimation(
                            getActivity(),
                            R.anim.anim_send_message_button_hide));
                    mSendMessageButtonSpace.setVisibility(View.GONE);
                } else {
                    if (mSendMessageButtonSpace.getVisibility() == View.GONE) {
                        mSendMessageButtonSpace.startAnimation(AnimationUtils.loadAnimation(
                                        getActivity(),
                                        R.anim.anim_send_message_button_show));
                        mSendMessageButtonSpace.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void initChatMessagesList(LinkedList<Message> messages) {
        mChatMessagesListAdapter = new ChatMessagesListAdapter(getActivity(), messages);
        mMessagesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMessagesList.setAdapter(mChatMessagesListAdapter);
        OverScrollDecoratorHelper
                .setUpOverScroll(mMessagesList, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
    }
}
