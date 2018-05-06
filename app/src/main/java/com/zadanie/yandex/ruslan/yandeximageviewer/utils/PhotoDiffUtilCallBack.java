package com.zadanie.yandex.ruslan.yandeximageviewer.utils;

import android.support.v7.util.DiffUtil;

import com.zadanie.yandex.ruslan.yandeximageviewer.model.Photo;

import java.util.List;

public class PhotoDiffUtilCallBack extends DiffUtil.Callback {

	private List<Photo> oldPhotos;
	private List<Photo> newPhotos;

	public PhotoDiffUtilCallBack(List<Photo> oldPhotos, List<Photo> newPhotos) {
		this.oldPhotos = oldPhotos;
		this.newPhotos = newPhotos;
	}

	@Override
	public int getOldListSize() {
		return oldPhotos.size();
	}

	@Override
	public int getNewListSize() {
		return newPhotos.size();
	}

	@Override
	public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
		return oldPhotos.get(oldItemPosition).getId().equals(newPhotos.get(newItemPosition).getId());
	}

	@Override
	public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
		Photo oldPhoto = oldPhotos.get(oldItemPosition);
		Photo newPhoto = newPhotos.get(newItemPosition);
		return (oldPhoto.getTitle().equalsIgnoreCase(newPhoto.getTitle()) && oldPhoto.getUrl_s().equals(newPhoto.getUrl_s()));
	}
}
