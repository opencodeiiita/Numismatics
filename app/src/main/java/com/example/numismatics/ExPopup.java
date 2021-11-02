package com.example.numismatics;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;

import java.util.List;

public class ExPopup extends DialogFragment implements MyAdapter.onClickListener {

    private ViewModel viewModel;
    MyAdapter adapter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose Sorting Order");
        String[] order = {"Date-Aesc", "Date-Desc", "Amount-Aesc", "Amount-Desc"};
        adapter = new MyAdapter(this);

        builder.setItems(order, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:sort0();
                    case 1:
                    case 2:sort2();
                    case 3:sort3();
                }
            }
        });
        return builder.create();
    }


    public void sort0(){
        viewModel.getAllTransactions0().observe(this, new Observer<List<TransactionEntity>>() {
            @Override
            public void onChanged(List<TransactionEntity> transactionEntities) {

                adapter.setTransactionEntities(transactionEntities);
            }
        });
    }
    public void sort2(){
        viewModel.getAllTransactions2().observe(this, new Observer<List<TransactionEntity>>() {
            @Override
            public void onChanged(List<TransactionEntity> transactionEntities) {

                adapter.setTransactionEntities(transactionEntities);
            }
        });
    }
    public void sort3(){
        viewModel.getAllTransactions3().observe(this, new Observer<List<TransactionEntity>>() {
            @Override
            public void onChanged(List<TransactionEntity> transactionEntities) {

                adapter.setTransactionEntities(transactionEntities);
            }
        });
    }

    @Override
    public void onDeleteClickListener(TransactionEntity transactionEntity) {

    }

    @Override
    public void onClickListener(TransactionEntity transactionEntity) {

    }

    @Override
    public void onEditListener(TransactionEntity transactionEntity) {

    }
}
