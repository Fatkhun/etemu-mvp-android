package com.etemu.pens.mvp.ui.about;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.ui.base.BaseActivity;
import com.etemu.pens.mvp.ui.home.HomeActivity;
import com.etemu.pens.mvp.ui.home.HomeMvpPresenter;
import com.etemu.pens.mvp.ui.home.HomeMvpView;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity implements AboutMvpView {

    @Inject
    AboutMvpPresenter<AboutMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

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
