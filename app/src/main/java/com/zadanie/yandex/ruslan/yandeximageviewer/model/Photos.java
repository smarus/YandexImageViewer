package com.zadanie.yandex.ruslan.yandeximageviewer.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ruslan on 04.05.18.
 */

public class Photos  implements Parcelable {
    private int  page;
    private  int pages;
    private int perpage;
    private  int total;
    private List<Photo>photo;

    protected Photos(Parcel in) {
        page = in.readInt();
        pages = in.readInt();
        perpage = in.readInt();
        total = in.readInt();
        photo = in.createTypedArrayList(Photo.CREATOR);
    }

    public Photos(){

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(pages);
        dest.writeInt(perpage);
        dest.writeInt(total);
        dest.writeTypedList(photo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Photos> CREATOR = new Creator<Photos>() {
        @Override
        public Photos createFromParcel(Parcel in) {
            return new Photos(in);
        }

        @Override
        public Photos[] newArray(int size) {
            return new Photos[size];
        }
    };

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }
}
