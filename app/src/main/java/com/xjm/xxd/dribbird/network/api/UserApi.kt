package com.xjm.xxd.dribbird.network.api

import com.xjm.xxd.dribbird.model.UserModel

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author : xiaoxiaoda
 * date: 16-12-7
 * email: daque@hustunique.com
 */
interface UserApi {

    @GET("/user")
    fun getAuthenticatedUser(): Observable<UserModel>

}
