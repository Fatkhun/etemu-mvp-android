package com.etemu.pens.mvp.ui.uploadfoundeditemlist;

import android.widget.Spinner;

import com.etemu.pens.mvp.data.network.model.UploadFoundedItemResponse;
import com.etemu.pens.mvp.data.network.model.UploadMissingItem;
import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.ui.base.MvpView;

import java.util.List;

public interface UploadFoundedItemListMvpView extends MvpView {
    void updateUploadFoundedItemList(List<UploadFoundedItemResponse> uploadFoundedItem);
    void setupCategoryItem(List<String> stringList, Spinner spinner);
    void setupCategoryList(List<String> stringList);
}
