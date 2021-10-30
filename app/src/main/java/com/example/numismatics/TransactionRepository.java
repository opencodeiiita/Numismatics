package com.example.numismatics;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TransactionRepository {
    private TransactionDAO transactionDao;
    private LiveData<List<TransactionEntity>> allTransactions;
    private TransactionEntity transactionEntity;

    @RequiresApi(api = Build.VERSION_CODES.O)
    TransactionRepository(Application application)
    {
        RoomDB database = RoomDB.getInstance(application);
        transactionDao=database.transactionDAO();
        allTransactions=transactionDao.getTransactions();
    }

    public TransactionEntity getTransactionEntity(int id){
        return transactionDao.transactionDetails(id);
    }

    public void insert(TransactionEntity transactionEntity)
    {
        new InsertTransactionAsyncTask(transactionDao).execute(transactionEntity);
    }

    public void delete(TransactionEntity transactionEntity)
    {
        new DeleteTransactionAsyncTask(transactionDao).execute(transactionEntity);
    }

    public LiveData<List<TransactionEntity>> getAllTransactions() {
        return allTransactions;
    }

    public static class InsertTransactionAsyncTask extends AsyncTask<TransactionEntity,Void,Void>
    {
        private TransactionDAO transactionDao;

        InsertTransactionAsyncTask(TransactionDAO transactionDao)
        {
            this.transactionDao=transactionDao;
        }

        @Override
        protected Void doInBackground(final TransactionEntity... transactionEntities) {
            transactionDao.insert(transactionEntities[0]);
            return null;
        }
    }

    public static class DeleteTransactionAsyncTask extends AsyncTask<TransactionEntity,Void,Void>
    {

        private TransactionDAO transactionDao;

        DeleteTransactionAsyncTask(TransactionDAO transactionDao)
        {
            this.transactionDao=transactionDao;
        }

        @Override
        protected Void doInBackground(final TransactionEntity... transactionEntities) {
            transactionDao.delete(transactionEntities[0]);
            return null;
        }
    }

}
