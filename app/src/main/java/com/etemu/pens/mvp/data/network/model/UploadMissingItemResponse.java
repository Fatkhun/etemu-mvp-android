package com.etemu.pens.mvp.data.network.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UploadMissingItemResponse implements Serializable {

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

	public void setItemType(String itemType){
		this.itemType = itemType;
	}

	public String getItemType(){
		return itemType;
	}

	public void setContact(String contact){
		this.contact = contact;
	}

	public String getContact(){
		return contact;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return detail;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setItemImage(String itemImage){
		this.itemImage = itemImage;
	}

	public String getItemImage(){
		return itemImage;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "UploadMissingItemResponse{" +
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