package com.etemu.pens.mvp.ui.claimmissingitem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.data.network.model.UploadMissingItem;
import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClaimMissingItemActivity extends BaseActivity implements ClaimMissingItemMvpView {

    @Inject
    ClaimMissingItemMvpPresenter<ClaimMissingItemMvpView> mPresenter;

    @BindView(R.id.tv_detail_category_missing_item)
    TextView tvDetailCategory;

    @BindView(R.id.tv_detail_desc_missing_item)
    TextView tvDetailDesc;

    @BindView(R.id.tv_detail_contact_missing_item)
    TextView tvDetailContact;

    @BindView(R.id.iv_detail_image_missing)
    ImageView ivImageMissing;


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
        imageViewPopUp();
        getIntentDetail();
    }

    private void getIntentDetail(){
        if (getIntent().getSerializableExtra("detail") != null){
            uploadMissingItemResponse = (UploadMissingItemResponse) getIntent().getSerializableExtra("detail");
            setFieldResult(uploadMissingItemResponse);
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

        imagePopup.initiatePopup(ivImageMissing.getDrawable());

        ivImageMissing.setOnClickListener(new View.OnClickListener() {
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
    public void setFieldResult(UploadMissingItemResponse uploadMissingItemResponse) {
        //decode base64 string to image
        final byte[] decodedBytes = Base64.decode(uploadMissingItemResponse.getItemImage(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        ivImageMissing.setImageBitmap(decodedImage);
        tvDetailCategory.setText(uploadMissingItemResponse.getCategory());
        tvDetailDesc.setText(uploadMissingItemResponse.getDetail());
        tvDetailContact.setText(uploadMissingItemResponse.getContact());
    }
}
