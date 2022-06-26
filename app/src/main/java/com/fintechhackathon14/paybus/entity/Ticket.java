package com.fintechhackathon14.paybus.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tickets")
public class Ticket {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String serviceName;
    private String codeTransport;
    private String priceTicket;
    private long dateTicket;

    @Ignore
    public Ticket() {
    }

    public Ticket(long id, String serviceName, String codeTransport, String priceTicket, long dateTicket) {
        this.id = id;
        this.serviceName = serviceName;
        this.codeTransport = codeTransport;
        this.priceTicket = priceTicket;
        this.dateTicket = dateTicket;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCodeTransport() {
        return codeTransport;
    }

    public void setCodeTransport(String codeTransport) {
        this.codeTransport = codeTransport;
    }

    public String getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(String priceTicket) {
        this.priceTicket = priceTicket;
    }

    public long getDateTicket() {
        return dateTicket;
    }

    public void setDateTicket(long dateTicket) {
        this.dateTicket = dateTicket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
