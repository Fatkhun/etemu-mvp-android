package com.etemu.pens.mvp.ui.claimfoundeditem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.data.network.model.UploadFoundedItemResponse;
import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.ui.base.BaseActivity;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemActivity;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemMvpPresenter;
import com.etemu.pens.mvp.ui.claimmissingitem.ClaimMissingItemMvpView;

import java.net.URLEncoder;

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

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.btn_copy_contact_founded_item)
    Button btnCopy;

    @BindView(R.id.btn_add_contact_founded_item)
    Button btnAdd;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ClaimFoundedItemActivity.class);
        return intent;
    }

    UploadFoundedItemResponse uploadFoundedItemResponses;

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
        mToolbar.setTitle("Detail Barang Ditemukan");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        if (getActionBar() != null) getActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(mToolbar);
        btnCopy.setOnClickListener(v -> {
            openWhatsApp(uploadFoundedItemResponses.getContact(), "");
        });

        btnAdd.setOnClickListener(v -> {
            addPhoneNumber(uploadFoundedItemResponses.getContact());
        });
        getIntentDetail();
    }

    private void getIntentDetail(){
        if (getIntent().getSerializableExtra("detail") != null){
            uploadFoundedItemResponses = (UploadFoundedItemResponse) getIntent().getSerializableExtra("detail");
            setFieldResult(uploadFoundedItemResponses);
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void setFieldResult(UploadFoundedItemResponse uploadFoundedItemResponse) {
        uploadFoundedItemResponses = uploadFoundedItemResponse;
        //decode base64 string to image
        final byte[] decodedBytes = Base64.decode(uploadFoundedItemResponse.getItemImage(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        ivImageFounded.setImageBitmap(decodedImage);
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


        tvDetailCategory.setText(uploadFoundedItemResponse.getCategory());
        tvDetailDesc.setText(uploadFoundedItemResponse.getDetail());
        tvDetailContact.setText(uploadFoundedItemResponse.getContact());
    }
}
