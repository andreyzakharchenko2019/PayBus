package com.fintechhackathon14.paybus.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.fintechhackathon14.paybus.util.TimestampConverter;

import java.util.Date;

@Entity
public class Bus {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ticket_id")
    public int ticketId;

    @ColumnInfo(name = "bus_code")
    public int busCode;

    @ColumnInfo(name = "payment_datetime")
    @TypeConverters({TimestampConverter.class})
    public Date paymentDatetime;

    @ColumnInfo(name = "sum")
    public int sum;

    @ColumnInfo(name = "qr_url")
    public String qrURL;

    @ColumnInfo(name = "service_name")
    public String serviceName;
}
