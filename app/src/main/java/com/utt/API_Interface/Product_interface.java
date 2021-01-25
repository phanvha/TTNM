package com.utt.API_Interface;

import com.utt.model.ProductResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Product_interface {

    @GET("/api/v1/products")
    Call<ProductResponse> getAllProducts();
    @GET("/api/v1/products/drink")
    Call<ProductResponse> getAllDrinkProducts(
            @Query("key") String key
    );

    //    @FormUrlEncoded
//    @POST("/api/v1/pothole")
//    Call<RequestBody> postAddPothole(
//            @Field("name") String name,
//            @Field("email") String email,
//            @Field("latitude") Double latitude,
//            @Field("longitude") Double longitude,
//            @Field("image") String image,
//            @Field("note") String note
//    );

    //    @Multipart
//    @POST("/api/v1/pothole")
//    Call<ResponseData> postAddPotholee(
//            @Part("name") String name,
//            @Part("email") String email,
//            @Part("latitude") Double latitude,
//            @Part("longitude") Double longitude,
//            @Part MultipartBody.Part image,
//            @Part("note") String note
//
//    );
//    @FormUrlEncoded
//    @POST("/api/v1/pothole")
//    Call<ResponseData> postAddPotholee(
//            @Part("name") String name,
//            @Part("email") String email,
//            @Part("latitude") Double latitude,
//            @Part("longitude") Double longitude,
//            @Part("image") String image,
//            @Part("note") String note
//
//    );
//
//
//    @FormUrlEncoded
//    @POST("/api/v1/point-check")
//    Call<ResponseDataPoint> postAddCheckPoint(
//            @Field("email") String email,
//            @Field("latitude") Double latitude,
//            @Field("longitude") Double longitude
//
//    );

}
