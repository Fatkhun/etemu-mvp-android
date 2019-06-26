package com.etemu.pens.mvp.ui.uploadfoundeditem;

import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Spinner;

import com.androidnetworking.error.ANError;
import com.etemu.pens.mvp.data.DataManager;
import com.etemu.pens.mvp.ui.base.BasePresenter;
import com.etemu.pens.mvp.utils.rx.SchedulerProvider;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UploadFoundedItemPresenter<V extends UploadFoundedItemMvpView> extends BasePresenter<V>
        implements UploadFoundedItemMvpPresenter<V> {

    @Inject
    public UploadFoundedItemPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void setUploadFoundedItem(String category, String detail, String contact, Bitmap imageFile) {
        String encodedImage = "";

        if (category == null || category.isEmpty()){
            getMvpView().onError("required category");
            return;
        }

        if (detail == null || detail.isEmpty()){
            getMvpView().onError("required detail");
            return;
        }

        if (!isValidPhone(contact) && contact == null && contact.isEmpty()){
            getMvpView().onError("required contact");
            return;
        }else {
            if (contact.charAt(0) == '8'){
                contact = "628" + contact.substring(1, contact.length());
            }else if (contact.charAt(0) == '0'){
                contact = "62" + contact.substring(1, contact.length());
            }
        }

        if (imageFile == null ){
            getMvpView().onError("required image");
            return;
        }else {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageFile.compress(Bitmap.CompressFormat.JPEG, 50, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        }

        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .uploadFoundedItem(category,detail,contact,encodedImage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uploadFoundedItemResponse ->  {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    getMvpView().showMessage("success upload");
                    getMvpView().openBackActivity();
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

    @Override
    public Boolean isValidPhone(String phone) {
        String expression = "(\\()?(\\+62|628|08|8)(\\d{2,4})?\\)?[ .-]?\\d{2,7}";
        CharSequence inputString = phone;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (inputString.length() < 11){
            matcher.matches();
            return false;
        }else if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }
}
