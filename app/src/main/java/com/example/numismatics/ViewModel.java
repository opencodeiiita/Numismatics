package com.example.numismatics;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private TransactionRepository repository;
    private LiveData<List<TransactionEntity>> allTransactions;
    private TransactionEntity transactionEntity;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ViewModel(@NonNull Application application) {
        super(application);
        repository=new TransactionRepository(application);
        allTransactions=repository.getAllTransactions();
    }

    public void insert(TransactionEntity transactionEntity){
        repository.insert(transactionEntity);
    }

    public void delete(TransactionEntity transactionEntity){
        repository.delete(transactionEntity);
    }

    public TransactionEntity getTransactionEntity(int id){
        return repository.getTransactionEntity(id);
    }

    public LiveData<List<TransactionEntity>> getAllTransactions() {
        return allTransactions;
    }

    public LiveData<List<TransactionEntity>> searchDatabase(String searchQuery, String sortBy, String sortOrder){
        return repository.searchDatabase(searchQuery, sortBy, sortOrder);
    }
}
