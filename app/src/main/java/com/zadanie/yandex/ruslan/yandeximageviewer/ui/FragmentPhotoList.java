package com.zadanie.yandex.ruslan.yandeximageviewer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.zadanie.yandex.ruslan.yandeximageviewer.R;
import com.zadanie.yandex.ruslan.yandeximageviewer.app.YandexImageApp;
import com.zadanie.yandex.ruslan.yandeximageviewer.model.Photo;
import com.zadanie.yandex.ruslan.yandeximageviewer.presenter.MainInteractorImpl;
import com.zadanie.yandex.ruslan.yandeximageviewer.presenter.MainPresenterImpl;
import com.zadanie.yandex.ruslan.yandeximageviewer.ui.adapter.PhotoAdapter;
import com.zadanie.yandex.ruslan.yandeximageviewer.view.MainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentPhotoList extends Fragment implements MainView, PhotoAdapter.OnClickPhotoListener {

	public static final String PHOTO = FragmentPhotoList.class.getName();

	Unbinder unbinder;
	@Inject
	MainInteractorImpl interactor;
	MainPresenterImpl mainPresenter;
	@BindView(R.id.list_photos)
	RecyclerView photoRecyclerView;
	@BindView(R.id.progress_bar)
	ProgressBar progressBar;
	private PhotoAdapter adapter;
	private List<Photo> photos = new ArrayList<>();

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		setRetainInstance(true);
		YandexImageApp.getComponent().inject(this);
		mainPresenter = new MainPresenterImpl(interactor);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (savedInstanceState != null) {
			photos = savedInstanceState.getParcelableArrayList(PHOTO);
			adapter.notifyDataSetChanged();
			photoRecyclerView.setVisibility(View.VISIBLE);
		} else {
			mainPresenter.bind(this);
		}
	}


	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelableArrayList(PHOTO, (ArrayList<? extends Parcelable>) photos);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_photo_list, container, false);
		unbinder = ButterKnife.bind(this, view);
		initRecycler();
		return view;
	}

	@Override
	public void showPhotos(List<Photo> photos) {
		this.photos.clear();
		this.photos.addAll(photos);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void showLoading() {
		Snackbar.make(photoRecyclerView, R.string.loading_photos, Snackbar.LENGTH_LONG).show();
		progressBar.setVisibility(View.VISIBLE);

	}

	@Override
	public void showError(String errorMessage) {
		Snackbar.make(photoRecyclerView, errorMessage, Snackbar.LENGTH_INDEFINITE).show();

	}

	@Override
	public void hideLoading() {
		progressBar.setVisibility(View.GONE);
	}

	private void initRecycler() {
		photoRecyclerView.setHasFixedSize(true);
		int columns = getResources().getInteger(R.integer.no_of_columns);
		RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), columns);
		photoRecyclerView.setLayoutManager(layoutManager);
		adapter = new PhotoAdapter(photos, this);
		photoRecyclerView.setAdapter(adapter);
		photoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				if (!recyclerView.canScrollVertically(1)) {
					mainPresenter.nextPage();
				}
			}
		});
	}

	@Override
	public void clickPhoto(Photo photo) {
		Intent intent = new Intent(getActivity(), FullScreenActivity.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable(PHOTO, photo);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mainPresenter.unbind();
		unbinder.unbind();
	}
}
