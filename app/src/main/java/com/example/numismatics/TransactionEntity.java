package com.example.numismatics;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "transaction")
public class TransactionEntity {

    @PrimaryKey(autoGenerate = true)
    private int transactionID;

    @ColumnInfo(name = "cost")
    private double cost;

    @ColumnInfo(name = "date")
    private LocalDate date;

    @ColumnInfo(name = "remark")
    private String remark;

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

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }
}
