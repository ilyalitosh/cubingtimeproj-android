package com.litosh.ilya.cubingtimeproj.chatactivity.presenters;

import com.litosh.ilya.ct_sdk.api.MessagesService;
import com.litosh.ilya.cubingtimeproj.chatactivity.models.ChatMessagesData;
import com.litosh.ilya.cubingtimeproj.chatactivity.views.NewMessagesView;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;

/**
 * NewMessagesPresenter
 *
 * @author Ilya Litosh
 */
public class NewMessagesPresenter {

    private NewMessagesView mNewMessagesView;
    private MessagesService mMessagesService;

    public NewMessagesPresenter(NewMessagesView newMessagesView) {
        mNewMessagesView = newMessagesView;
        mMessagesService = new MessagesService(new UserCookie());
    }

    public void listenNewMessages(ChatMessagesData chatMessagesData) {
        mMessagesService = new MessagesService(new UserCookie());
        mMessagesService.listenNewMessagesInChat(
                chatMessagesData.getChatId(),
                message -> {
                    mNewMessagesView.updateMessagesList(message);
                });
    }

    public void stopListenNewMessages() {
        mMessagesService.stopListenNewMessagesInChat();
    }

}
