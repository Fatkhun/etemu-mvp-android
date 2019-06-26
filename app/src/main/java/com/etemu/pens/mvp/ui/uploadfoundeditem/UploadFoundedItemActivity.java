package com.etemu.pens.mvp.ui.uploadfoundeditem;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.ui.base.BaseActivity;
import com.etemu.pens.mvp.ui.home.HomeActivity;
import com.etemu.pens.mvp.utils.KeyboardUtils;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadFoundedItemActivity extends BaseActivity implements UploadFoundedItemMvpView {

    @Inject
    UploadFoundedItemMvpPresenter<UploadFoundedItemMvpView> mPresenter;

    @BindView(R.id.btn_take_photo_founded_item)
    Button btnTakePhoto;

    @BindView(R.id.btn_cancel_founded_item)
    Button btnCancel;

    @BindView(R.id.iv_founded_item)
    ImageView ivFoundedItem;

    @BindView(R.id.btn_save_founded_item)
    Button btnSaveFoundedItem;

    @BindView(R.id.sp_category_founded_item)
    Spinner spCategoryItem;

    @BindView(R.id.et_detail_founded_item)
    EditText etDetailFoundedItem;

    @BindView(R.id.et_contact_founded_item)
    EditText etContactFoundedItem;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, UploadFoundedItemActivity.class);
        return intent;
    }

    Uri mCropImageUri, resultUri;
    Bitmap bitmap = null;

    String mCategory, mDetail, mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_founded_item);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        mToolbar.setTitle("Upload Barang Ditemukan");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        if (getActionBar() != null) getActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(mToolbar);
        btnTakePhoto.setOnClickListener(v -> {
            onSelectImageClick(ivFoundedItem);
        });
        btnCancel.setOnClickListener(v -> {
            deleteCache(this);
            ivFoundedItem.setImageBitmap(null);
        });

        btnSaveFoundedItem.setOnClickListener(v -> {
            mCategory = spCategoryItem.getSelectedItem().toString();
            mDetail = etDetailFoundedItem.getText().toString();
            mContact = etContactFoundedItem.getText().toString();
            mPresenter.setUploadFoundedItem(mCategory, mDetail, mContact, bitmap);
        });

        mPresenter.getCategoryItem(spCategoryItem);

    }

    // clear cache
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            } else if(dir.isFile()) {
                dir.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    @SuppressLint("NewApi")
    public void onSelectImageClick(View view) {
        if (CropImage.isExplicitCameraPermissionRequired(this)) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
        } else {
            CropImage.startPickImageActivity(this);
        }
    }

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // handle result of pick image chooser
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);
            // For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                mCropImageUri = imageUri;
                Toast.makeText(this, "Permission dibutuhkan untuk mengambil gambar", Toast.LENGTH_SHORT).show();
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},   CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            }else {
                // no permissions required or already granted, can start crop image activity
                startCropImageActivity(imageUri);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Log.d("DEbug","Kepanggil");
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos); //bm is the bitmap object
                    byte[] b = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                    //decode base64 string to image
                    b = Base64.decode(encodedImage, Base64.DEFAULT);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(b, 0, b.length);
                    ivFoundedItem.setImageBitmap(decodedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Log.d("Error",error.toString());
            }
        }
    }

    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setCropShape(CropImageView.CropShape.RECTANGLE)
                .setMinCropWindowSize(0,0)
                .setMinCropResultSize(100,100)
                .setMaxCropResultSize(1000,1000)
                .setShowCropOverlay(true)
                .start(this);
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
    public void openBackActivity() {
        Intent intent = HomeActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }
}
