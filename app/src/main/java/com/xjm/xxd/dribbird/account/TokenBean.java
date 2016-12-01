package com.xjm.xxd.dribbird.account;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by queda on 2016/12/1.
 */

public class TokenBean implements Serializable {

    @SerializedName("access_token")
    private String mAccessToken;

    @SerializedName("token_type")
    private String mTokenType;

    @SerializedName("scope")
    private String mScope;

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public String getTokenType() {
        return mTokenType;
    }

    public void setTokenType(String tokenType) {
        mTokenType = tokenType;
    }

    public String getScope() {
        return mScope;
    }

    public void setScope(String scope) {
        mScope = scope;
    }

}
