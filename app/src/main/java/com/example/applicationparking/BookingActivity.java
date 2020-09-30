package com.example.applicationparking;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.applicationparking.API.CarBookingAPI;
import com.example.applicationparking.Model.CarBooking;
import com.example.applicationparking.URL.url;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity  {
    private int counter = 1;
private EditText cfullname, ccontact, cvehiclemodel, cvehiclenum;
private Spinner spin;
private Button btncdate, btntime, btnctime, btncbook;
private Calendar date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        cfullname = findViewById(R.id.cfullname);
        ccontact = findViewById(R.id.ccontact);
        spin = findViewById(R.id.spin);
        cvehiclemodel = findViewById(R.id.cvehiclemodel);
        cvehiclenum = findViewById(R.id.cvehiclenum);
        btncdate = findViewById(R.id.btncdate);
        btntime = findViewById(R.id.btntime);
        btnctime = findViewById(R.id.btnctime);
        btncbook = findViewById(R.id.btncbook);

        String brand [] = {"Hyundai","Suzuki","Toyota","Audi","Volkswagen","Ford"};
        ArrayAdapter arrayAdapter1= new ArrayAdapter(this, android.R.layout.select_dialog_item,brand);
        spin.setAdapter(arrayAdapter1);

        btncbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter+=counter+1;

                if(TextUtils.isEmpty(cfullname.getText())){
                    cfullname.setError("Please enter your fullname");
                    return;
                }

                else if(TextUtils.isEmpty(ccontact.getText())){
                    ccontact.setError("Please enter your contact number");
                    return;
                }

                else if(TextUtils.isEmpty(cvehiclemodel.getText())){
                    cvehiclemodel.setError("Please enter your vehicle model");
                    return;
                }

                else if(TextUtils.isEmpty(cvehiclenum.getText())){
                    cvehiclenum.setError("Please enter your vehicle number");
                    return;
                }

                else if(TextUtils.isEmpty(btncdate.getText())){
                    btncdate.setError("Please enter date");
                    return;
                }

                else if(TextUtils.isEmpty(btntime.getText())){
                    btntime.setError("Please enter time");
                    return;
                }
                else if(TextUtils.isEmpty(btnctime.getText())){
                    btnctime.setError("Please enter time");
                    return;
                }

                else {
                    registercarbooking();
                }
            }
        });

        btncdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker();

            }
        });

        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTime();
            }
        });

        btnctime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadEtime();
            }
        });

    }

    private void loadEtime() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int Minute = calendar.get(Calendar.MINUTE);

        boolean is24HourFormat = DateFormat.is24HourFormat(this);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String timeString = "hour:" + hourOfDay + "minute :" + minute;
                btnctime.setText(timeString);
            }
        }, HOUR,Minute,is24HourFormat);
        timePickerDialog.show();


    }

    private void loadTime() {

        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int Minute = calendar.get(Calendar.MINUTE);

        boolean is24HourFormat = DateFormat.is24HourFormat(this);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String timeString = "hour:" + hourOfDay + "minute :" + minute;
                btntime.setText(timeString);
            }
        }, HOUR,Minute,is24HourFormat);
        timePickerDialog.show();


    }

    private void loadDatePicker() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                String date = "Select Date:" + (monthOfYear+1) + "/" + dayOfMonth + "/" + year;
                btncdate.setText(date);
            }
        };
        DatePickerDialog datePickerDialog = new  DatePickerDialog(BookingActivity.this, dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH),   currentDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }

    private void registercarbooking() {
        String fullname=cfullname.getText().toString();
        String contact=ccontact.getText().toString();
        String brand=spin.getSelectedItem().toString();
        String model=cvehiclemodel.getText().toString();
        String number=cvehiclenum.getText().toString();
        String date=btncdate.getText().toString();
        String time=btntime.getText().toString();
        String etime=btnctime.getText().toString();

        CarBooking carBooking = new CarBooking(fullname,contact,brand,model,number,date,time,etime);
        CarBookingAPI carBookingAPI = url.getInstance().create(CarBookingAPI.class);
        Call<CarBooking> carBookingCall = carBookingAPI.registercarbooking(carBooking);
        carBookingCall.enqueue(new Callback<CarBooking>() {
            @Override
            public void onResponse(Call<CarBooking> call, Response<CarBooking> response) {
                if (! response.isSuccessful()) {
                    Toast.makeText(BookingActivity.this, "Error : API is not responding " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(BookingActivity.this, "Bike Parking Booked", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CarBooking> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Error : Network Problem  and Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}