package com.etemu.pens.mvp.ui.claimmissingitem;

import com.etemu.pens.mvp.data.DataManager;
import com.etemu.pens.mvp.ui.base.BasePresenter;
import com.etemu.pens.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ClaimMissingItemPresenter<V extends ClaimMissingItemMvpView> extends BasePresenter<V>
        implements ClaimMissingItemMvpPresenter<V> {

    @Inject
    public ClaimMissingItemPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
