package com.example.numismatics;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class EditTransaction extends AppCompatActivity {
    private ViewModel viewModel;
    private EditText edittext,eid,amount,remark;
    private Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction);

        Intent intent = getIntent();
        int id = Integer.parseInt(intent.getStringExtra("transactionEntity"));
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        TransactionEntity transactionEntity = viewModel.getTransactionEntity(id);

        eid =(EditText) findViewById(R.id.eid);
        eid.setText(String.valueOf(id));
        amount =(EditText) findViewById(R.id.edit_amount);
        amount.setText(String.valueOf(transactionEntity.getCost()));
        remark =(EditText) findViewById(R.id.edit_remark);
        remark.setText(String.valueOf(transactionEntity.getRemark()));
        edittext =(EditText) findViewById(R.id.date);
        edittext.setText(String.valueOf(transactionEntity.getDate()));

        myCalendar = Calendar.getInstance();
        Toolbar myChildToolbar =
                findViewById(R.id.edit_my_toolbar);
        setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        edittext= findViewById(R.id.date);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog( EditTransaction.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
}
