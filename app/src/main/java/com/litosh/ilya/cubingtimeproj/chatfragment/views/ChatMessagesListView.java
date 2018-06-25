package com.litosh.ilya.cubingtimeproj.chatfragment.views;

import com.arellomobile.mvp.MvpView;
import com.litosh.ilya.ct_sdk.models.messages.Message;

import java.util.LinkedList;

/**
 * ChatMessagesListView
 *
 * Created by ilya_ on 25.06.2018.
 */

public interface ChatMessagesListView extends MvpView {

    /**
     * Вызывается при установке адаптера recyclerview
     * с сообщениями
     *
     * @param messages сообщения
     */
    void initChatMessagesList(LinkedList<Message> messages);

}
