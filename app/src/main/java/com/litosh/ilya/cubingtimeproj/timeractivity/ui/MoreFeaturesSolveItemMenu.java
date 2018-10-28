package com.litosh.ilya.cubingtimeproj.timeractivity.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve;

public class MoreFeaturesSolveItemMenu extends PopupMenu implements PopupMenu.OnMenuItemClickListener {

    private Context mContext;
    private OnFeatureClick mOnFeatureClick;
    private Solve mSolve;
    private int mPosition;

    public MoreFeaturesSolveItemMenu(@NonNull Context context, @NonNull View anchor, Solve solve, int position) {
        super(context, anchor);
        mContext = context;
        mSolve = solve;
        mPosition = position;
        inflate(R.menu.menu_more_features_solve_item);
        setOnMenuItemClickListener(this);
    }

    public MoreFeaturesSolveItemMenu(@NonNull Context context, @NonNull View anchor, int gravity) {
        super(context, anchor, gravity);
        mContext = context;
    }

    public MoreFeaturesSolveItemMenu(@NonNull Context context, @NonNull View anchor, int gravity, int popupStyleAttr, int popupStyleRes) {
        super(context, anchor, gravity, popupStyleAttr, popupStyleRes);
        mContext = context;
    }

    public void setOnFeatureClick(OnFeatureClick mOnFeatureClick) {
        this.mOnFeatureClick = mOnFeatureClick;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_more_features_solve_item_delete:
                mOnFeatureClick.delete(mPosition, mSolve);
                return true;
            case R.id.menu_more_features_solve_item_dnf:
                Toast.makeText(mContext, "Скоро...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_more_features_solve_item_plus_two:
                Toast.makeText(mContext, "Скоро...", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    /**
     * OnFeatureClick
     *
     * @author Ilya Litosh
     */
    public interface OnFeatureClick {

        /**
         * Вызывается при нажатии
         * на удаление
         *
         * @param position позиция в списке сборок
         * @param solve сущность сборки
         */
        void delete(int position, Solve solve);

        /**
         * Вызывается при нажатии
         * на dnf
         *
         * @param position позиция в списке сборок
         * @param solve сущность сборки
         */
        void dnf(int position, Solve solve);

        /**
         * Вызывается при нажатии
         * на +2
         *
         * @param position позиция в списке сборок
         * @param solve сущность сборки
         */
        void plusTwo(int position, Solve solve);

    }

}
