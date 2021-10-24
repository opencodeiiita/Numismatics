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

        }
        return instance;
    }

    public abstract TransactionDAO transactionDAO();


    }