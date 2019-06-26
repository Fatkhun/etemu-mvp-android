package com.etemu.pens.mvp.ui.uploadfoundeditem;

import android.widget.Spinner;

import com.etemu.pens.mvp.ui.base.MvpView;

import java.util.List;

public interface UploadFoundedItemMvpView extends MvpView {
    void setupCategoryItem(List<String> stringList, Spinner spinner);

    void openBackActivity();
}
