package com.etemu.pens.mvp.ui.about;

import com.etemu.pens.mvp.di.PerActivity;
import com.etemu.pens.mvp.ui.base.MvpPresenter;
import com.etemu.pens.mvp.ui.home.HomeMvpView;

@PerActivity
public interface AboutMvpPresenter<V extends AboutMvpView> extends MvpPresenter<V> {
}
