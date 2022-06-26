package com.fintechhackathon14.paybus.ticket.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.fintechhackathon14.paybus.R;
import com.fintechhackathon14.paybus.databinding.FragmentHistoryBinding;
import com.fintechhackathon14.paybus.entity.Ticket;
import com.fintechhackathon14.paybus.ticket.presenter.TicketPresenter;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private List<RecyclerHistoryViewItem> recyclerHistoryViewItemList;
    private TicketPresenter ticketPresenter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        ticketPresenter = new TicketPresenter(getContext());
        recyclerView = binding.recyclerView;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerHistoryViewItemList = new ArrayList<>();

        adapter = new RecyclerViewHistoryAdapter(ticketPresenter.getAllTickets(), getActivity());
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);


    }
}