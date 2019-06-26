package com.etemu.pens.mvp.ui.claimmissingitem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.data.network.model.UploadFoundedItemResponse;
import com.etemu.pens.mvp.data.network.model.UploadMissingItem;
import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.ui.base.BaseActivity;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.btn_copy_contact_missing_item)
    Button btnCopy;

    @BindView(R.id.btn_add_contact_missing_item)
    Button btnAdd;


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
        mToolbar.setTitle("Detail Barang Hilang");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        if (getActionBar() != null) getActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(mToolbar);

        btnCopy.setOnClickListener(v -> {
            openWhatsApp(uploadMissingItemResponse.getContact(), "");
        });

        btnAdd.setOnClickListener(v -> {
            addPhoneNumber(uploadMissingItemResponse.getContact());
        });

        getIntentDetail();
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void addPhoneNumber(String phone){
        Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
        contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        contactIntent
                .putExtra(ContactsContract.Intents.Insert.PHONE, phone);

        startActivityForResult(contactIntent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1)
        {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Added Contact", Toast.LENGTH_SHORT).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled Added Contact", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openWhatsApp(String phone,String message){

        try{
            PackageManager packageManager = getApplicationContext().getPackageManager();
            Intent i = new Intent(Intent.ACTION_VIEW);
            String url = "https://api.whatsapp.com/send?phone="+ phone +"&text=" + URLEncoder.encode(message, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                startActivity(i);
            }else {
                Toast.makeText(getApplicationContext(), phone, Toast.LENGTH_SHORT).show();
            }
        } catch(Exception e) {
            Log.e("ERROR WHATSAPP",e.toString());
            Toast.makeText(getApplicationContext(), phone, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void setFieldResult(UploadMissingItemResponse uploadMissingItemResponses) {
        uploadMissingItemResponse = uploadMissingItemResponses;
        //decode base64 string to image
        final byte[] decodedBytes = Base64.decode(uploadMissingItemResponses.getItemImage(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        ivImageMissing.setImageBitmap(decodedImage);
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



        tvDetailCategory.setText(uploadMissingItemResponses.getCategory());
        tvDetailDesc.setText(uploadMissingItemResponses.getDetail());
        tvDetailContact.setText(uploadMissingItemResponses.getContact());
    }
}
