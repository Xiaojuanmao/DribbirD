package com.xjm.xxd.dribbird.api

import com.xjm.xxd.dribbird.model.BucketBean
import com.xjm.xxd.dribbird.model.BucketBriefInfoRsp

import io.reactivex.Flowable
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * @author : xiaoxiaoda
 * date: 16-12-7
 * email: daque@hustunique.com
 *
 * Api to manage bucket
 */
interface BucketApi {

    @GET("buckets/{bucketId}")
    fun getBucket(
            @Path("bucketId") bucketId: Int
    ): Flowable<BucketBean>

    @FormUrlEncoded
    @POST("buckets")
    fun createBucket(
            @Field("name") bucketName: String,
            @Field("description") bucketDesc: String
    ): Flowable<BucketBriefInfoRsp>

    @PUT("buckets/{bucketId}")
    fun updateBucket(
            @Path("bucketId") bucketId: Int,
            @Field("name") bucketName: String,
            @Field("description") bucketDesc: String
    ): Flowable<BucketBriefInfoRsp>

    @DELETE("buckets/{bucketId}")
    fun deleteBucket(
            @Path("bucketId") bucketId: Int
    ): Flowable<Void>


}

