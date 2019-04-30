package com.etemu.pens.mvp.ui.uploadmissingitem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.ui.base.BaseActivity;
import com.etemu.pens.mvp.ui.splash.SplashActivity;
import com.etemu.pens.mvp.ui.splash.SplashMvpPresenter;
import com.etemu.pens.mvp.ui.splash.SplashMvpView;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class UploadMissingItemActivity extends BaseActivity implements UploadMissingItemMvpView {

    @Inject
    UploadMissingItemMvpPresenter<UploadMissingItemMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, UploadMissingItemActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_missing_item);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
