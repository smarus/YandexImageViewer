package com.zadanie.yandex.ruslan.yandeximageviewer.presenter;

import com.zadanie.yandex.ruslan.yandeximageviewer.model.Photo;
import com.zadanie.yandex.ruslan.yandeximageviewer.view.MainView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenterImpl implements MainPresenter {


	public MainInteractorImpl mainInteractor;
	private Disposable disposable;
	private MainView view;
	private List<Photo> photos = new ArrayList<>();
	private int currentPage = 1;

	public MainPresenterImpl(MainInteractorImpl interactor) {
		this.mainInteractor = interactor;

	}

	@Override
	public void bind(MainView mainView) {
		view = mainView;
		loadPhotos();

	}

	@Override
	public void firstPage() {
		currentPage = 1;
		photos.clear();
		loadPhotos();

	}

	@Override
	public void nextPage() {

		currentPage++;
		if (currentPage == 10)
			currentPage = 1;
		loadPhotos();

	}

	@Override
	public void unbind() {
		view = null;
		if (disposable != null && !disposable.isDisposed())
			disposable.dispose();

	}

	private void loadPhotos() {
		disposable = mainInteractor.loadPhotos(currentPage)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnSubscribe(this::showProgress)
				.doAfterTerminate(this::hideProgress)
				.subscribe(this::fetchSuccess, this::fetchFailed);
	}

	private void fetchSuccess(List<Photo> newPhotos) {
		this.photos.addAll(newPhotos);
		if (view != null) {
			view.showPhotos(photos);
		}
	}

	private void fetchFailed(Throwable e) {
		if (isViewAttached())
			view.showError(e.getMessage());
	}

	private void showProgress(Disposable disposable) {
		if (isViewAttached())
			view.showLoading();
	}

	private void hideProgress() {
		if (isViewAttached())
			view.hideLoading();
	}

	private boolean isViewAttached() {
		return view != null;
	}

}
