package com.example.numismatics;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExPopup extends DialogFragment implements MyAdapter.onClickListener {
    ViewModel viewModel;
    TransactionEntity transactionEntity;
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
                    case 0:order("", transactionEntity.date, "ASC");
                    case 1:order("", transactionEntity.date, "DESC");
                    case 2:order("", transactionEntity.cost.toString(), "ASC");
                    case 3:order("", transactionEntity.cost.toString(), "DESC");
                }
            }
        });
        return builder.create();
    }
    private void order(String query,String sortBy,String sortOrder){

        viewModel.searchDatabase("",sortBy,sortOrder).observe(this, new Observer<List<TransactionEntity>>() {
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
