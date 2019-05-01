package com.etemu.pens.mvp.ui.uploadfoundeditemlist;

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
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListActivity;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListAdapter;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListMvpPresenter;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListMvpView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadFoundedItemListActivity extends BaseActivity implements UploadFoundedItemListMvpView, UploadFoundedItemListAdapter.Callback {

    @Inject
    UploadFoundedItemListAdapter mUploadFoundedItemListAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    UploadFoundedItemListMvpPresenter<UploadFoundedItemListMvpView> mPresenter;

    @BindView(R.id.rv_upload_founded_item)
    RecyclerView rvUploadFoundedItem;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, UploadFoundedItemListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_founded_item_list);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        mUploadFoundedItemListAdapter = new UploadFoundedItemListAdapter(new ArrayList<>(), this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvUploadFoundedItem.setLayoutManager(mLayoutManager);
        rvUploadFoundedItem.setItemAnimator(new DefaultItemAnimator());
        rvUploadFoundedItem.setAdapter(mUploadFoundedItemListAdapter);
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
    public void updateUploadFoundedItemList(List<UploadMissingItem> uploadMissingItem) {
        mUploadFoundedItemListAdapter.addItems(uploadMissingItem);
    }
}
