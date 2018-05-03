package com.zadanie.yandex.ruslan.yandeximageviewer.network;

import android.content.Context;

import com.zadanie.yandex.ruslan.yandeximageviewer.BuildConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by ruslan on 03.05.18.
 */
@Module
public class NetworkModule {
    public static final int CONNECT_TIMEOUT_IN_MS = 30000;

    @Provides
    @Singleton
    Interceptor authInterceptor(AuthInterceptor interceptor) {
        return interceptor;
    }

    @Provides
    @Singleton
    Cache provideCache(Context context){
        Cache cache = null;
        try {
            File dir = context.getCacheDir();
            cache = new Cache(new File(dir, "http-cache"), 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }

    @Provides
    @Singleton
    Interceptor requestInterceptor(RequestInterceptor requestInterceptor){return requestInterceptor; }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(AuthInterceptor authInterceptor,RequestInterceptor requestInterceptor
            ,RewriteInterceptor rewriteInterceptor,Cache cache) {
        HttpLoggingInterceptor HttploggingInterceptor = new HttpLoggingInterceptor();
        HttploggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new okhttp3.OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .cache(cache)
                .addInterceptor(HttploggingInterceptor)
                .addInterceptor(authInterceptor)
                .addInterceptor(requestInterceptor)
                .addNetworkInterceptor(rewriteInterceptor)
                .build();
    }

    @Singleton
    @Provides
    Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit
                .Builder()
                .baseUrl(BuildConfig.FLICKR_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    FlickrService FlickrService(Retrofit retrofit) {
        return retrofit.create(FlickrService.class);
    }
}
