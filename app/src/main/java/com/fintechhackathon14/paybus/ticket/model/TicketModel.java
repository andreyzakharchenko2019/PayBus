package com.fintechhackathon14.paybus.ticket.model;

import android.content.Context;

import androidx.room.Room;

import com.fintechhackathon14.paybus.entity.Ticket;

import java.util.List;

public class TicketModel {
    private TicketsDatabase ticketsDatabase;
    private Context context;

    public TicketModel(Context context) {
        this.context = context;
        ticketsDatabase = Room.databaseBuilder(context, TicketsDatabase.class, "ticket_db")
                .allowMainThreadQueries()
                .build();
    }

    public void saveTicket(Ticket ticket) {
        ticketsDatabase.getTicketDAO().addTicket(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketsDatabase.getTicketDAO().getAllTickets();
    }


}
