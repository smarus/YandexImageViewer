package com.zadanie.yandex.ruslan.yandeximageviewer.app;

import android.app.Application;
import android.os.StrictMode;

import com.zadanie.yandex.ruslan.yandeximageviewer.di.component.AppComponent;
import com.zadanie.yandex.ruslan.yandeximageviewer.di.component.DaggerAppComponent;
import com.zadanie.yandex.ruslan.yandeximageviewer.di.module.ApplicationModule;
import com.zadanie.yandex.ruslan.yandeximageviewer.network.NetworkModule;

/**
 * Created by ruslan on 03.05.18.
 */

public class YandexImageApp extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.enableDefaults();
        sAppComponent = createAppComponent();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public static AppComponent getComponent(){
        return sAppComponent;
    }

}
