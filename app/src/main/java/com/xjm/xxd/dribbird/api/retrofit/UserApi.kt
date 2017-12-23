package com.xjm.xxd.dribbird.api.retrofit

import com.xjm.xxd.dribbird.model.UserBean

import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author : xiaoxiaoda
 * date: 16-12-7
 * email: daque@hustunique.com
 */
interface UserApi {

    @get:GET("user")
    val authenticatedUser: Observable<UserBean>

    @GET("users/{userId}")
    fun getUserInfo(@Path("userId") userId: String): Observable<UserBean>

}
