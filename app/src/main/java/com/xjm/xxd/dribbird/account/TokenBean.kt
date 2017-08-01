package com.xjm.xxd.dribbird.account

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 * Created by queda on 2016/12/1.
 */

class TokenBean : Serializable {

    @SerializedName("access_token")
    @JvmField
    var accessToken: String? = null

    @SerializedName("token_type")
    @JvmField
    var tokenType: String? = null

    @SerializedName("scope")
    @JvmField
    var scope: String? = null

}
