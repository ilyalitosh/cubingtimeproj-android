package com.litosh.ilya.cubingtimeproj.chatfragment.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.ct_sdk.api.MessagesService;
import com.litosh.ilya.cubingtimeproj.chatfragment.models.ChatMessagesData;
import com.litosh.ilya.cubingtimeproj.chatfragment.views.NewMessagesView;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;

@InjectViewState
public class NewMessagesPresenter extends MvpPresenter<NewMessagesView> {

    private MessagesService mMessagesService;

    public NewMessagesPresenter() {
        mMessagesService = new MessagesService(new UserCookie());
    }

    public void listenNewMessages(ChatMessagesData chatMessagesData) {
        mMessagesService = new MessagesService(new UserCookie());
        mMessagesService.listenNewMessagesInChat(
                chatMessagesData.getChatId(),
                message -> {
                    getViewState().updateMessagesList(message);
                });
    }

    public void stopListenNewMessages() {
        mMessagesService.stopListenNewMessagesInChat();
    }

}
