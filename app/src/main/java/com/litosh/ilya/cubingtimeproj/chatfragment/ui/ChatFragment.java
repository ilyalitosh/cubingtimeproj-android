package com.litosh.ilya.cubingtimeproj.chatfragment.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.ct_sdk.callbacks.OnMessageSendingCallback;
import com.litosh.ilya.ct_sdk.models.messages.Message;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.chatfragment.models.ChatMessagesData;
import com.litosh.ilya.cubingtimeproj.chatfragment.models.adapters.ChatMessagesListAdapter;
import com.litosh.ilya.cubingtimeproj.chatfragment.presenters.ChatMessagesListPresenter;
import com.litosh.ilya.cubingtimeproj.chatfragment.presenters.NewMessagesPresenter;
import com.litosh.ilya.cubingtimeproj.chatfragment.views.ChatMessagesListView;
import com.litosh.ilya.cubingtimeproj.chatfragment.views.NewMessagesView;
import com.litosh.ilya.cubingtimeproj.globalmodels.InputFormsChecker;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * ChatFragment
 *
 * Created by ilya_ on 24.06.2018.
 */

public class ChatFragment extends MvpAppCompatFragment
        implements ChatMessagesListView, InputFormsChecker, NewMessagesView {

    @InjectPresenter
    NewMessagesPresenter mNewMessagesPresenter;
    @InjectPresenter
    ChatMessagesListPresenter mChatMessagesListPresenter;
    private static final String TAG = "ChatFragment";
    private RoundedImageView mChatImage;
    private AppCompatTextView mChatName;
    private AppCompatTextView mChatActivity;
    private AppCompatEditText mInputMessage;
    private RelativeLayout mSendMessageButtonSpace;
    private AppCompatButton mSendMessageButton;
    private RecyclerView mMessagesList;
    private ChatMessagesListAdapter mChatMessagesListAdapter;
    private ChatMessagesData mChatMessagesData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_room, null);

        if (savedInstanceState != null) {
            Log.i(TAG, "state restored");
            restoreChatMessageDataFromBundle(savedInstanceState);
        }

        initComponents(view);
        initListeners();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "state saved");
        saveChatMessageDataInBundle(outState);
    }

    private void saveChatMessageDataInBundle(Bundle outState) {
        outState.putString("ct-chat-room-chat-id", mChatMessagesData.getChatId());
        outState.putString("ct-chat-room-chat-image-url", mChatMessagesData.getChatImageUrl());
        outState.putString("ct-chat-room-chat-name", mChatMessagesData.getChatName());
    }

    private void restoreChatMessageDataFromBundle(Bundle savedInstanceState) {
        mChatMessagesData = new ChatMessagesData();
        mChatMessagesData.setChatId(savedInstanceState.getString("ct-chat-room-chat-id"));
        mChatMessagesData.setChatImageUrl(savedInstanceState.getString("ct-chat-room-chat-image-url"));
        mChatMessagesData.setChatName(savedInstanceState.getString("ct-chat-room-chat-name"));
    }

    private void initComponents(View view) {
        mChatImage =
                view.findViewById(R.id.fragment_chat_room_header_space_chat_avatar);
        Picasso.get().load(Uri.parse(mChatMessagesData.getChatImageUrl())).into(mChatImage);
        mChatName =
                view.findViewById(R.id.fragment_chat_room_header_space_chat_name);
        mChatName.setHint(mChatMessagesData.getChatName());
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
        mChatMessagesListPresenter.initChatMessagesList(mChatMessagesData.getChatId());
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
        initSpringListenerWithSendButton();
        mSendMessageButton.setOnClickListener(v -> {
            if (!isSomeEmpty()) {
                ApiService.sendMessage(
                        new UserCookie(),
                        mInputMessage.getText().toString(),
                        mChatMessagesData.getChatId(),
                        new OnMessageSendingCallback() {
                            @Override
                            public void onSuccess(Message message) {
                                mChatMessagesListAdapter.addItem(message);
                                mMessagesList.scrollToPosition(
                                        mChatMessagesListAdapter.getItemCount() - 1);
                                mInputMessage.setText("");
                            }

                            @Override
                            public void onError(String error) {
                                Log.i(TAG, error);
                            }
                        });
            }
        });
        mNewMessagesPresenter.initListenerNewMessages(mChatMessagesData);
    }

    private void initSpringListenerWithSendButton() {
        SpringSystem springSystem = SpringSystem.create();
        SpringConfig springConfig = new SpringConfig(300, 10);
        Spring spring = springSystem.createSpring();
        spring.setSpringConfig(springConfig);
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float) spring.getCurrentValue();
                float scale = 1f - (value * 0.5f);
                mSendMessageButton.setScaleX(scale);
                mSendMessageButton.setScaleY(scale);
            }
        });
        mSendMessageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        spring.setEndValue(1);
                        break;
                    case MotionEvent.ACTION_UP:
                        spring.setEndValue(0);
                        break;
                }
                return false;
            }

        });
    }

    public void setChatMessagesData(ChatMessagesData mChatMessagesData) {
        this.mChatMessagesData = mChatMessagesData;
    }

    @Override
    public void initChatMessagesList(LinkedList<Message> messages) {
        mChatMessagesListAdapter = new ChatMessagesListAdapter(getActivity(), messages);
        mMessagesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMessagesList.setAdapter(mChatMessagesListAdapter);
        OverScrollDecoratorHelper
                .setUpOverScroll(mMessagesList, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        mMessagesList.scrollToPosition(mChatMessagesListAdapter.getItemCount() - 1);
    }

    @Override
    public boolean isSomeEmpty() {
        if (TextUtils.isEmpty(mInputMessage.getText())) {
            return true;
        }
        return false;
    }

    @Override
    public void updateMessagesList(Message message) {
        mChatMessagesListAdapter.addItem(message);
        mMessagesList.scrollToPosition(mChatMessagesListAdapter.getItemCount() - 1);
    }
}
