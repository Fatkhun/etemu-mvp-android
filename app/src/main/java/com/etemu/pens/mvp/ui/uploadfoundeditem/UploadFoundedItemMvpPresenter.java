package com.etemu.pens.mvp.ui.uploadfoundeditem;

import android.graphics.Bitmap;
import android.widget.Spinner;

import com.etemu.pens.mvp.di.PerActivity;
import com.etemu.pens.mvp.ui.base.MvpPresenter;

@PerActivity
public interface UploadFoundedItemMvpPresenter<V extends UploadFoundedItemMvpView> extends MvpPresenter<V> {
    void setUploadFoundedItem(String category, String detail, String contact, Bitmap imageFile);
    void getCategoryItem(Spinner spinner);
}
