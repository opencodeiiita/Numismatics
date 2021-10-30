package com.example.numismatics;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public interface onDeleteClickListener{
        void onDeleteClickListener(TransactionEntity transactionEntity);
    }

    public MyAdapter(onDeleteClickListener listener){
        this.onDeleteClickListener=listener;
    }

    private List<TransactionEntity> transactionEntity=new ArrayList<>();
    private onDeleteClickListener onDeleteClickListener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.transaction_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TransactionEntity currentTransaction=transactionEntity.get(position);
        holder.remark.setText(currentTransaction.getRemark());
        holder.cost.setText(String.valueOf(currentTransaction.getCost()));
        holder.date.setText(currentTransaction.getDate());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setMessage("Do you want to delete the Transaction?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(onDeleteClickListener!=null){
                                    onDeleteClickListener.onDeleteClickListener(transactionEntity.get(position));
                                }
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionEntity.size();
    }

    public void setTransactionEntities(List<TransactionEntity> transactionEntity){
        this.transactionEntity=transactionEntity;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView remark,date,cost;
        ImageView edit,delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            remark=(TextView) itemView.findViewById(R.id.remark);
            date=(TextView) itemView.findViewById(R.id.date);
            cost=(TextView) itemView.findViewById(R.id.cost);
            edit=(ImageView) itemView.findViewById(R.id.edit);
            delete=(ImageView) itemView.findViewById(R.id.delete);

        }
    }

}