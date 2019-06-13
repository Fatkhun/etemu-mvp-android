package com.etemu.pens.mvp.ui.uploudmissingitemlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.data.network.model.UploadMissingItem;
import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.ui.base.BaseActivity;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemActivity;
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

    @BindView(R.id.sp_category_item)
    Spinner spCategoryItem;

    List<UploadMissingItemResponse> uploadMissingItemResponses;

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
        mUploadMissingItemListAdapter.setCallback(this);


        mPresenter.getUploadMissingItem();
        mPresenter.getCategoryItem(spCategoryItem);
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
    public void onItemLocationListClick(int position) {
        Intent intent = ClaimMissingItemActivity.getStartIntent(this);
        intent.putExtra("detail", uploadMissingItemResponses.get(position));
        startActivity(intent);
    }

    @Override
    public void updateUploadMissingItemList(List<UploadMissingItemResponse> uploadMissingItem) {
        uploadMissingItemResponses = uploadMissingItem;
        mUploadMissingItemListAdapter.addItems(uploadMissingItem);
        mUploadMissingItemListAdapter.setCallback(this);
    }

    @Override
    public void setupCategoryItem(List<String> stringList, Spinner spinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, stringList);
        // muncul pilihan brand
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set adapter
        spinner.setAdapter(adapter);
    }
}
