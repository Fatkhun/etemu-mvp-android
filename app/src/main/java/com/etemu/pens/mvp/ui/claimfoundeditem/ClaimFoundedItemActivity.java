package com.etemu.pens.mvp.ui.claimfoundeditem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.data.network.model.UploadFoundedItemResponse;
import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.ui.base.BaseActivity;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemActivity;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemMvpPresenter;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemMvpView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClaimFoundedItemActivity extends BaseActivity implements ClaimFoundedItemMvpView {

    @Inject
    ClaimFoundedItemMvpPresenter<ClaimFoundedItemMvpView> mPresenter;

    @BindView(R.id.tv_detail_category_founded_item)
    TextView tvDetailCategory;

    @BindView(R.id.tv_detail_desc_founded_item)
    TextView tvDetailDesc;

    @BindView(R.id.tv_detail_contact_founded_item)
    TextView tvDetailContact;

    @BindView(R.id.iv_detail_image_founded)
    ImageView ivImageFounded;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ClaimFoundedItemActivity.class);
        return intent;
    }

    UploadFoundedItemResponse uploadFoundedItemResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_founded_item);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        imageViewPopUp();
        getIntentDetail();
    }

    private void getIntentDetail(){
        if (getIntent().getSerializableExtra("detail") != null){
            uploadFoundedItemResponse = (UploadFoundedItemResponse) getIntent().getSerializableExtra("detail");
            setFieldResult(uploadFoundedItemResponse);
        }
    }

    private void imageViewPopUp(){
        final ImagePopup imagePopup = new ImagePopup(this);
        imagePopup.setWindowHeight(800); // Optional
        imagePopup.setWindowWidth(800); // Optional
        imagePopup.setBackgroundColor(Color.WHITE);  // Optional
        imagePopup.setFullScreen(true); // Optional
        imagePopup.setHideCloseIcon(false);  // Optional
        imagePopup.setImageOnClickClose(true);  // Optional

        imagePopup.initiatePopup(ivImageFounded.getDrawable());

        ivImageFounded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Initiate Popup view **/
                imagePopup.viewPopup();

            }
        });
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void setFieldResult(UploadFoundedItemResponse uploadFoundedItemResponse) {
        //decode base64 string to image
        final byte[] decodedBytes = Base64.decode(uploadFoundedItemResponse.getItemImage(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        ivImageFounded.setImageBitmap(decodedImage);
        tvDetailCategory.setText(uploadFoundedItemResponse.getCategory());
        tvDetailDesc.setText(uploadFoundedItemResponse.getDetail());
        tvDetailContact.setText(uploadFoundedItemResponse.getContact());
    }
}
