package com.example.numismatics;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface TransactionDAO {

    @Insert
    void insert(TransactionEntity transaction);

}
