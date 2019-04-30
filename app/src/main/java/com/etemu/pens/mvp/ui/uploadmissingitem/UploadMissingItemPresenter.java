package com.etemu.pens.mvp.ui.uploadmissingitem;

import com.etemu.pens.mvp.data.DataManager;
import com.etemu.pens.mvp.ui.base.BasePresenter;
import com.etemu.pens.mvp.ui.splash.SplashMvpPresenter;
import com.etemu.pens.mvp.ui.splash.SplashMvpView;
import com.etemu.pens.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class UploadMissingItemPresenter<V extends UploadMissingItemMvpView> extends BasePresenter<V>
        implements UploadMissingItemMvpPresenter<V> {

    @Inject
    public UploadMissingItemPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
