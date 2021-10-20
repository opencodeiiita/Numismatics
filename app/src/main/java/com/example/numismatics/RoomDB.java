package com.example.numismatics;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

//Add Database Entities
@Database(entities = {TransactionEntity.class},version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    //Create database instance
    private static RoomDB instance;
    private static String DATABASE_NAME="database";


    @RequiresApi(api = Build.VERSION_CODES.O)
    public synchronized static RoomDB getInstance(Context context){
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class,DATABASE_NAME).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
            instance.run();
        }
        return instance;
    }

    public abstract TransactionDAO transactionDAO();

                @RequiresApi(api = Build.VERSION_CODES.O)
                public void run() {
                    TransactionEntity transactionEntity1=new TransactionEntity(1,120.0,"19/10/21","Food");
                    transactionDAO().insert(transactionEntity1);
                    TransactionEntity transactionEntity2= new TransactionEntity(2,550.0,"19/10/21","Recharge");
                    transactionDAO().insert(transactionEntity2);
                    TransactionEntity transactionEntity3=new TransactionEntity(3,100.0,"19/10/21","Not found!");
                    transactionDAO().insert(transactionEntity3);
                    TransactionEntity transactionEntity4=new TransactionEntity(4,130.0,"19/10/21","Tailor");
                    transactionDAO().insert(transactionEntity4);
                    TransactionEntity transactionEntity5=new TransactionEntity(5,140.0,"19/10/21","Accessories");
                    transactionDAO().insert(transactionEntity5);

                    }
    }