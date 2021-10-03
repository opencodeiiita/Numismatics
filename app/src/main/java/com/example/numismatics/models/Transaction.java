package com.example.numismatics.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "transaction_table")
public class Transaction {

    @NonNull
    @PrimaryKey
    public String transactionID;

    @ColumnInfo(name = "title")
    public Double cost;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "remark")
    public String remark;

    public Transaction(){}

    public Transaction(@NonNull String transactionID, Double cost, String date, String remark) {
        this.transactionID = transactionID;
        this.cost = cost;
        this.date = date;
        this.remark = remark;
    }

    @NonNull
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(@NonNull String transactionID) {
        this.transactionID = transactionID;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Ignore
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID='" + transactionID + '\'' +
                ", cost=" + cost +
                ", date='" + date + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
