package com.litosh.ilya.cubingtimeproj.baseactivity.presenters;

import android.content.res.Configuration;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.baseactivity.models.ActionBarDrawerData;
import com.litosh.ilya.cubingtimeproj.baseactivity.views.NavigationViewView;

/**
 * NavigationViewPresenter
 *
 * Created by ilya_ on 24.06.2018.
 */

@InjectViewState
public class NavigationViewPresenter extends MvpPresenter<NavigationViewView> {

    public void setDrawerListener(ActionBarDrawerData actionBarDrawerData) {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                (AppCompatActivity) actionBarDrawerData.getContext(),
                actionBarDrawerData.getDrawerLayout(),
                null,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                checkerPressedPointer = 0;
                actionBarDrawerData.getPointer().setRotation(0);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                checkerPressedPointer = 1;
                actionBarDrawerData.getPointer().setRotation(180);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                rotate();
                actionBarDrawerData
                        .getPointerSpace()
                        .setX(actionBarDrawerData.getNavigationView().getRight());
            }

            int checkerPressedPointer = 0;
            int previousValueRight = 0;
            int currentValueRight = 0;
            /* поворот стрелки */
            private void rotate() {
                previousValueRight = currentValueRight;
                currentValueRight = actionBarDrawerData.getNavigationView().getRight();
                actionBarDrawerData.getPointer().post(new Runnable() {
                    float countDegreeForIterationForRotate = (180f/actionBarDrawerData.getNavigationView().getWidth()) * (currentValueRight - previousValueRight);
                    float i = 0;
                    @Override
                    public void run() {
                        if (countDegreeForIterationForRotate > 0) {
                            while(i < countDegreeForIterationForRotate){
                                if((i + 1) > countDegreeForIterationForRotate){
                                    actionBarDrawerData.getPointer().setRotation(actionBarDrawerData.getPointer().getRotation() + (countDegreeForIterationForRotate - i));
                                    break;
                                }
                                actionBarDrawerData.getPointer().setRotation(
                                        actionBarDrawerData.getPointer().getRotation() + 1);
                                i++;
                            }
                        } else {
                            while(i > countDegreeForIterationForRotate){
                                if((i - 1) < countDegreeForIterationForRotate){
                                    actionBarDrawerData.getPointer().setRotation(actionBarDrawerData.getPointer().getRotation() + (countDegreeForIterationForRotate - i));
                                    break;
                                }
                                actionBarDrawerData.getPointer().setRotation(
                                        actionBarDrawerData.getPointer().getRotation() - 1);
                                i--;
                            }
                        }
                    }
                });
            }
        };
        getViewState().setDrawerListener(actionBarDrawerToggle);
    }

}
