package com.zadanie.yandex.ruslan.yandeximageviewer.photoUnitTest;


import com.zadanie.yandex.ruslan.yandeximageviewer.RxSchedulerRule;
import com.zadanie.yandex.ruslan.yandeximageviewer.model.Photo;
import com.zadanie.yandex.ruslan.yandeximageviewer.presenter.MainInteractorImpl;
import com.zadanie.yandex.ruslan.yandeximageviewer.presenter.MainPresenterImpl;
import com.zadanie.yandex.ruslan.yandeximageviewer.view.MainView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author arunsasidharan
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterImplTest {
	@Rule
	public RxSchedulerRule rule = new RxSchedulerRule();

	@Mock
	private MainView view;

	private List<Photo> photos = new ArrayList<>(0);

	@Mock
	private MainInteractorImpl interactor;

	private MainPresenterImpl mainPresenter;


	@Before
	public void setUp() {
		mainPresenter = new MainPresenterImpl(interactor);
	}

	@After
	public void teardown() {
		mainPresenter.unbind();
	}

	@Test
	public void shouldBeAbleToDisplayMovies() {
		// given:
		Observable<List<Photo>> responseObservable = Observable.just(photos);
		when(mainPresenter.mainInteractor.loadPhotos(anyInt())).thenReturn(responseObservable);

		// when:
		mainPresenter.bind(view);

		// then:
		verify(view).showPhotos(photos);
	}
}