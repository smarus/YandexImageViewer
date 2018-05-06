package com.zadanie.yandex.ruslan.yandeximageviewer.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.zadanie.yandex.ruslan.yandeximageviewer.R
import com.zadanie.yandex.ruslan.yandeximageviewer.model.Photo
import kotlinx.android.synthetic.main.activity_full_screen.*


class FullScreenActivity : AppCompatActivity() {

    lateinit var photo: Photo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)
        photo = intent.extras.getParcelable(FragmentPhotoList.PHOTO)

    }

    override fun onResume() {
        super.onResume()
        val options = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.default_photo)
                .error(R.drawable.error_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
        Glide.with(this)
                .applyDefaultRequestOptions(options)
                .load(photo.url_s)
                .into(full_image)
    }
}
