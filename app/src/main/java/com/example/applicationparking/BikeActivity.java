package com.example.applicationparking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import com.example.applicationparking.API.BikeBookingAPI;
import com.example.applicationparking.Model.BikeBooking;
import com.example.applicationparking.URL.url;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BikeActivity extends AppCompatActivity {

    private int counter = 1;
private EditText bfullname, bcontact,bvehiclemodel, bvehiclenum;
private Button btnbdate, btnbtime, btnetime, btnbbook;
private Spinner spin;
    private Calendar date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike);
        bfullname = findViewById(R.id.bfullname);
        bcontact = findViewById(R.id.bcontact);
        bvehiclemodel = findViewById(R.id.bvehiclemodel);
        bvehiclenum = findViewById(R.id.bvehiclenum);
        btnbdate = findViewById(R.id.btnbdate);
        btnbtime = findViewById(R.id.btnbtime);
        btnetime = findViewById(R.id.btnetime);
        btnbbook = findViewById(R.id.btnbbook);
        spin = findViewById(R.id.spin);

        String brand [] = {"Honda","Suzuki","Yamaha","KTM","Bajaj"};
        ArrayAdapter arrayAdapter1= new ArrayAdapter(this, android.R.layout.select_dialog_item,brand);
        spin.setAdapter(arrayAdapter1);

        btnbbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter+=counter+1;

                if(TextUtils.isEmpty(bfullname.getText())){
                    bfullname.setError("Please enter your fullname");
                    return;
                }

                else if(TextUtils.isEmpty(bcontact.getText())){
                    bcontact.setError("Please enter your contact number");
                    return;
                }

                else if(TextUtils.isEmpty(bvehiclemodel.getText())){
                    bvehiclemodel.setError("Please enter your vehicle model");
                    return;
                }

                else if(TextUtils.isEmpty(bvehiclenum.getText())){
                    bvehiclenum.setError("Please enter your vehicle number");
                    return;
                }

                else if(TextUtils.isEmpty(btnbdate.getText())){
                    btnbdate.setError("Please enter date");
                    return;
                }

                else if(TextUtils.isEmpty(btnbtime.getText())){
                    btnbtime.setError("Please enter time");
                    return;
                }
                else if(TextUtils.isEmpty(btnetime.getText())){
                    btnetime.setError("Please enter time");
                    return;
                }
                
                else {
                    registerbikebooking();
                }
            }
        });

        btnbdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker();
            }
        });

        btnbtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTime();
            }
        });

        btnetime.setOnClickListener(new View.OnClickListener() {
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
                btnetime.setText(timeString);
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
                btnbtime.setText(timeString);
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
                btnbdate.setText(date);
            }
        };
        DatePickerDialog datePickerDialog = new  DatePickerDialog(BikeActivity.this, dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH),   currentDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }

    private void registerbikebooking() {
        String fullname=bfullname.getText().toString();
        String contact=bcontact.getText().toString();
        String brand=spin.getSelectedItem().toString();
        String model=bvehiclemodel.getText().toString();
        String number=bvehiclenum.getText().toString();
        String date=btnbdate.getText().toString();
        String time=btnbtime.getText().toString();
        String etime=btnetime.getText().toString();

        BikeBooking bikeBooking = new BikeBooking(fullname,contact,brand,model,number,date,time,etime);
        BikeBookingAPI bikeBookingAPI = url.getInstance().create(BikeBookingAPI.class);
        Call<BikeBooking> bikeBookingCall = bikeBookingAPI.registerbikebooking(bikeBooking);
        bikeBookingCall.enqueue(new Callback<BikeBooking>() {
            @Override
            public void onResponse(Call<BikeBooking> call, Response<BikeBooking> response) {
                if (! response.isSuccessful()) {
                    Toast.makeText(BikeActivity.this, "Error : API is not responding " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(BikeActivity.this, "Car Booked", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<BikeBooking> call, Throwable t) {
                Toast.makeText(BikeActivity.this, "Error : Network Problem  and Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}