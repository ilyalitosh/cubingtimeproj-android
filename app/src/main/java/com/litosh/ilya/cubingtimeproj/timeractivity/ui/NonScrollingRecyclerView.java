package com.litosh.ilya.cubingtimeproj.timeractivity.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * NonScrollingRecyclerView
 *
 * @author Ilya Litosh
 */
public class NonScrollingRecyclerView extends RecyclerView {

    public NonScrollingRecyclerView(Context context) {
        super(context);
    }

    public NonScrollingRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NonScrollingRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return false;
    }
}
