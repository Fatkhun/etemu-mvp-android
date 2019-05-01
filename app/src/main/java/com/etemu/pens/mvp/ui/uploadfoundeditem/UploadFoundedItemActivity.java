package com.etemu.pens.mvp.ui.uploadfoundeditem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class UploadFoundedItemActivity extends BaseActivity implements UploadFoundedItemMvpView {

    @Inject
    UploadFoundedItemMvpPresenter<UploadFoundedItemMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, UploadFoundedItemActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_founded_item);

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
