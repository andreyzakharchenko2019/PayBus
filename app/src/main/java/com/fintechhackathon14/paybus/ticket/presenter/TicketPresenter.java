package com.fintechhackathon14.paybus.ticket.presenter;

import android.content.Context;

import com.fintechhackathon14.paybus.entity.Ticket;
import com.fintechhackathon14.paybus.ticket.model.TicketModel;

import java.util.List;

public class TicketPresenter {

    private Context context;
    private TicketModel ticketModel;

    public TicketPresenter(Context context) {
        this.context = context;
        ticketModel = new TicketModel(context);
    }

    public void saveTicket(Ticket ticket) {
        ticketModel.saveTicket(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketModel.getAllTickets();
    }
}
