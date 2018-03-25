package com.xjm.xxd.dribbird.api

import com.google.gson.annotations.SerializedName

/**
 * Created by queda on 2018/3/8.
 */

data class DribErrorResponse(
        @SerializedName("message") val message: String?,
        @SerializedName("errors") val errorList: List<DribErrorModel>?
)
