package com.etemu.pens.mvp.ui.home;

import com.etemu.pens.mvp.di.PerActivity;
import com.etemu.pens.mvp.ui.base.MvpPresenter;
import com.etemu.pens.mvp.ui.splash.SplashMvpView;

@PerActivity
public interface HomeMvpPresenter<V extends HomeMvpView> extends MvpPresenter<V> {

}
