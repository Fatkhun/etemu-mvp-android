package com.etemu.pens.mvp.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UploadFoundedItemResponse implements Serializable {

    @SerializedName("itemType")
    private String itemType;

    @SerializedName("contact")
    private String contact;

    @SerializedName("_id")
    private String id;

    @SerializedName("detail")
    private String detail;

    @SerializedName("category")
    private String category;

    @SerializedName("itemImage")
    private String itemImage;

    @SerializedName("createdAt")
    private String time;

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UploadFoundedItemResponse{" +
                "itemType='" + itemType + '\'' +
                ", contact='" + contact + '\'' +
                ", id='" + id + '\'' +
                ", detail='" + detail + '\'' +
                ", category='" + category + '\'' +
                ", itemImage='" + itemImage + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
