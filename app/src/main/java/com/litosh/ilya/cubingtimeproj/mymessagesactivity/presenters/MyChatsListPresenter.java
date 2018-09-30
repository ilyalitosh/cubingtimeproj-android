package com.litosh.ilya.cubingtimeproj.mymessagesactivity.presenters;

import android.util.Log;

import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.ct_sdk.api.MessagesService;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;
import com.litosh.ilya.cubingtimeproj.mymessagesactivity.views.MyChatsListView;

/**
 * MyChatsListPresenter
 *
 * @author Ilya Litosh
 */
public class MyChatsListPresenter {

    private MyChatsListView mMyChatsListView;
    private static final String TAG = "MyChatsListPresenter";
    private MessagesService mMessagesService;

    public MyChatsListPresenter(MyChatsListView myChatsListView) {
        mMyChatsListView = myChatsListView;
        mMessagesService = new MessagesService(new UserCookie());
    }

    public void setMyChatsListAdapter() {
        mMyChatsListView.showProgressBar();
        ApiService.getChats(
                new UserCookie(),
                chatLinkedList -> {
                    mMyChatsListView.setMyChatListAdapter(chatLinkedList);
                    mMyChatsListView.hideProgressBar();
                });
    }

    public void listenNewMessagesInChatsList() {
        mMessagesService.listenNewMessagesInChatsList(
                chat -> {
                    Log.i(TAG, chat.getChatLastMessage());
                    mMyChatsListView.updateMyChatListAfterNewMessage(chat);
                });
    }

    public void stopListenNewMessagesInChatsList() {
        mMessagesService.stopListenNewMessagesInChatsList();
    }

}
