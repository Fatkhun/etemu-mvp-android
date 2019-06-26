/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.etemu.pens.mvp.di.module;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.etemu.pens.mvp.di.ActivityContext;
import com.etemu.pens.mvp.di.PerActivity;
import com.etemu.pens.mvp.ui.about.AboutMvpPresenter;
import com.etemu.pens.mvp.ui.about.AboutMvpView;
import com.etemu.pens.mvp.ui.about.AboutPresenter;
import com.etemu.pens.mvp.ui.claimfoundeditem.ClaimFoundedItemMvpPresenter;
import com.etemu.pens.mvp.ui.claimfoundeditem.ClaimFoundedItemMvpView;
import com.etemu.pens.mvp.ui.claimfoundeditem.ClaimFoundedItemPresenter;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemMvpPresenter;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemMvpView;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemPresenter;
import com.etemu.pens.mvp.ui.home.HomeMvpPresenter;
import com.etemu.pens.mvp.ui.home.HomeMvpView;
import com.etemu.pens.mvp.ui.home.HomePresenter;
import com.etemu.pens.mvp.ui.splash.SplashMvpPresenter;
import com.etemu.pens.mvp.ui.splash.SplashMvpView;
import com.etemu.pens.mvp.ui.splash.SplashPresenter;
import com.etemu.pens.mvp.ui.uploadfoundeditem.UploadFoundedItemMvpPresenter;
import com.etemu.pens.mvp.ui.uploadfoundeditem.UploadFoundedItemMvpView;
import com.etemu.pens.mvp.ui.uploadfoundeditem.UploadFoundedItemPresenter;
import com.etemu.pens.mvp.ui.uploadfoundeditemlist.UploadFoundedItemListAdapter;
import com.etemu.pens.mvp.ui.uploadfoundeditemlist.UploadFoundedItemListMvpPresenter;
import com.etemu.pens.mvp.ui.uploadfoundeditemlist.UploadFoundedItemListMvpView;
import com.etemu.pens.mvp.ui.uploadfoundeditemlist.UploadFoundedItemListPresenter;
import com.etemu.pens.mvp.ui.uploadmissingitem.UploadMissingItemMvpPresenter;
import com.etemu.pens.mvp.ui.uploadmissingitem.UploadMissingItemMvpView;
import com.etemu.pens.mvp.ui.uploadmissingitem.UploadMissingItemPresenter;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListAdapter;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListMvpPresenter;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListMvpView;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListPresenter;
import com.etemu.pens.mvp.utils.rx.AppSchedulerProvider;
import com.etemu.pens.mvp.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by janisharali on 27/01/17.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;
    Context context;
    Cursor cursor;
    String type;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    HomeMvpPresenter<HomeMvpView> provideHomeMvpPresenter(HomePresenter<HomeMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    UploadMissingItemMvpPresenter<UploadMissingItemMvpView> provideUploadMissingItemPresenter(UploadMissingItemPresenter<UploadMissingItemMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    UploadFoundedItemMvpPresenter<UploadFoundedItemMvpView> provideUploadFoundedItemPresenter(UploadFoundedItemPresenter<UploadFoundedItemMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    ClaimMissingItemMvpPresenter<ClaimMissingItemMvpView> provideClaimItemPresenter(ClaimMissingItemPresenter<ClaimMissingItemMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    ClaimFoundedItemMvpPresenter<ClaimFoundedItemMvpView> provideClaimFoundedItemPresenter(ClaimFoundedItemPresenter<ClaimFoundedItemMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    UploadMissingItemListMvpPresenter<UploadMissingItemListMvpView> provideUploadMissingItemListPresenter(UploadMissingItemListPresenter<UploadMissingItemListMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    UploadFoundedItemListMvpPresenter<UploadFoundedItemListMvpView> provideUploadFoundedItemListPresenter(UploadFoundedItemListPresenter<UploadFoundedItemListMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    AboutMvpPresenter<AboutMvpView> provideAboutPresenter(AboutPresenter<AboutMvpView> presenter){
        return presenter;
    }

    @Provides
    UploadMissingItemListAdapter provideUploadMissingItemListAdapter(){
        return new UploadMissingItemListAdapter(new ArrayList<>(), context, type);
    }

    @Provides
    UploadFoundedItemListAdapter provideUploadFoundedItemListAdapter(){
        return new UploadFoundedItemListAdapter(new ArrayList<>(), context);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
