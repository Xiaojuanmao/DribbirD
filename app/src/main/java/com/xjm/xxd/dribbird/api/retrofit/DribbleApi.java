package com.xjm.xxd.dribbird.api.retrofit;

import com.xjm.xxd.dribbird.model.UserBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author : xiaoxiaoda
 *         date: 16-11-26
 *         email: daque@hustunique.com
 */

public interface DribbleApi {

    @GET("/user")
    Observable<UserBean> getAuthenticatedUser();

    @GET("/users/{userId}")
    Observable<UserBean> getUserInfo(@Path("userId") String userId);

}
