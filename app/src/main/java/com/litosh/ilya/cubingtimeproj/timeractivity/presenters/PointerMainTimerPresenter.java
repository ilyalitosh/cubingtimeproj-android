package com.litosh.ilya.cubingtimeproj.timeractivity.presenters;

import android.support.v7.widget.AppCompatImageView;

import com.litosh.ilya.cubingtimeproj.timeractivity.views.PointerMainTimerView;

/**
 * PointerMainTimerView
 *
 * @author Ilya Litosh
 */
public class PointerMainTimerPresenter {

    private PointerMainTimerView mPointerMainTimerView;

    public PointerMainTimerPresenter(PointerMainTimerView pointerMainTimerView) {
        mPointerMainTimerView = pointerMainTimerView;
    }

    public void rotate(AppCompatImageView pointer, int dy, int heightElem) {
        pointer.post(new Runnable() {
            float countDegreeForIterationForRotate = 180f / heightElem * dy;
            float i = 0;
            @Override
            public void run() {
                if (countDegreeForIterationForRotate > 0) {
                    while(i < countDegreeForIterationForRotate){
                        if((i + 1) > countDegreeForIterationForRotate){
                            mPointerMainTimerView.rotatePointer(pointer.getRotation() + (countDegreeForIterationForRotate - i));
                            break;
                        }
                        mPointerMainTimerView.rotatePointer(pointer.getRotation() + 1);
                        i++;
                    }
                } else {
                    while(i > countDegreeForIterationForRotate){
                        if((i - 1) < countDegreeForIterationForRotate){
                            mPointerMainTimerView.rotatePointer(pointer.getRotation() + (countDegreeForIterationForRotate - i));
                            break;
                        }
                        mPointerMainTimerView.rotatePointer(pointer.getRotation() - 1);
                        i--;
                    }
                }
            }
        });
    }

}
