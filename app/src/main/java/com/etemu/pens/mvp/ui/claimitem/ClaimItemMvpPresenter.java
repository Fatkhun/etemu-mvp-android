package com.etemu.pens.mvp.ui.claimitem;

import com.etemu.pens.mvp.di.PerActivity;
import com.etemu.pens.mvp.ui.base.MvpPresenter;
import com.etemu.pens.mvp.ui.home.HomeMvpView;

@PerActivity
public interface ClaimItemMvpPresenter<V extends ClaimItemMvpView> extends MvpPresenter<V> {
}
