package com.example.numismatics;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TransactionRepository {
    private TransactionDAO transactioDao;
    private LiveData<List<TransactionEntity>> allTransactions;
    private TransactionEntity transactionEntity;

    public TransactionRepository(Application application)
    {
        RoomDB database = RoomDB.getInstance(application);
        transactioDao=database.transactionDAO();
        allTransactions=transactioDao.getTransactions();
    }

    public TransactionEntity getTransactionEntity(int id){
        if (id== transactionEntity.getTransactionID())
            return transactionEntity;
        return null;
    }

    public void insert(TransactionEntity transactionEntity)
    {
        new InsertTransactionAsyncTask(transactioDao).execute(transactionEntity);
    }

    public LiveData<List<TransactionEntity>> getAllTransactions() {
        return allTransactions;
    }

    public static class InsertTransactionAsyncTask extends AsyncTask<TransactionEntity,Void,Void>
    {
        private TransactionDAO transactioDao;

        private InsertTransactionAsyncTask(TransactionDAO transactioDao)
        {
            this.transactioDao=transactioDao;
        }

        @Override
        protected Void doInBackground(TransactionEntity... transactionEntities) {
            transactioDao.insert(transactionEntities[0]);
            return null;
        }
    }

}
