package com.litosh.ilya.cubingtimeproj.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.cubingtimeproj.db.models.MyObjectBox;

import io.objectbox.BoxStore;

/**
 * App класс приложения
 *
 * @author Ilya Litosh
 */
public class App extends Application {

    private static BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        ApiService.init();

        boxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public static BoxStore getDbSession() {
        return boxStore;
    }
}
