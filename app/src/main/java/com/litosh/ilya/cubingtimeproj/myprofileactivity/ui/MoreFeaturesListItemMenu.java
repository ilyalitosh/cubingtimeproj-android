package com.litosh.ilya.cubingtimeproj.myprofileactivity.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.litosh.ilya.cubingtimeproj.R;

/**
 * MoreFeaturesListItemMenu
 *
 * @author Ilya Litosh
 */
public class MoreFeaturesListItemMenu extends PopupMenu implements PopupMenu.OnMenuItemClickListener {

    private Context mContext;

    public MoreFeaturesListItemMenu(@NonNull Context context, @NonNull View anchor) {
        super(context, anchor);
        mContext = context;
        inflate(R.menu.menu_more_features_list_item);
        setOnMenuItemClickListener(this);
    }

    public MoreFeaturesListItemMenu(@NonNull Context context, @NonNull View anchor, int gravity) {
        super(context, anchor, gravity);
        mContext = context;
    }

    public MoreFeaturesListItemMenu(@NonNull Context context, @NonNull View anchor, int gravity, int popupStyleAttr, int popupStyleRes) {
        super(context, anchor, gravity, popupStyleAttr, popupStyleRes);
        mContext = context;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_more_features_list_item_edit:
                Toast.makeText(mContext, "Скоро...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_more_features_list_item_delete:
                Toast.makeText(mContext, "Скоро...", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}
