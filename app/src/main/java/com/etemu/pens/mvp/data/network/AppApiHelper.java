/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.etemu.pens.mvp.data.network;


import com.etemu.pens.mvp.data.network.model.CategoryItemResponse;
import com.etemu.pens.mvp.data.network.model.UploadFoundedItemResponse;
import com.etemu.pens.mvp.data.network.model.UploadMissingItemResponse;
import com.etemu.pens.mvp.data.prefs.PreferencesHelper;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by janisharali on 28/01/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;
    private Map<String, String> mHeader;

    @Inject
    PreferencesHelper preferencesHelper;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<UploadMissingItemResponse> uploadMissingItem(String category, String detail, String contact, String imageFile) {
        Map<String, String> map = new MapBuilder()
                .add("category", category)
                .add("detail", detail)
                .add("contact", contact)
                .add("itemImage", imageFile)
                .build();

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_UPLOAD_MISSING_ITEM)
                .setContentType("application/json")
                .addApplicationJsonBody(map)
                .build()
                .getObjectSingle(UploadMissingItemResponse.class);
    }

    @Override
    public Single<UploadFoundedItemResponse> uploadFoundedItem(String category, String detail, String contact, String imageFile) {
        Map<String, String> map = new MapBuilder()
                .add("category", category)
                .add("detail", detail)
                .add("contact", contact)
                .add("itemImage", imageFile)
                .build();

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_UPLOAD_FOUNDED_ITEM)
                .setContentType("application/json")
                .addApplicationJsonBody(map)
                .build()
                .getObjectSingle(UploadFoundedItemResponse.class);
    }

    @Override
    public Single<List<CategoryItemResponse>> getCategoryItem() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CATEGORY_ITEM)
                .build()
                .getObjectListSingle(CategoryItemResponse.class);
    }

    @Override
    public Single<List<UploadMissingItemResponse>> getUploadMissingItem() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_LIST_MISSING_ITEM)
                .build()
                .getObjectListSingle(UploadMissingItemResponse.class);
    }

    @Override
    public Single<List<UploadFoundedItemResponse>> getUploadFoundedItem() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_LIST_FOUNDED_ITEM)
                .build()
                .getObjectListSingle(UploadFoundedItemResponse.class);
    }

    private Map<String, String> getHeader(){
        if (mHeader == null){
            mHeader = new HashMap<>();
            mHeader.put("Content-Type", "multipart/form-data");
        }
        return mHeader;
    }

    private class MapBuilder extends HashMap<String, String>{

        public MapBuilder add(String key,String value){
            put(key,value);
            return this;
        }

        public Map<String,String> build(){
            return this;
        }
    }
}

