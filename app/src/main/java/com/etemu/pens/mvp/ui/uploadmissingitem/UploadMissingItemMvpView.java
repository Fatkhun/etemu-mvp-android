package com.etemu.pens.mvp.ui.uploadmissingitem;

import android.widget.Spinner;

import com.etemu.pens.mvp.data.network.model.CategoryItemResponse;
import com.etemu.pens.mvp.ui.base.MvpView;

import java.util.List;

public interface UploadMissingItemMvpView extends MvpView {

    void setupCategoryItem(List<String> stringList, Spinner spinner);

    void openBackActivity();
}
