package com.example.numismatics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AnalyticsActivity extends AppCompatActivity {
    private EditText edittext,edittext2;
    private TextView amount;
    private Calendar myCalendar, myCalendar2;
    private Button submit;
    private ViewModel viewModel;

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
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        myCalendar = Calendar.getInstance();
        myCalendar2 = Calendar.getInstance();

        amount = (TextView) findViewById(R.id.amount);
        edittext= findViewById(R.id.add_date1);
        edittext2= findViewById(R.id.add_date2);
        initialiseDatePicker(myCalendar,edittext);
        initialiseDatePicker(myCalendar2,edittext2);

        Button submit = findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittext.getText().toString().isEmpty() || edittext2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    amount.setText(String.valueOf(viewModel.range(edittext.getText().toString(),edittext2.getText().toString())));
                }
            }
        });

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
