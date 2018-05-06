package com.zadanie.yandex.ruslan.yandeximageviewer.view;

import com.zadanie.yandex.ruslan.yandeximageviewer.model.Photo;

import java.util.List;

public interface MainView {
	void showPhotos(List<Photo> photos);

	void showLoading();

	void showError(String errorMessage);

	void hideLoading();

}
