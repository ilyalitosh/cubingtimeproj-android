package com.litosh.ilya.cubingtimeproj.mymessagesactivity.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.litosh.ilya.ct_sdk.models.messages.Chat;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.baseactivity.ui.BaseActivity;
import com.litosh.ilya.cubingtimeproj.mymessagesactivity.models.adapters.MyChatsListAdapter;
import com.litosh.ilya.cubingtimeproj.mymessagesactivity.presenters.MyChatsListPresenter;
import com.litosh.ilya.cubingtimeproj.mymessagesactivity.views.MyChatsListView;

import java.util.LinkedList;

/**
 * MyMessagesActivity
 *
 * @author Ilya Litosh
 */
public class MyMessagesActivity extends BaseActivity implements MyChatsListView {

    private static final String TAG = "MyMessagesActivity";
    private MyChatsListPresenter mMyChatsListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: реализовать восстановление при повороте экрана
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_messages;
    }

    private RecyclerView mMyChatsList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressBar mProgressBar;
    @Override
    public void initActivityComponents() {
        super.initActivityComponents();
        mMyChatsList = findViewById(R.id.fragment_my_messages_chats_list);
        mSwipeRefreshLayout = findViewById(R.id.fragment_my_messages_swipe_refresh_layout);
        mProgressBar = findViewById(R.id.activity_my_messages_progress_bar);
        setCustomSwipeRefreshLayoutStyle();
        setCustomProgressBarStyle();
        mMyChatsListPresenter = new MyChatsListPresenter(this);
        mMyChatsListPresenter.setMyChatsListAdapter();
    }

    @Override
    public void initActivityListeners() {
        super.initActivityListeners();
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mMyChatsListPresenter.setMyChatsListAdapter();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_LIKES_CHANGES:
                if (resultCode == Activity.RESULT_OK) {
                    Log.i(TAG, "Ответ пришел");
                }
                break;
        }
    }

    private void setCustomSwipeRefreshLayoutStyle() {
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorMain,
                R.color.colorMain,
                R.color.colorMain);
    }

    private void setCustomProgressBarStyle() {
        mProgressBar.getIndeterminateDrawable()
                .setColorFilter(
                        Color.argb(255, 85, 102, 176),
                        PorterDuff.Mode.SRC_IN);
    }

    private MyChatsListAdapter mMyChatsListAdapter;
    @Override
    public void setMyChatListAdapter(LinkedList<Chat> chatLinkedList) {
        mMyChatsListAdapter = new MyChatsListAdapter(this, chatLinkedList);
        mMyChatsList.setLayoutManager(new LinearLayoutManager(this));
        mMyChatsList.setAdapter(mMyChatsListAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void updateMyChatListAfterNewMessage(Chat chat) {
        mMyChatsListAdapter.updateChat(chat);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMyChatsListPresenter.listenNewMessagesInChatsList();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMyChatsListPresenter.stopListenNewMessagesInChatsList();
    }
}
