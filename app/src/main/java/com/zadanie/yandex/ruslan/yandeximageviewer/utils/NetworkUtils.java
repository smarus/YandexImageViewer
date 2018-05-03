package com.zadanie.yandex.ruslan.yandeximageviewer.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by ruslan on 03.05.18.
 */

public class NetworkUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null;
    }
}
