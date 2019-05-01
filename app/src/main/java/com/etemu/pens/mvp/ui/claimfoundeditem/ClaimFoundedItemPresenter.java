package com.etemu.pens.mvp.ui.claimfoundeditem;

import com.etemu.pens.mvp.data.DataManager;
import com.etemu.pens.mvp.ui.base.BasePresenter;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemMvpPresenter;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemMvpView;
import com.etemu.pens.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ClaimFoundedItemPresenter<V extends ClaimFoundedItemMvpView> extends BasePresenter<V>
        implements ClaimFoundedItemMvpPresenter<V> {

    @Inject
    public ClaimFoundedItemPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
