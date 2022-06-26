package com.fintechhackathon14.paybus.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Bus.class}, version  = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BusDao userDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "BUS_DB")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}
