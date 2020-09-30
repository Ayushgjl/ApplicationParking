package com.example.applicationparking.API;


import com.example.applicationparking.Model.UpdateProfile;
import com.example.applicationparking.Model.User;
import com.example.applicationparking.Model.username;
import com.example.applicationparking.serverresponse.ImageResponse;
import com.example.applicationparking.serverresponse.SignUpResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UserAPI {
    @POST("users/signup")
    Call<SignUpResponse> registerUser(@Body User users);


    @POST("users/login")
    Call<SignUpResponse> checklogin(@Body username Username);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("users/me")
    Call<User> getUserDetails(@Header("Authorization") String token);

//    @GET("users/me")
//    Call<Usercrud> getupdate (@Header("Authorization") String token, @Body Usercrud usercrud );

    @GET("users/images")
    Call<ImageResponse> getImage(@Part MultipartBody.Part img);

    @PUT("users/me")
    Call<UpdateProfile> edituser(@Header("Authorization") String token, @Body UpdateProfile updateProfile);

}
