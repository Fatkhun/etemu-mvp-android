package com.etemu.pens.mvp.ui.uploudmissingitemlist;

import com.etemu.pens.mvp.data.network.model.UploadMissingItem;
import com.etemu.pens.mvp.ui.base.MvpView;

import java.util.List;

public interface UploadMissingItemListMvpView extends MvpView {
    void updateUploadMissingItemList(List<UploadMissingItem> uploadMissingItem);
}
