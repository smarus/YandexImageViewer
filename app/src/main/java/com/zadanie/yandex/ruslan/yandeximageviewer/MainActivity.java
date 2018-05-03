package com.zadanie.yandex.ruslan.yandeximageviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zadanie.yandex.ruslan.yandeximageviewer.app.YandexImageApp;
import com.zadanie.yandex.ruslan.yandeximageviewer.di.component.AppComponent;
import com.zadanie.yandex.ruslan.yandeximageviewer.network.FlickrService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Inject
    FlickrService flickrService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YandexImageApp.getComponent().inject(this);

        Disposable disposable = flickrService.getRecentPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PhotoWrapper>() {
                    @Override
                    public void accept(PhotoWrapper photoWrapper) throws Exception {
                        Log.e("TAG",photoWrapper.getPhotos().getPhoto().size()+" ");
                    }
                });
    }
}
