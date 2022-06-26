package com.fintechhackathon14.paybus.ticket.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.fintechhackathon14.paybus.entity.Ticket;

@Database(entities = {Ticket.class}, version = 1)
public abstract class TicketsDatabase extends RoomDatabase {
    public abstract TicketDAO getTicketDAO();
}
