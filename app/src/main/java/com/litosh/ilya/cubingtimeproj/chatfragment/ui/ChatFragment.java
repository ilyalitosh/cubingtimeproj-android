package com.litosh.ilya.cubingtimeproj.chatfragment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.litosh.ilya.cubingtimeproj.R;

/**
 * ChatFragment
 *
 * Created by ilya_ on 24.06.2018.
 */

public class ChatFragment extends MvpAppCompatFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_room, null);

        return view;
    }
}
