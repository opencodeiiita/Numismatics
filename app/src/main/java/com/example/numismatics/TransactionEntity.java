package com.example.numismatics;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "transaction")
public class TransactionEntity {

    public void setTransactionID(@NonNull Integer transactionID) {
        this.transactionID = transactionID;
    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    protected Integer transactionID;

    @ColumnInfo(name = "cost")
    protected Double cost;

    @ColumnInfo(name = "date")
    protected String date;

    @ColumnInfo(name = "remark")
    protected String remark;

    public TransactionEntity() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public TransactionEntity(@NonNull Integer transactionID, Double cost, String date, String remark) {
        this.transactionID = transactionID;
        this.cost = cost;
        this.date = date;
        this.remark = remark;
    }

    public TransactionEntity(Double cost, String date, String remark) {
        this.cost = cost;
        this.date = date;
        this.remark = remark;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public double getCost() {
        return cost;
    }

    public String getDate() {
        return date;
    }

    public String getRemark() {
        return remark;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
