package com.etemu.pens.mvp.ui.uploudmissingitemlist;

import android.widget.Spinner;

import com.etemu.pens.mvp.di.PerActivity;
import com.etemu.pens.mvp.ui.base.MvpPresenter;
import com.etemu.pens.mvp.ui.home.HomeMvpView;

@PerActivity
public interface UploadMissingItemListMvpPresenter<V extends UploadMissingItemListMvpView> extends MvpPresenter<V> {
    void getUploadMissingItem();
    void getCategoryItem(Spinner spinner);
}
