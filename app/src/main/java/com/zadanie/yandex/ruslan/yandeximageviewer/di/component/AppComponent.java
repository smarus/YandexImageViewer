package com.zadanie.yandex.ruslan.yandeximageviewer.di.component;

import com.zadanie.yandex.ruslan.yandeximageviewer.MainActivity;
import com.zadanie.yandex.ruslan.yandeximageviewer.di.module.ApplicationModule;
import com.zadanie.yandex.ruslan.yandeximageviewer.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ruslan on 03.05.18.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
}
