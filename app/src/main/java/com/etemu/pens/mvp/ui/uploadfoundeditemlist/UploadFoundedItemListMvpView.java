package com.etemu.pens.mvp.ui.uploadfoundeditemlist;

import com.etemu.pens.mvp.data.network.model.UploadMissingItem;
import com.etemu.pens.mvp.ui.base.MvpView;

import java.util.List;

public interface UploadFoundedItemListMvpView extends MvpView {
    void updateUploadFoundedItemList(List<UploadMissingItem> uploadMissingItem);
}
