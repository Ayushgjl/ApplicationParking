package com.example.applicationparking.bll;

import com.example.applicationparking.API.UserAPI;
import com.example.applicationparking.Model.username;
import com.example.applicationparking.URL.url;
import com.example.applicationparking.serverresponse.SignUpResponse;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {
        com.example.applicationparking.Model.username Username=new username(username, password);


        UserAPI userAPI = url.getInstance().create(UserAPI.class);
        Call<SignUpResponse> usersCall = userAPI.checklogin(Username);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}

