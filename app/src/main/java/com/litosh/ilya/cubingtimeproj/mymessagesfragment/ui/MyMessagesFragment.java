package com.litosh.ilya.cubingtimeproj.mymessagesfragment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.litosh.ilya.ct_sdk.models.messages.Chat;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.mymessagesfragment.models.adapters.MyChatsListAdapter;
import com.litosh.ilya.cubingtimeproj.mymessagesfragment.presenters.MyChatsListPresenter;
import com.litosh.ilya.cubingtimeproj.mymessagesfragment.views.MyChatsListView;

import java.util.LinkedList;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * MyMessagesFragment
 *
 * Created by ilya_ on 24.06.2018.
 */

public class MyMessagesFragment extends MvpAppCompatFragment implements MyChatsListView {

    @InjectPresenter
    MyChatsListPresenter mMyChatsListPresenter;
    private static final String TAG = "MyMessagesFragment";
    private RecyclerView mMyChatsList;
    private MyChatsListAdapter mMyChatsListAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_messages, null);

        initComponents(view);
        initListeners();

        return view;
    }

    private void initComponents(View view) {
        mMyChatsList = view.findViewById(R.id.fragment_my_messages_chats_list);
        mSwipeRefreshLayout = view.findViewById(R.id.fragment_my_messages_swipe_refresh_layout);
        setCustomSwipeRefreshLayoutStyle();

        mMyChatsListPresenter.setMyChatsListAdapter();
    }

    private void setCustomSwipeRefreshLayoutStyle() {
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorMain,
                R.color.colorMain,
                R.color.colorMain);
    }

    private void initListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mMyChatsListPresenter.setMyChatsListAdapter();
        });
    }

    @Override
    public void setMyChatListAdapter(LinkedList<Chat> chatLinkedList) {
        mMyChatsListAdapter = new MyChatsListAdapter(getActivity(), chatLinkedList);
        mMyChatsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyChatsList.setAdapter(mMyChatsListAdapter);
        OverScrollDecoratorHelper
                .setUpOverScroll(mMyChatsList, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
