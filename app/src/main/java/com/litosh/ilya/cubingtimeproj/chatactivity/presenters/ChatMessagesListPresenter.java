package com.litosh.ilya.cubingtimeproj.chatactivity.presenters;

import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.cubingtimeproj.chatactivity.views.ChatMessagesListView;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;

/**
 * ChatMessagesListPresenter
 *
 * @author Ilya Litosh
 */
public class ChatMessagesListPresenter {

    private ChatMessagesListView mChatMessagesListView;

    public ChatMessagesListPresenter(ChatMessagesListView chatMessagesListView) {
        mChatMessagesListView = chatMessagesListView;
    }

    public void initChatMessagesList(String chatId) {
        ApiService.getMessagesInChat(
                chatId,
                new UserCookie(),
                messages -> {
                    mChatMessagesListView.initChatMessagesList(messages);
                });
    }

}
