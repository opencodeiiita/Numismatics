package com.example.numismatics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.appbar.MaterialToolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AnalyticsActivity extends AppCompatActivity {
    private EditText edittext,edittext2;
    private Calendar myCalendar, myCalendar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        MaterialToolbar mtoolbar = findViewById(R.id.topAppBar);
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        myCalendar = Calendar.getInstance();
        myCalendar2 = Calendar.getInstance();

        edittext= findViewById(R.id.add_date1);
        edittext2= findViewById(R.id.add_date2);
        initialiseDatePicker(myCalendar,edittext);
        initialiseDatePicker(myCalendar2,edittext2);

    }
    private void initialiseDatePicker(Calendar myCalendar, EditText edittext){
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                //update the label
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog( AnalyticsActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });
    }

}
