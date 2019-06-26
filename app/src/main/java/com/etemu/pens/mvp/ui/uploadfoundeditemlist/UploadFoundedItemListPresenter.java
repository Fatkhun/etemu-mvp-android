package com.etemu.pens.mvp.ui.uploadfoundeditemlist;

import android.util.Log;
import android.widget.Spinner;

import com.androidnetworking.error.ANError;
import com.etemu.pens.mvp.data.DataManager;
import com.etemu.pens.mvp.ui.base.BasePresenter;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListMvpPresenter;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListMvpView;
import com.etemu.pens.mvp.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UploadFoundedItemListPresenter<V extends UploadFoundedItemListMvpView> extends BasePresenter<V>
        implements UploadFoundedItemListMvpPresenter<V> {

    @Inject
    public UploadFoundedItemListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getUploadFoundedItem() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getUploadFoundedItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataResponseList ->  {
                    if (!isViewAttached()){
                        return;
                    }
                    if (dataResponseList != null && dataResponseList.size() != 0 ){
                        getMvpView().updateUploadFoundedItemList(dataResponseList);
                    }else {
                        getMvpView().showMessage("load failed");
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
                        getMvpView().setupCategoryList(strings);
                        getMvpView().setupCategoryItem(strings,spinner);
                        Log.d("TAG", "getCategoryItem: " + strings);
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
