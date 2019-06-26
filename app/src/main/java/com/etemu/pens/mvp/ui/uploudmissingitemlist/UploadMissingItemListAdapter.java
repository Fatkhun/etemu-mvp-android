package com.etemu.pens.mvp.ui.uploudmissingitemlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.etemu.pens.mvp.R;
import com.etemu.pens.mvp.data.network.model.CategoryItemResponse;
import com.etemu.pens.mvp.data.network.model.UploadMissingItem;
import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.ui.base.BaseViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.android.gms.internal.zzs.TAG;

public class UploadMissingItemListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;


    UploadMissingItemListAdapter.Callback mCallback;
    private List<UploadMissingItemResponse> mData;
    List<UploadMissingItemResponse> mDataDefault;
    String mType;
    Context context;

    public UploadMissingItemListAdapter(List<UploadMissingItemResponse> dataResponseList, Context context, String Type) {
        mData = dataResponseList;
        this.context = context;
        mType = Type;
    }

    public void setCallback(UploadMissingItemListAdapter.Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new UploadMissingItemListAdapter.ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upload_missing_view, parent, false));
            case VIEW_TYPE_EMPTY:
                return new UploadMissingItemListAdapter.EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
            default:
                return new UploadMissingItemListAdapter.EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mData != null && mData.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mData != null && mData.size() > 0) {
            return mData.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<UploadMissingItemResponse> dataResponses) {
        mData.clear();
        mData.addAll(dataResponses);
        mData = dataResponses;
        notifyDataSetChanged();
    }

    public void filterByName(String query){
        List<UploadMissingItemResponse> filtered = new ArrayList<>();
        for (UploadMissingItemResponse itemResponse : mData){
            if (itemResponse.getDetail().toLowerCase().contains(query.toLowerCase()) ){
                filtered.add(itemResponse);
            }
        }
        mData.clear();
        mData.addAll(filtered);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onBlogEmptyViewRetryClick();
        void onItemLocationListClick(int position);
    }

    public class ViewHolder extends BaseViewHolder {

        ImageView imageView;
        TextView tvDesc;
        TextView tvTime;
        Spinner spinner;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_upload_missing_item_list);
            tvDesc = itemView.findViewById(R.id.tv_desc_upload_missing_item_list);
            tvTime = itemView.findViewById(R.id.tv_time_missing_item_list);
            spinner = itemView.findViewById(R.id.sp_category_item);

        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);

            UploadMissingItemResponse item = mData.get(position);
            Log.d("Debug", mData.toString());

            Log.d(TAG, "onBind: " + mType);


//            if (mType.get(position).equals("Aksesoris")){
//                tvDesc.setText(item.getDetail());
//            }else if (mType.get(position).equals("Sepatu")){
//                tvDesc.setText(item.getDetail());
//            }else if (mType.get(position).equals("Fashion")){
//                tvDesc.setText(item.getDetail());
//            }else {
//                tvDesc.setText(item.getDetail());
//            }
            String textDesc = "";
            if (item.getDetail() != null && item.getItemImage() != null && item.getContact() != null && item.getTime() != null ){
                if (item.getDetail().length() >= 30){
                    textDesc = item.getDetail().substring(0, 30) + "...";
                }else {
                    textDesc = item.getDetail();
                }
                tvDesc.setText(textDesc);
                tvTime.setText(dateConverter(item.getTime()));
                //decode base64 string to image
                final byte[] decodedBytes = Base64.decode(item.getItemImage(), Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                imageView.setImageBitmap(decodedImage);
            }

            itemView.setOnClickListener(v->{
                if (mCallback != null){
                    mCallback.onItemLocationListClick(position);
                }
            });
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {

        @BindView(R.id.btn_retry)
        Button btnRetry;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            btnRetry.setOnClickListener(v -> {
                if (mCallback != null)
                    mCallback.onBlogEmptyViewRetryClick();
            });
        }

        @Override
        protected void clear() {

        }

    }

    private String dateConverter(String dateInput){
        try {
            SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            spf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date newDate = null;
            newDate = spf.parse(dateInput);
            spf= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String returnDate = spf.format(newDate);
            return returnDate;

        }catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
