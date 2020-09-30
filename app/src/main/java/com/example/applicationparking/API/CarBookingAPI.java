package com.example.applicationparking.API;

import com.example.applicationparking.Model.CarBooking;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CarBookingAPI {
    @POST("carbooking")
    Call<CarBooking> registercarbooking(@Body CarBooking carBooking);
}
