package com.xjm.xxd.dribbird.api

import com.google.gson.annotations.SerializedName

/**
 * Created by queda on 2018/3/8.
 */

data class DribErrorModel(
        @SerializedName("attribute") val attribute: String?,
        @SerializedName("message") val message: String?
)