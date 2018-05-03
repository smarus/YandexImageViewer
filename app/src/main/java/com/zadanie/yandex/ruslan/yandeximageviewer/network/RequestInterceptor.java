package com.zadanie.yandex.ruslan.yandeximageviewer.network;

import android.content.Context;

import com.zadanie.yandex.ruslan.yandeximageviewer.utils.NetworkUtils;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ruslan on 03.05.18.
 */
@Singleton
public class RequestInterceptor implements Interceptor {
    private Context mContext;

    @Inject
    public RequestInterceptor(Context context) {
        mContext = context;
    }

    @Override
        public Response intercept(Chain chain) throws IOException {
            int maxStale = 60 * 60 * 24 * 5;
            Request request;
            if (NetworkUtils.isNetworkAvailable(mContext)) {
                request = chain.request();
            } else {
                request = chain.request().newBuilder().header("Cache-Control", "max-stale=" + maxStale).build();
            }
            return chain.proceed(request);
        }
}
