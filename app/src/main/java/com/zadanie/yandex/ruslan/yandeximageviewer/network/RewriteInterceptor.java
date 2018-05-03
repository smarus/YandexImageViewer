package com.zadanie.yandex.ruslan.yandeximageviewer.network;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ruslan on 03.05.18.
 */
@Singleton
public class RewriteInterceptor implements Interceptor {

    @Inject
    public RewriteInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        int maxStale = 60 * 60 * 24 * 5;
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder().header("Cache-Control", "public, max-age=120, max-stale=" + maxStale).build();

    }
}
