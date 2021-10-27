package com.example.numismatics;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.function.DoubleUnaryOperator;

public class AddTransaction extends AppCompatActivity {
    public static final String EXTRA_AMOUNT="com.example.numismatics.EXTRA_AMOUNT";
    public static final String EXTRA_REMARK="com.example.numismatics.EXTRA_REMARK";
    public static final String EXTRA_DATE="com.example.numismatics.EXTRA_DATE";
    private EditText edittext,remark,amount;
    Button Submit;
    ToggleButton toggle;
    private Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        myCalendar = Calendar.getInstance();
        Toolbar myChildToolbar =
                findViewById(R.id.add_my_toolbar);
        setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        edittext= findViewById(R.id.add_date);
        amount=findViewById(R.id.add_amount);
        remark=findViewById(R.id.add_remark);
        Submit=findViewById(R.id.add_submit);
        toggle=findViewById(R.id.add_toggle);
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
                new DatePickerDialog( AddTransaction.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTransaction();
                finish();
            }
        });

    }

    private void saveTransaction(){
        String amount1= amount.getText().toString();
        String remark1=remark.getText().toString();
        String date1=edittext.getText().toString();

        if(amount1.trim().isEmpty())
        {
            Toast.makeText(this, "Please insert amount", Toast.LENGTH_SHORT).show();
        }
        if(date1.trim().isEmpty())
        {
            Toast.makeText(this, "Please insert date", Toast.LENGTH_SHORT).show();
        }
        if(amount1.trim().isEmpty() && date1.trim().isEmpty())
        {
            Toast.makeText(this, "Please insert amount and date", Toast.LENGTH_SHORT).show();
        }
        if(!toggle.isChecked()){
            amount1="-"+amount1;
        }

        Intent data=new Intent();
        data.putExtra(EXTRA_AMOUNT,amount1);
        data.putExtra(EXTRA_DATE,date1);
        data.putExtra(EXTRA_REMARK,remark1);
        setResult(RESULT_OK,data);
        finish();

    }
    

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }

}
