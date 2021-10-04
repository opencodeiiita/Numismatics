package com.example.numismatics;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "transaction")
public class TransactionEntity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer transactionID;

    @ColumnInfo(name = "cost")
    private Double cost;

    @ColumnInfo(name = "date")
    private LocalDate date;

    @ColumnInfo(name = "remark")
    private String remark;

    public TransactionEntity() {
    }

    public TransactionEntity(@NonNull Integer transactionID, Double cost, LocalDate date, String remark) {
        this.transactionID = transactionID;
        this.cost = cost;
        this.date = date;
        this.remark = remark;
    }

    public TransactionEntity(Double cost, LocalDate date, String remark) {
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

    public LocalDate getDate() {
        return date;
    }

    public String getRemark() {
        return remark;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
