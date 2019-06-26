package com.etemu.pens.mvp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.ui.about.AboutActivity;
import com.etemu.pens.mvp.ui.base.BaseActivity;
import com.etemu.pens.mvp.ui.splash.SplashActivity;
import com.etemu.pens.mvp.ui.splash.SplashMvpPresenter;
import com.etemu.pens.mvp.ui.splash.SplashMvpView;
import com.etemu.pens.mvp.ui.uploadfoundeditem.UploadFoundedItemActivity;
import com.etemu.pens.mvp.ui.uploadfoundeditemlist.UploadFoundedItemListActivity;
import com.etemu.pens.mvp.ui.uploadmissingitem.UploadMissingItemActivity;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements HomeMvpView {

    @Inject
    HomeMvpPresenter<HomeMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        mToolbar.setTitle("Etemu");
        setSupportActionBar(mToolbar);
    }

    @OnClick(R.id.cv_upload_missing_item)
    void onClickUploadMissingItem(View v){
        startActivity(UploadMissingItemActivity.getStartIntent(this));
    }

    @OnClick(R.id.cv_upload_founded_item)
    void onClickUploadFoundedItem(View v){
        startActivity(UploadFoundedItemActivity.getStartIntent(this));
    }

    @OnClick(R.id.cv_upload_missing_item_list)
    void onClickUploadMissingItemList(View v){
        startActivity(UploadMissingItemListActivity.getStartIntent(this));
    }

    @OnClick(R.id.cv_upload_founded_item_list)
    void onClickUploadFoundedItemList(View v){
        startActivity(UploadFoundedItemListActivity.getStartIntent(this));
    }

    @OnClick(R.id.cv_about)
    void onClickAbout(View v){
        startActivity(AboutActivity.getStartIntent(this));
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
