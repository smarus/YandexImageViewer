package com.zadanie.yandex.ruslan.yandeximageviewer.di.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ruslan on 03.05.18.
 */
@Module
public class ApplicationModule {
    Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

        @Provides
        @Singleton
        public Context provideContext()
        {
            return context;
        }

        @Provides
        @Singleton
        public Resources provideResources(Context context)
        {
            return context.getResources();
        }
}
