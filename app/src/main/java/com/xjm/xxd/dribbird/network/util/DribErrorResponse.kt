package com.xjm.xxd.dribbird.network.util

import com.google.gson.annotations.SerializedName
import com.xjm.xxd.dribbird.network.api.DribErrorModel

/**
 * Created by queda on 2018/3/8.
 */

data class DribErrorResponse(
        @SerializedName("message") val message: String?,
        @SerializedName("errors") val errorList: List<DribErrorModel>?
)
