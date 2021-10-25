package com.example.numismatics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTransaction extends AppCompatActivity {

    private EditText amount,remark,date;
    Button submit;
    public static final String EXTRA_AMOUNT="com.example.numismatics.EXTRA_AMOUNT";
    public static final String EXTRA_REMARK="com.example.numismatics.EXTRA_REMARK";
    public static final String EXTRA_DATE="com.example.numismatics.EXTRA_DATE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        amount=findViewById(R.id.amount);
        remark=findViewById(R.id.remark);
        date=findViewById(R.id.date);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTransaction();
            }
        });

    }

    private void saveTransaction(){
        String amount1= amount.getText().toString();
        String remark1=remark.getText().toString();
        String date1=date.getText().toString();
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

        Intent data=new Intent();
        data.putExtra(EXTRA_AMOUNT,amount1);
        data.putExtra(EXTRA_DATE,date1);
        data.putExtra(EXTRA_REMARK,remark1);
        setResult(RESULT_OK,data);
        finish();

    }
}
