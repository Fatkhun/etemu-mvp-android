package com.etemu.pens.mvp.ui.claimmissingitem;

import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.ui.base.MvpView;

public interface ClaimMissingItemMvpView extends MvpView {
    void setFieldResult(UploadMissingItemResponse uploadMissingItemResponse);
}
