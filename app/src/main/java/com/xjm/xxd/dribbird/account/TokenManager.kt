package com.xjm.xxd.dribbird.account

import com.google.gson.JsonSyntaxException
import com.xjm.xxd.framework.api.ApiConstants
import com.xjm.xxd.framework.api.GsonManager
import com.xjm.xxd.framework.api.OkHttpManager
import com.xjm.xxd.framework.data.CommonStore
import com.xjm.xxd.skeleton.kotlinext.nullAsFalse

/**
 * @author : dada
 * date: 16-11-29
 * email: daque@hustunique.com
 */

object TokenManager {

    private var mOAuthUrl: String = ""

    private var mTokenBean: TokenBean? = null

    /**
     * build oauth2 authentication url
     * @return the url to auth
     */
    val oAuth2Url: String
        get() {
            if (mOAuthUrl.isEmpty()) {
                val builder = StringBuilder(ApiConstants.OAUTH_BASE_URL)
                builder.append(ApiConstants.AUTHORIZE)
                        .append(ApiConstants.QUESTION_MARK)
                        .append(ApiConstants.CLIENT_ID)
                        .append(ApiConstants.EQUAL_MARK)
                        .append(ApiConstants.DRIBBLE_CLIENT_ID)
                        .append(ApiConstants.AND_MARK)
                        .append(ApiConstants.REDIRECT_URI)
                        .append(ApiConstants.EQUAL_MARK)
                        .append(ApiConstants.DEFAULT_REDIRECT_URI_PRE)
                mOAuthUrl = builder.toString()
            }
            return mOAuthUrl
        }

    val tokenBean: TokenBean?
        get() {
            if (mTokenBean == null) {
                val tokenBean = CommonStore.currentTokenBean
                if (tokenBean.isEmpty()) {
                    return null
                }
                val gson = OkHttpManager.instance.gson
                try {
                    mTokenBean = gson.fromJson(tokenBean, TokenBean::class.java)
                } catch (e: JsonSyntaxException) {
                    e.printStackTrace()
                    return null
                }
            }
            return mTokenBean
        }

    fun isOAuth2Url(targetStr: String?): Boolean {
        return !targetStr.isNullOrEmpty() && targetStr.equals(oAuth2Url)
    }

    fun isMatchRedirectUrl(targetStr: String?): Boolean {
        return !targetStr.isNullOrEmpty() && targetStr?.startsWith(ApiConstants.DEFAULT_REDIRECT_URI_PRE).nullAsFalse()
    }

    /**
     * return if there is a local token
     * @return
     */
    fun hasAvailableToken(): Boolean {
        val tokenBean = CommonStore.currentTokenBean
        if (tokenBean.isEmpty()) {
            return false
        }
        val gson = OkHttpManager.instance.gson
        try {
            mTokenBean = gson.fromJson(tokenBean, TokenBean::class.java)
        } catch (e: Throwable) {
            e.printStackTrace()
            return false
        }

        return true
    }

    fun requestForToken(returnCode: String?): TokenBean? {
        if (returnCode.isNullOrEmpty()) {
            return null
        }
        val params = hashMapOf<String, String>()
        params.put(ApiConstants.CLIENT_ID, ApiConstants.DRIBBLE_CLIENT_ID)
        params.put(ApiConstants.CLIENT_SECRET, ApiConstants.DRIBBLE_CLIENT_SECRET)
        params.put(ApiConstants.CODE, returnCode!!)
        val response = OkHttpManager.instance.postSync(
                ApiConstants.OAUTH_BASE_URL + ApiConstants.TOKEN,
                params) ?: return null
        val responseBody = response.body() ?: return null
        return GsonManager.gson().fromJson(responseBody.charStream(), TokenBean::class.java)
    }

}
