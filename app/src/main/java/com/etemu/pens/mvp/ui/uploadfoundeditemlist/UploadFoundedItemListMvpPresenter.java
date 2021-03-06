package com.etemu.pens.mvp.ui.uploadfoundeditemlist;


import android.widget.Spinner;

import com.etemu.pens.mvp.di.PerActivity;
import com.etemu.pens.mvp.ui.base.MvpPresenter;
import com.etemu.pens.mvp.ui.uploadmissingitem.UploadMissingItemMvpView;

@PerActivity
public interface UploadFoundedItemListMvpPresenter<V extends UploadFoundedItemListMvpView> extends MvpPresenter<V> {
    void getUploadFoundedItem();
    void getCategoryItem(Spinner spinner);
}
