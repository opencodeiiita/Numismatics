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
    TextView amount,tid,date,remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactionn_detail);
        Intent intent = getIntent();
        int id = Integer.parseInt(intent.getStringExtra("transactionEntity"));
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        TransactionEntity transactionEntity = viewModel.getTransactionEntity(id);

        tid =(TextView)findViewById(R.id.et_detail_tid);
        tid.setText(String.valueOf(id));
        amount =(TextView)findViewById(R.id.et_detail_amount);
        amount.setText(String.valueOf(transactionEntity.getCost()));
        remark =(TextView)findViewById(R.id.et_detail_remark);
        remark.setText(String.valueOf(transactionEntity.getRemark()));
        date =(TextView)findViewById(R.id.et_detail_date);
        date.setText(String.valueOf(transactionEntity.getDate()));


        Toolbar myChildToolbar =
                findViewById(R.id.transaction_detail);
        setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
