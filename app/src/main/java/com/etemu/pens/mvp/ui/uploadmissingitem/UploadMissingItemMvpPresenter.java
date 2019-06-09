package com.etemu.pens.mvp.ui.uploadmissingitem;

import android.graphics.Bitmap;
import android.widget.Spinner;

import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.di.PerActivity;
import com.etemu.pens.mvp.ui.base.MvpPresenter;
import com.etemu.pens.mvp.ui.splash.SplashMvpView;

@PerActivity
public interface UploadMissingItemMvpPresenter<V extends UploadMissingItemMvpView> extends MvpPresenter<V> {
    void setUploadMissingItem(String category, String detail, String contact, Bitmap imageFile);

    void getCategoryItem(Spinner spinner);
}
