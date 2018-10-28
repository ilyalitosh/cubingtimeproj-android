package com.litosh.ilya.cubingtimeproj.chatactivity.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.litosh.ilya.cubingtimeproj.R;

/**
 * MoreFeaturesChatItemMenu
 *
 * @author Ilya Litosh
 */
public class MoreFeaturesChatItemMenu extends PopupMenu implements PopupMenu.OnMenuItemClickListener {

    private Context mContext;

    public MoreFeaturesChatItemMenu(@NonNull Context context, @NonNull View anchor) {
        super(context, anchor);
        mContext = context;
        inflate(R.menu.menu_more_features_chat_item);
        setOnMenuItemClickListener(this);
    }

    public MoreFeaturesChatItemMenu(@NonNull Context context, @NonNull View anchor, int gravity) {
        super(context, anchor, gravity);
        mContext = context;
    }

    public MoreFeaturesChatItemMenu(@NonNull Context context, @NonNull View anchor, int gravity, int popupStyleAttr, int popupStyleRes) {
        super(context, anchor, gravity, popupStyleAttr, popupStyleRes);
        mContext = context;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_more_features_chat_item_update:
                Toast.makeText(mContext, "Скоро...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_more_features_chat_item_delete:
                Toast.makeText(mContext, "Скоро...", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

}
