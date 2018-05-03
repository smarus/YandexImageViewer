package com.zadanie.yandex.ruslan.yandeximageviewer;

import com.squareup.moshi.Json;
import com.zadanie.yandex.ruslan.yandeximageviewer.model.Photos;

/**
 * Created by ruslan on 04.05.18.
 */

public class PhotoWrapper {
    Photos photos;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }
}
