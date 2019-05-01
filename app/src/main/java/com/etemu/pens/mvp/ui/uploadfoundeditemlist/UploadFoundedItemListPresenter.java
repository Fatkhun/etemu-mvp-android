package com.etemu.pens.mvp.ui.uploadfoundeditemlist;

import com.etemu.pens.mvp.data.DataManager;
import com.etemu.pens.mvp.ui.base.BasePresenter;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListMvpPresenter;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListMvpView;
import com.etemu.pens.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class UploadFoundedItemListPresenter<V extends UploadFoundedItemListMvpView> extends BasePresenter<V>
        implements UploadFoundedItemListMvpPresenter<V> {

    @Inject
    public UploadFoundedItemListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
