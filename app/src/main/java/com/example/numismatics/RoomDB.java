package com.example.numismatics;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Add Database Entities
@Database(entities = {TransactionEntity.class},version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    //Create database instance
    private static RoomDB instance;
    private static String DATABASE_NAME="database";

    public synchronized static RoomDB getInstance(Context context){
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class,DATABASE_NAME).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract TransactionDAO transactionDAO();

}
