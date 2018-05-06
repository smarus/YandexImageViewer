package com.zadanie.yandex.ruslan.yandeximageviewer.presenter;

import com.zadanie.yandex.ruslan.yandeximageviewer.model.Photo;
import com.zadanie.yandex.ruslan.yandeximageviewer.network.FlickrService;

import java.util.List;

import io.reactivex.Observable;

public class MainInteractorImpl {

	private FlickrService flickrService;

	public MainInteractorImpl(FlickrService flickrService) {
		this.flickrService = flickrService;
	}

	public Observable<List<Photo>> loadPhotos(int page) {
		return flickrService.getRecentPhotos(page).map(photoWrapper -> photoWrapper.getPhotos().getPhoto());
	}
}
