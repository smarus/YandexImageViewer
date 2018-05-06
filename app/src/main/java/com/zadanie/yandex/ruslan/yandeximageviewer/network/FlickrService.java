package com.zadanie.yandex.ruslan.yandeximageviewer.network;

import com.zadanie.yandex.ruslan.yandeximageviewer.PhotoWrapper;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ruslan on 03.05.18.
 */

public interface FlickrService {
    @GET("?method=flickr.photos.getRecent")
	Observable<PhotoWrapper> getRecentPhotos(@Query("page") int page);
}
