package com.litosh.ilya.cubingtimeproj.chatfragment.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.cubingtimeproj.chatfragment.views.ChatMessagesListView;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;

/**
 * ChatMessagesListPresenter
 *
 * Created by ilya_ on 25.06.2018.
 */
@InjectViewState
public class ChatMessagesListPresenter extends MvpPresenter<ChatMessagesListView> {

    public void initChatMessagesList(String chatId) {
        ApiService.getMessagesInChat(
                chatId,
                new UserCookie(),
                messages -> {
                    getViewState().initChatMessagesList(messages);
                });
    }

}
