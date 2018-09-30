package com.litosh.ilya.cubingtimeproj.chatactivity.ui;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.ct_sdk.callbacks.OnMessageSendingCallback;
import com.litosh.ilya.ct_sdk.models.messages.Message;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.chatactivity.models.ChatMessagesData;
import com.litosh.ilya.cubingtimeproj.chatactivity.models.adapters.ChatMessagesListAdapter;
import com.litosh.ilya.cubingtimeproj.chatactivity.presenters.ChatMessagesListPresenter;
import com.litosh.ilya.cubingtimeproj.chatactivity.presenters.NewMessagesPresenter;
import com.litosh.ilya.cubingtimeproj.chatactivity.views.ChatMessagesListView;
import com.litosh.ilya.cubingtimeproj.chatactivity.views.NewMessagesView;
import com.litosh.ilya.cubingtimeproj.globalmodels.InputFormsChecker;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.LinkedList;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * ChatActivity
 *
 * @author Ilya Litosh
 */
public class ChatActivity extends AppCompatActivity
        implements ChatMessagesListView, InputFormsChecker, NewMessagesView {

    private NewMessagesPresenter mNewMessagesPresenter;
    private ChatMessagesListPresenter mChatMessagesListPresenter;
    private static final String TAG = "ChatFragment";
    private ChatMessagesData mChatMessagesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        restoreChatMessageDataFromBundle(savedInstanceState);

        initActivityComponents();
        initActivityListeners();
    }

    @Override
    public void onResume() {
        super.onResume();
        mNewMessagesPresenter.listenNewMessages(mChatMessagesData);
    }

    @Override
    public void onPause() {
        super.onPause();
        mNewMessagesPresenter.stopListenNewMessages();
    }

    private RoundedImageView mChatImage;
    private AppCompatTextView mChatName;
    private AppCompatTextView mChatActivity;
    private AppCompatEditText mInputMessage;
    private RelativeLayout mSendMessageButtonSpace;
    private AppCompatButton mSendMessageButton;
    private RecyclerView mMessagesList;
    private RelativeLayout mMoreFeaturesButton;
    public void initActivityComponents() {
        mChatImage = findViewById(R.id.activity_chat_room_header_space_chat_avatar);
        Glide.with(this)
                //.applyDefaultRequestOptions(RequestOptions.fitCenterTransform().centerCrop().circleCrop())
                .load(Uri.parse(mChatMessagesData.getChatImageUrl()))
                .into(mChatImage);
        mChatName = findViewById(R.id.activity_chat_room_header_space_chat_name);
        mChatName.setHint(mChatMessagesData.getChatName());
        mChatActivity = findViewById(R.id.activity_chat_room_header_space_chat_activity);
        mInputMessage = findViewById(R.id.fragment_chat_room_footer_space_input_message_text);
        mSendMessageButton = findViewById(R.id.fragment_chat_room_footer_space_button_send);
        mSendMessageButtonSpace = findViewById(R.id.fragment_chat_room_footer_space_button_send_space);
        mMessagesList = findViewById(R.id.fragment_chat_room_messages_list);
        mMoreFeaturesButton = findViewById(R.id.activity_chat_room_header_space_more_features_button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mChatName.setTransitionName(getIntent().getExtras().getString("transition-user-name"));
            mChatImage.setTransitionName(getIntent().getExtras().getString("transition-user-avatar"));
        }
        mChatMessagesListPresenter = new ChatMessagesListPresenter(this);
        mNewMessagesPresenter = new NewMessagesPresenter(this);
        mChatMessagesListPresenter.initChatMessagesList(mChatMessagesData.getChatId());
    }

    public void initActivityListeners() {
        mInputMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    mSendMessageButtonSpace.startAnimation(AnimationUtils.loadAnimation(
                            ChatActivity.this,
                            R.anim.anim_send_message_button_hide));
                    mSendMessageButtonSpace.setVisibility(View.GONE);
                } else {
                    if (mSendMessageButtonSpace.getVisibility() == View.GONE) {
                        mSendMessageButtonSpace.startAnimation(AnimationUtils.loadAnimation(
                                ChatActivity.this,
                                R.anim.anim_send_message_button_show));
                        mSendMessageButtonSpace.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
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
        mMoreFeaturesButton.setOnClickListener(v -> {
            MoreFeaturesChatItemMenu moreFeaturesChatItemMenu =
                    new MoreFeaturesChatItemMenu(ChatActivity.this, v);
            moreFeaturesChatItemMenu.show();
        });
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
        if (savedInstanceState != null) {
            mChatMessagesData = new ChatMessagesData();
            mChatMessagesData
                    .setChatId(savedInstanceState.getString("ct-chat-room-chat-id"));
            mChatMessagesData
                    .setChatImageUrl(savedInstanceState.getString("ct-chat-room-chat-image-url"));
            mChatMessagesData
                    .setChatName(savedInstanceState.getString("ct-chat-room-chat-name"));
            Log.i(TAG, "state restored");
        } else {
            mChatMessagesData = getChatMessagesData();
        }
    }

    private ChatMessagesData getChatMessagesData() {
        ChatMessagesData chatMessagesData = new ChatMessagesData();
        chatMessagesData.setChatId(getIntent().getStringExtra("chatId"));
        chatMessagesData.setChatName(getIntent().getStringExtra("chatName"));
        chatMessagesData.setChatImageUrl(getIntent().getStringExtra("urlChatImage"));
        return chatMessagesData;
    }

    private ChatMessagesListAdapter mChatMessagesListAdapter;
    @Override
    public void initChatMessagesList(LinkedList<Message> messages) {
        mChatMessagesListAdapter = new ChatMessagesListAdapter(this, messages);
        mMessagesList.setLayoutManager(new LinearLayoutManager(this));
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
