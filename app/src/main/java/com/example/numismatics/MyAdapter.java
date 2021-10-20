package com.example.numismatics;

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

    private List<TransactionEntity> transactionEntity=new ArrayList<>();
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.transaction_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TransactionEntity currentTransaction=transactionEntity.get(position);
        holder.remark.setText(currentTransaction.getRemark());
        holder.cost.setText(String.valueOf(currentTransaction.getCost()));
        holder.date.setText(currentTransaction.getDate());
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