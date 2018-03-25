package com.xjm.xxd.dribbird.account

import android.net.Uri
import com.google.gson.JsonSyntaxException
import com.xjm.xxd.dribbird.network.*
import com.xjm.xxd.framework.network.GsonManager
import com.xjm.xxd.framework.network.OkHttpManager
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
                val builder = StringBuilder(OAUTH_BASE_URL)
                builder.append(AUTHORIZE)
                        .append(QUESTION_MARK)
                        .append(CLIENT_ID)
                        .append(EQUAL_MARK)
                        .append(DRIBBLE_CLIENT_ID)
                        .append(AND_MARK)
                        .append(REDIRECT_URI)
                        .append(EQUAL_MARK)
                        .append(DEFAULT_REDIRECT_URI_PRE)
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

    fun isMatchRedirectUrl(targetUrl: String?): Boolean {
        return !targetUrl.isNullOrEmpty() && targetUrl?.startsWith(DEFAULT_REDIRECT_URI_PRE).nullAsFalse()
    }

    fun isMatchRedirectUrl(targetUri: Uri?): Boolean {
        return targetUri != null && targetUri.toString().startsWith(DEFAULT_REDIRECT_URI_PRE)
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
        params[CLIENT_ID] = DRIBBLE_CLIENT_ID
        params[CLIENT_SECRET] = DRIBBLE_CLIENT_SECRET
        params[CODE] = returnCode!!
        val response = OkHttpManager.instance.postSync(
                OAUTH_BASE_URL + TOKEN,
                params) ?: return null
        val responseBody = response.body() ?: return null
        return GsonManager.gson().fromJson(responseBody.charStream(), TokenBean::class.java)
    }

}
