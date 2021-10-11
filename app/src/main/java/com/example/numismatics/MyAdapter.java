package com.example.numismatics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.transaction_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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