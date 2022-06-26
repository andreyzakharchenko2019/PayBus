package com.fintechhackathon14.paybus.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BusDao {

    @Query("SELECT * FROM bus")
    List<Bus> getAllBuses();

    @Insert
    void insertBus(Bus... buses);

    @Delete
    void delete(Bus bus);
}
