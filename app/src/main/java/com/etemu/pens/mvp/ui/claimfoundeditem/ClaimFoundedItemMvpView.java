package com.etemu.pens.mvp.ui.claimfoundeditem;

import com.etemu.pens.mvp.data.network.model.UploadFoundedItemResponse;
import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.ui.base.MvpView;

public interface ClaimFoundedItemMvpView extends MvpView {
    void setFieldResult(UploadFoundedItemResponse uploadFoundedItemResponse);
}
