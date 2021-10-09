package com.example.numismatics;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDAO {

    @Insert
    void insert(TransactionEntity transaction);

    @Query("SELECT * FROM `transaction` ORDER BY transactionID ASC")
    LiveData<List<TransactionEntity>> getTransactions();

}
