package com.litosh.ilya.cubingtimeproj.mymessagesfragment.views;

import com.arellomobile.mvp.MvpView;
import com.litosh.ilya.ct_sdk.models.messages.Chat;

import java.util.LinkedList;

/**
 * MyChatsListView
 *
 * Created by ilya_ on 24.06.2018.
 */

public interface MyChatsListView extends MvpView {

    /**
     * Вызывается при установки адаптера MyChatsList
     *
     * @param chatLinkedList chatLinkedList
     */
    void setMyChatListAdapter(LinkedList<Chat> chatLinkedList);

    /**
     * Вызывается при получении нового сообщения
     * и обновляет чат
     *
     * @param chat Chat модель
     */
    void updateMyChatListAfterNewMessage(Chat chat);

}
