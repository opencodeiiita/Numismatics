package com.example.numismatics;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private TransactionRepository repository;
    private LiveData<List<TransactionEntity>> allTransactions;
    private TransactionEntity transactionEntity;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository=new TransactionRepository(application);
        allTransactions=repository.getAllTransactions();
    }

    public void insert(TransactionEntity transactionEntity){
        repository.insert(transactionEntity);
    }

    public TransactionEntity getTransactionEntity(int id){
        if (id== transactionEntity.getTransactionID())
            return transactionEntity;
        return null;
    }

    public LiveData<List<TransactionEntity>> getAllTransactions() {
        return allTransactions;
    }
}
