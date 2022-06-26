package com.fintechhackathon14.paybus.ticket.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.fintechhackathon14.paybus.entity.Ticket;

import java.util.List;

@Dao
public interface TicketDAO {
    @Insert
    long addTicket(Ticket ticket);

    @Delete
    void deleteTicket(Ticket ticket);

    @Query("select * from tickets")
    List<Ticket> getAllTickets();

    @Query("select * from tickets where id ==:ticketId")
    Ticket getTicket(long ticketId);
}
