package com.example.applicationparking.API;

import com.example.applicationparking.Model.BikeBooking;
import com.example.applicationparking.Model.CarBooking;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BikeBookingAPI {
    @POST("bikebooking")
    Call<BikeBooking> registerbikebooking(@Body BikeBooking bikeBooking);
}
