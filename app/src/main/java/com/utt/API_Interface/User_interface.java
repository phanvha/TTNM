package com.utt.API_Interface;

import com.utt.model.DataUser;
import com.utt.model.UserResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface User_interface {

    @FormUrlEncoded
    @POST("/api/v1/user/login")
    Call<UserResponse> postLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @Headers({"Accept: application/json"})
    @Multipart
    @POST("/api/v1/user/register")
    Call<UserResponse> postRegister(
            @Part("name") String name,
            @Part("email") String email,
            @Part("phonenumber") int phone,
            @Part("password") String password,
            @Part MultipartBody.Part image
    );


}
