package com.etemu.pens.mvp.ui.claimitem;

import com.etemu.pens.mvp.data.DataManager;
import com.etemu.pens.mvp.ui.base.BasePresenter;
import com.etemu.pens.mvp.ui.home.HomeMvpPresenter;
import com.etemu.pens.mvp.ui.home.HomeMvpView;
import com.etemu.pens.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ClaimItemPresenter<V extends ClaimItemMvpView> extends BasePresenter<V>
        implements ClaimItemMvpPresenter<V> {

    @Inject
    public ClaimItemPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
