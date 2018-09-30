package com.litosh.ilya.cubingtimeproj.mymessagesactivity.views;

import com.litosh.ilya.ct_sdk.models.messages.Chat;

import java.util.LinkedList;

/**
 * MyChatsListView
 *
 * @author Ilya Litosh
 */
public interface MyChatsListView {

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

    /**
     * Показывает прогресс бар
     *
     */
    void showProgressBar();

    /**
     * Прячет прогресс бар
     *
     */
    void hideProgressBar();

}
