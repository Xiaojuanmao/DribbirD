package com.xjm.xxd.dribbird.account;

import android.text.TextUtils;

import com.xjm.xxd.dribbird.api.ApiConstants;

import static com.xjm.xxd.dribbird.api.ApiConstants.EQUAL_MARK;

/**
 * @author : xiaoxiaoda
 *         date: 16-11-29
 *         email: daque@hustunique.com
 */
public class TokenManager {

    private static String mOAuthUrl;

    /**
     * build oauth2 authentication url
     * @return the url to auth
     */
    public static String getOAuth2Url() {
        if (TextUtils.isEmpty(mOAuthUrl)) {
            StringBuilder builder = new StringBuilder(ApiConstants.OAUTH_BASE_URL);
            builder.append(ApiConstants.AUTHORIZE)
                    .append(ApiConstants.QUESTION_MARK)
                    .append(ApiConstants.CLIENT_ID)
                    .append(EQUAL_MARK)
                    .append(ApiConstants.DRIBBLE_CLIENT_ID)
                    .append(ApiConstants.AND_MARK)
                    .append(ApiConstants.REDIRECT_URI)
                    .append(EQUAL_MARK)
                    .append(ApiConstants.DEFAULT_REDIRECT_URI_PRE);
            mOAuthUrl =  builder.toString();
        }
        return mOAuthUrl;
    }

    public static boolean isOAuth2Url(String targetStr) {
        return !TextUtils.isEmpty(targetStr) && getOAuth2Url().equals(targetStr);
    }

    public static boolean isMatchRedirectUrl(String targetStr) {
        return !TextUtils.isEmpty(targetStr) && targetStr.contains(ApiConstants.DEFAULT_REDIRECT_URI_PRE);
    }


}
