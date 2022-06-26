package com.fintechhackathon14.paybus.ticket.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fintechhackathon14.paybus.R;
import com.fintechhackathon14.paybus.entity.Ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerViewHistoryAdapter extends RecyclerView.Adapter<RecyclerViewHistoryAdapter.RecyclerViewHolder> {

    private List<Ticket> ticketList;
    private Activity activity;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView nameService;
        public TextView codeTransport;
        public TextView date;
        public TextView price;
        public LinearLayout linearLayout;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameService = itemView.findViewById(R.id.nameService);
            codeTransport = itemView.findViewById(R.id.codeTransport);
            date = itemView.findViewById(R.id.date);
            price = itemView.findViewById(R.id.price);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

    public RecyclerViewHistoryAdapter(List<Ticket> ticketList, Activity activity) {
        this.ticketList = ticketList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,
                parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Ticket ticket = ticketList.get(position);
        holder.nameService.setText(ticket.getServiceName());
        holder.codeTransport.setText(ticket.getCodeTransport());
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date = new Date(ticket.getDateTicket());
        holder.date.setText(dateFormat.format(date));
        holder.price.setText(ticket.getPriceTicket() + " ₸");
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }


}
