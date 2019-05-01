package com.etemu.pens.mvp.ui.uploadfoundeditem;

import com.etemu.pens.mvp.data.DataManager;
import com.etemu.pens.mvp.ui.base.BasePresenter;
import com.etemu.pens.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class UploadFoundedItemPresenter<V extends UploadFoundedItemMvpView> extends BasePresenter<V>
        implements UploadFoundedItemMvpPresenter<V> {

    @Inject
    public UploadFoundedItemPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
