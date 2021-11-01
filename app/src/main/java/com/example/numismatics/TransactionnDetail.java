package com.example.numismatics;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TransactionnDetail extends AppCompatActivity {
    private ViewModel viewModel;
    TextView amount,id,date,remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = getIntent();
//        int id = Integer.parseInt(intent.getStringExtra("transactionEntity"));
//        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
//        TransactionEntity transactionEntity = viewModel.getTransactionEntity(id);
//        TextView myAwesomeTextView = (TextView)findViewById(R.id.myAwesomeTextView);
//
////in your OnCreate() method
//        myAwesomeTextView.setText("My Awesome Text");

        amount =(TextView)findViewById(R.id.et_detail_amount);
        amount.setText("12");

//        Log.d("sad", String.valueOf(viewModel.getTransactionEntity(id)));

        setContentView(R.layout.activity_transactionn_detail);
        Toolbar myChildToolbar =
                findViewById(R.id.transaction_detail);
        setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
