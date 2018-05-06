package com.zadanie.yandex.ruslan.yandeximageviewer.ui.adapter;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zadanie.yandex.ruslan.yandeximageviewer.R;
import com.zadanie.yandex.ruslan.yandeximageviewer.model.Photo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

	private List<Photo> photos;
	private OnClickPhotoListener photoListener;


	public PhotoAdapter(List<Photo> photos, OnClickPhotoListener photoListener) {
		this.photos = photos;
		this.photoListener = photoListener;
	}

	@Override
	public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
		return new PhotoHolder(view);
	}

	@Override
	public void onBindViewHolder(PhotoHolder holder, int position) {
		holder.title.setText(photos.get(position).getTitle());
		RequestOptions options = new RequestOptions()
				.centerCrop()
				.placeholder(R.drawable.default_photo)
				.error(R.drawable.error_image)
				.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
				.priority(Priority.HIGH);


		Glide.with(holder.itemView.getContext())
				.asBitmap()
				.load(photos.get(position).getUrl_s())
				.apply(options)
				.into(new BitmapImageViewTarget(holder.photo) {
					@Override
					public void onResourceReady(Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
						super.onResourceReady(bitmap, transition);
						Palette.from(bitmap).generate(palette -> holder.photoTitleBackground.setBackgroundColor(
								palette.getVibrantColor(holder.itemView.getContext().getResources().getColor(R.color.black_translucent_60))));
					}
				});

	}

	public List<Photo> getData() {
		return photos;
	}


	@Override
	public int getItemCount() {
		return photos.size();
	}

	public interface OnClickPhotoListener {
		void clickPhoto(Photo photo);
	}

	class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		@BindView(R.id.photo)
		ImageView photo;
		@BindView(R.id.photo_title_background)
		View photoTitleBackground;
		@BindView(R.id.photo_title)
		TextView title;

		public PhotoHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			photoListener.clickPhoto(photos.get(getAdapterPosition()));
		}
	}
}
