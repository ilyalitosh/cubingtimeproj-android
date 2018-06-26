package com.litosh.ilya.cubingtimeproj.chatactivity.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.litosh.ilya.cubingtimeproj.baseactivity.ui.BaseActivity;
import com.litosh.ilya.cubingtimeproj.chatfragment.models.ChatMessagesData;
import com.litosh.ilya.cubingtimeproj.chatfragment.ui.ChatFragment;

/**
 * ChatActivity
 *
 * Created by ilya_ on 24.06.2018.
 */

public class ChatActivity extends BaseActivity {

    private static final String TAG = "ChatActivity";
    private ChatFragment mChatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initChatFragment();

    }

    private void initChatFragment() {
        mChatFragment = new ChatFragment();
        mChatFragment.setChatMessagesData(getChatMessagesData());
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(getFragmentContainer().getId(), mChatFragment);
        ft.commit();
    }

    private ChatMessagesData getChatMessagesData() {
        ChatMessagesData chatMessagesData = new ChatMessagesData();
        chatMessagesData.setChatId(getIntent().getStringExtra("chatId"));
        chatMessagesData.setChatName(getIntent().getStringExtra("chatName"));
        chatMessagesData.setChatImageUrl(getIntent().getStringExtra("urlChatImage"));
        return chatMessagesData;
    }

}
