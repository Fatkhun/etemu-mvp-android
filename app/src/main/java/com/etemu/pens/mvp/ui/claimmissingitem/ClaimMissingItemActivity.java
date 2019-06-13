package com.etemu.pens.mvp.ui.claimmissingitem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.data.network.model.UploadMissingItem;
import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ClaimMissingItemActivity extends BaseActivity implements ClaimMissingItemMvpView {

    @Inject
    ClaimMissingItemMvpPresenter<ClaimMissingItemMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ClaimMissingItemActivity.class);
        return intent;
    }

    UploadMissingItemResponse uploadMissingItemResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_missing_item);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {

    }

    private void getIntentDetail(){
        if (getIntent().getSerializableExtra("detail") != null){
            uploadMissingItemResponse = (UploadMissingItemResponse) getIntent().getSerializableExtra("detail");
            setFieldResult(uploadMissingItemResponse);
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void setFieldResult(UploadMissingItemResponse uploadMissingItemResponse) {

    }
}
