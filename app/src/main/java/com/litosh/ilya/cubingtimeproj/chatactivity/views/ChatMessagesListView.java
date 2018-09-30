package com.litosh.ilya.cubingtimeproj.chatactivity.views;

import com.litosh.ilya.ct_sdk.models.messages.Message;

import java.util.LinkedList;

/**
 * ChatMessagesListView
 *
 * @author Ilya Litosh
 */
public interface ChatMessagesListView {

    /**
     * Вызывается при установке адаптера recyclerview
     * с сообщениями
     *
     * @param messages сообщения
     */
    void initChatMessagesList(LinkedList<Message> messages);

}
