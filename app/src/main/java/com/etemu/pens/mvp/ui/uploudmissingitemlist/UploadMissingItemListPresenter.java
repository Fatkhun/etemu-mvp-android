package com.etemu.pens.mvp.ui.uploudmissingitemlist;

import android.util.Log;
import android.widget.Spinner;

import com.androidnetworking.error.ANError;
import com.etemu.pens.mvp.data.DataManager;
import com.etemu.pens.mvp.ui.base.BasePresenter;
import com.etemu.pens.mvp.ui.home.HomeMvpPresenter;
import com.etemu.pens.mvp.ui.home.HomeMvpView;
import com.etemu.pens.mvp.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UploadMissingItemListPresenter<V extends UploadMissingItemListMvpView> extends BasePresenter<V>
        implements UploadMissingItemListMvpPresenter<V> {

    @Inject
    public UploadMissingItemListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getUploadMissingItem() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getUploadMissingItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataResponseList ->  {
                    if (!isViewAttached()){
                        return;
                    }
                    if (dataResponseList != null && dataResponseList.size() != 0 ){
                        getMvpView().updateUploadMissingItemList(dataResponseList);
                    }
                    getMvpView().hideLoading();
                    Log.d("Debug",dataResponseList.toString());
                }, throwable ->  {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    // handle the error here
                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        baseHandleError(anError);
                    }
                }));
    }

    @Override
    public void getCategoryItem(Spinner spinner) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getCategoryItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryItemResponse ->  {
                    if (!isViewAttached()) {
                        return;
                    }
                    if (categoryItemResponse != null && !categoryItemResponse.isEmpty()){
                        List<String> strings = new ArrayList<>();
                        for (int i=0;i < categoryItemResponse.size(); i++){
                            strings.add(categoryItemResponse.get(i).getName());
                        }
                        getMvpView().setupCategoryItem(strings,spinner);
                    }else {
                        getMvpView().showMessage("failed load category");
                    }
                    getMvpView().hideLoading();
                }, throwable ->  {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    // handle the login error here
                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        baseHandleError(anError);
                    }
                }));
    }
}
