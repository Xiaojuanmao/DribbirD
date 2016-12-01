package com.xjm.xxd.dribbird.account;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.xjm.xxd.dribbird.api.ApiConstants;
import com.xjm.xxd.dribbird.api.okhttp.OkHttpManager;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;
import okhttp3.ResponseBody;

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
     *
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
            mOAuthUrl = builder.toString();
        }
        return mOAuthUrl;
    }

    public static boolean isOAuth2Url(String targetStr) {
        return !TextUtils.isEmpty(targetStr) && getOAuth2Url().equals(targetStr);
    }

    public static boolean isMatchRedirectUrl(String targetStr) {
        return !TextUtils.isEmpty(targetStr) && targetStr.contains(ApiConstants.DEFAULT_REDIRECT_URI_PRE);
    }

    public static
    @Nullable
    boolean requestForToken(String returnCode) {
        if (TextUtils.isEmpty(returnCode)) {
            return false;
        }
        Map<String, String> params = new HashMap<>();
        params.put(ApiConstants.CLIENT_ID, ApiConstants.DRIBBLE_CLIENT_ID);
        params.put(ApiConstants.CLIENT_SECRET, ApiConstants.DRIBBLE_CLIENT_SECRET);
        params.put(ApiConstants.CODE, returnCode);
        Response response = OkHttpManager.getInstance().postSync(
                ApiConstants.OAUTH_BASE_URL + ApiConstants.TOKEN,
                params);
        if (response == null) {
            return false;
        }
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            return false;
        }
        Gson gson = OkHttpManager.getInstance().getGson();
        TokenBean tokenBean = gson.fromJson(responseBody.charStream(), TokenBean.class);

        return true;
    }

}
