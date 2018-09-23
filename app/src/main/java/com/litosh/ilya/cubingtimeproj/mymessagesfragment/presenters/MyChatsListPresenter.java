package com.litosh.ilya.cubingtimeproj.mymessagesfragment.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.ct_sdk.api.MessagesService;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;
import com.litosh.ilya.cubingtimeproj.mymessagesfragment.views.MyChatsListView;

/**
 * MyChatsListPresenter
 *
 * @author Ilya Litosh
 */
@InjectViewState
public class MyChatsListPresenter extends MvpPresenter<MyChatsListView> {

    private static final String TAG = "MyChatsListPresenter";
    private MessagesService mMessagesService;

    public MyChatsListPresenter() {
        mMessagesService = new MessagesService(new UserCookie());
    }

    public void setMyChatsListAdapter() {
        ApiService.getChats(
                new UserCookie(),
                chatLinkedList -> {
                    getViewState().setMyChatListAdapter(chatLinkedList);
                });
    }

    public void listenNewMessagesInChatsList() {
        mMessagesService.listenNewMessagesInChatsList(
                chat -> {
                    Log.i(TAG, chat.getChatLastMessage());
                    getViewState().updateMyChatListAfterNewMessage(chat);
                });
    }

    public void stopListenNewMessagesInChatsList() {
        mMessagesService.stopListenNewMessagesInChatsList();
    }

}
