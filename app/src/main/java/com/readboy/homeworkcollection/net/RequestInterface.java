package com.readboy.homeworkcollection.net;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RequestInterface {

    @Multipart
    @POST("pad/zuoye/demo")
    Observable<RequestResult> getImgResult(@Part MultipartBody.Part imgs);




    @FormUrlEncoded
    @POST("v1/auth/login")
    Observable<EBagRequestResult> loginSystem(
            @Field("userType") String userType,
            @Field("userAccount") String userAccount,
            @Field("password") String password
            );


}
