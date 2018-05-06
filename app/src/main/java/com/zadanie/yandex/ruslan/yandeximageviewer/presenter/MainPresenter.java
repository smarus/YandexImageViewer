package com.zadanie.yandex.ruslan.yandeximageviewer.presenter;

import com.zadanie.yandex.ruslan.yandeximageviewer.view.MainView;

public interface MainPresenter {
	void bind(MainView mainView);

	void firstPage();

	void nextPage();

	void unbind();

}
