package com.etemu.pens.mvp.ui.uploudmissingitemlist;

import com.etemu.pens.mvp.data.DataManager;
import com.etemu.pens.mvp.ui.base.BasePresenter;
import com.etemu.pens.mvp.ui.home.HomeMvpPresenter;
import com.etemu.pens.mvp.ui.home.HomeMvpView;
import com.etemu.pens.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class UploadMissingItemListPresenter<V extends UploadMissingItemListMvpView> extends BasePresenter<V>
        implements UploadMissingItemListMvpPresenter<V> {

    @Inject
    public UploadMissingItemListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
