package com.xjm.xxd.dribbird.api.retrofit;

/**
 * @author : xiaoxiaoda
 *         date: 16-12-7
 *         email: daque@hustunique.com
 */

import com.xjm.xxd.dribbird.model.BucketBean;
import com.xjm.xxd.dribbird.model.BucketBriefInfoRsp;

import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Api to manage bucket
 */
public interface BucketApi {

    @GET("buckets/{bucketId}")
    Observable<BucketBean> getBucket(
            @Path("bucketId") int bucketId
    );

    @FormUrlEncoded
    @POST("buckets")
    Observable<BucketBriefInfoRsp> createBucket(
            @Field("name") String bucketName,
            @Field("description") String bucketDesc
    );

    @PUT("buckets/{bucketId}")
    Observable<BucketBriefInfoRsp> updateBucket(
            @Path("bucketId") int bucketId,
            @Field("name") String bucketName,
            @Field("description") String bucketDesc
    );

    @DELETE("buckets/{bucketId}")
    Observable<Void> deleteBucket(
            @Path("bucketId") int bucketId
    );



}

