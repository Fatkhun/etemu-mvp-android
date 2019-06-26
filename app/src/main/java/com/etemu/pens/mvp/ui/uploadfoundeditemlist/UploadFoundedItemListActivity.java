package com.etemu.pens.mvp.ui.uploadfoundeditemlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.data.network.model.UploadFoundedItemResponse;
import com.etemu.pens.mvp.data.network.model.UploadMissingItem;
import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.ui.base.BaseActivity;
import com.etemu.pens.mvp.ui.claimfoundeditem.ClaimFoundedItemActivity;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemActivity;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListActivity;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListAdapter;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListMvpPresenter;
import com.etemu.pens.mvp.ui.uploudmissingitemlist.UploadMissingItemListMvpView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

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

    @BindView(R.id.sp_category_founded_item_list)
    Spinner spCategoryItem;

    @BindView(R.id.sv_founded_item)
    MaterialSearchView searchView;

    @BindView(R.id.toolbars)
    Toolbar mToolbar;

    List<UploadFoundedItemResponse> uploadFoundedItemResponses;
    List<String> type = new ArrayList<>();

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
        mToolbar.setTitle("Detail");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        if (getActionBar() != null) getActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(mToolbar);

        mUploadFoundedItemListAdapter = new UploadFoundedItemListAdapter(new ArrayList<>(), this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvUploadFoundedItem.setLayoutManager(mLayoutManager);
        rvUploadFoundedItem.setItemAnimator(new DefaultItemAnimator());
        rvUploadFoundedItem.setAdapter(mUploadFoundedItemListAdapter);
        mUploadFoundedItemListAdapter.setCallback(this);

        //search
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                try{
                    mUploadFoundedItemListAdapter.filterByName(query);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try{
                    mUploadFoundedItemListAdapter.filterByName(newText);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });


        mPresenter.getUploadFoundedItem();
        mPresenter.getCategoryItem(spCategoryItem);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onBlogEmptyViewRetryClick() {
        mPresenter.getUploadFoundedItem();
    }

    @Override
    public void onItemLocationListClick(int position) {
        Intent intent = ClaimFoundedItemActivity.getStartIntent(this);
        intent.putExtra("detail", uploadFoundedItemResponses.get(position));
        startActivity(intent);
    }

    @Override
    public void updateUploadFoundedItemList(List<UploadFoundedItemResponse> uploadFoundedItem) {
        uploadFoundedItemResponses = uploadFoundedItem;
        mUploadFoundedItemListAdapter.addItems(uploadFoundedItem);
        mUploadFoundedItemListAdapter.notifyDataSetChanged();
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

    @Override
    public void setupCategoryList(List<String> stringList) {
        type = stringList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem item = menu.findItem(R.id.action_search_item);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            mPresenter.getUploadFoundedItem();
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
