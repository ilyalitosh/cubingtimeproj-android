package com.litosh.ilya.cubingtimeproj.mymessagesfragment.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;
import com.litosh.ilya.cubingtimeproj.mymessagesfragment.views.MyChatsListView;

/**
 * MyChatsListPresenter
 *
 * Created by ilya_ on 24.06.2018.
 */

@InjectViewState
public class MyChatsListPresenter extends MvpPresenter<MyChatsListView> {

    public void setMyChatsListAdapter() {
        ApiService.getChats(
                new UserCookie(),
                chatLinkedList -> {
                    getViewState().setMyChatListAdapter(chatLinkedList);
                });
    }

}