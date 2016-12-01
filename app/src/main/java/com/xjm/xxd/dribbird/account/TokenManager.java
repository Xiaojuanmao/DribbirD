package com.xjm.xxd.dribbird.account;

import android.support.annotation.NonNull;

import com.xjm.xxd.dribbird.api.ApiConstants;

import static com.xjm.xxd.dribbird.api.ApiConstants.EQUAL_MARK;

/**
 * @author : xiaoxiaoda
 *         date: 16-11-29
 *         email: daque@hustunique.com
 */
public class TokenManager {

    public static String getDribbleOAuth2Url() {
        return getDribbleOAuth2Url(ApiConstants.DEFAULT_REDIRECT_URI);
    }

    public static String getDribbleOAuth2Url(@NonNull String redirectUri) {
        StringBuilder builder = new StringBuilder(ApiConstants.OAUTH_BASE_URL);
        builder.append(ApiConstants.AUTHORIZE)
                .append(ApiConstants.QUESTION_MARK)
                .append(ApiConstants.CLIENT_ID)
                .append(EQUAL_MARK)
                .append(ApiConstants.DRIBBLE_CLIENT_ID)
                .append(ApiConstants.AND_MARK)
                .append(ApiConstants.REDIRECT_URI)
                .append(EQUAL_MARK)
                .append(redirectUri);
        return builder.toString();
    }

}
