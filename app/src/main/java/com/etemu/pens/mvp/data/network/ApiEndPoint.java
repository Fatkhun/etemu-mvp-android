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


import com.etemu.pens.mvp.utils.AppConstants;

/**
 * Created by amitshekhar on 01/02/17.
 */

public final class ApiEndPoint {


    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

    public static final String ENDPOINT_UPLOAD_MISSING_ITEM = AppConstants.BASE_URL_API
            + "missing/create";

    public static final String ENDPOINT_CATEGORY_ITEM = AppConstants.BASE_URL_API
            + "category/all";

    public static final String ENDPOINT_LIST_MISSING_ITEM = AppConstants.BASE_URL_API
            + "missing/all";

}
