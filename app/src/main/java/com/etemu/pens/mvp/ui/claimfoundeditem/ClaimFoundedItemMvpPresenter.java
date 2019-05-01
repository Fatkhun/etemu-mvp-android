package com.etemu.pens.mvp.ui.claimfoundeditem;

import com.etemu.pens.mvp.di.PerActivity;
import com.etemu.pens.mvp.ui.base.MvpPresenter;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemMvpView;

@PerActivity
public interface ClaimFoundedItemMvpPresenter<V extends ClaimFoundedItemMvpView> extends MvpPresenter<V> {
}
