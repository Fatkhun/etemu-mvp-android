package com.etemu.pens.mvp.ui.uploudmissingitemlist;

import android.widget.Spinner;

import com.etemu.pens.mvp.data.network.model.UploadMissingItem;
import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.ui.base.MvpView;

import java.util.List;


public interface UploadMissingItemListMvpView extends MvpView {
    void updateUploadMissingItemList(List<UploadMissingItemResponse> uploadMissingItem);
    void setupCategoryItem(List<String> stringList, Spinner spinner);
    void setupCategoryList(List<String> stringList);
}
