package com.zadanie.yandex.ruslan.yandeximageviewer.network;

import com.zadanie.yandex.ruslan.yandeximageviewer.BuildConfig;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ruslan on 03.05.18.
 */

@Singleton
public class AuthInterceptor implements Interceptor {

    @Inject
    public AuthInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.FLICKR_KEY)
                .addQueryParameter("format","json")
                .addQueryParameter("nojsoncallback","1")
                .addQueryParameter("extras","url_s")
                .build();
        Request request = original.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
