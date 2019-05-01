package com.etemu.pens.mvp.ui.uploudmissingitemlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.data.network.model.UploadMissingItem;
import com.etemu.pens.mvp.ui.base.BaseActivity;
import com.etemu.pens.mvp.ui.home.HomeActivity;
import com.etemu.pens.mvp.ui.home.HomeMvpPresenter;
import com.etemu.pens.mvp.ui.home.HomeMvpView;

import org.jsoup.Connection;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadMissingItemListActivity extends BaseActivity implements UploadMissingItemListMvpView, UploadMissingItemListAdapter.Callback {

    @Inject
    UploadMissingItemListAdapter mUploadMissingItemListAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    UploadMissingItemListMvpPresenter<UploadMissingItemListMvpView> mPresenter;

    @BindView(R.id.rv_upload_missing_item)
    RecyclerView rvUploadMissingItem;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, UploadMissingItemListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_missing_item_list);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        mUploadMissingItemListAdapter = new UploadMissingItemListAdapter(new ArrayList<>(), this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvUploadMissingItem.setLayoutManager(mLayoutManager);
        rvUploadMissingItem.setItemAnimator(new DefaultItemAnimator());
        rvUploadMissingItem.setAdapter(mUploadMissingItemListAdapter);

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onBlogEmptyViewRetryClick() {

    }

    @Override
    public void updateUploadMissingItemList(List<UploadMissingItem> uploadMissingItem) {
        mUploadMissingItemListAdapter.addItems(uploadMissingItem);
    }
}
