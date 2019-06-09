package com.etemu.pens.mvp.ui.uploadmissingitem;

import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Spinner;

import com.androidnetworking.error.ANError;
import com.etemu.pens.mvp.data.DataManager;
import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.ui.base.BasePresenter;
import com.etemu.pens.mvp.ui.splash.SplashMvpPresenter;
import com.etemu.pens.mvp.ui.splash.SplashMvpView;
import com.etemu.pens.mvp.utils.rx.SchedulerProvider;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UploadMissingItemPresenter<V extends UploadMissingItemMvpView> extends BasePresenter<V>
        implements UploadMissingItemMvpPresenter<V> {

    @Inject
    public UploadMissingItemPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void setUploadMissingItem(String category, String detail, String contact, Bitmap imageFile) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageFile.compress(Bitmap.CompressFormat.JPEG, 50, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        if (category == null || category.isEmpty()){
            getMvpView().onError("required");
            return;
        }

        if (detail == null || detail.isEmpty()){
            getMvpView().onError("required");
            return;
        }

        if (contact == null || contact.isEmpty()){
            getMvpView().onError("required");
            return;
        }

        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .uploadMissingItem(category,detail,contact,encodedImage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uploadMissingItemResponse ->  {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    getMvpView().showMessage("success upload");
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
