package com.litosh.ilya.cubingtimeproj.chatfragment.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.cubingtimeproj.chatfragment.models.ChatMessagesData;
import com.litosh.ilya.cubingtimeproj.chatfragment.views.NewMessagesView;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;

@InjectViewState
public class NewMessagesPresenter extends MvpPresenter<NewMessagesView> {

    public void initListenerNewMessages(ChatMessagesData chatMessagesData) {
        ApiService.listenNewMessages(
                new UserCookie(),
                chatMessagesData.getChatId(),
                message -> {
                    getViewState().updateMessagesList(message);
                });
    }

}
