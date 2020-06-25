package com.example.myapplication.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DbResponse;
import com.example.myapplication.MainFragment;
import com.example.myapplication.R;
import com.example.myapplication.databinding.RoomAdapterLayoutBinding;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {
    List<Employee> employeeList;
    MainFragment.roomFragmentCommunication communication;
    Context context;

    public RoomAdapter(List<Employee> employeeList, MainFragment.roomFragmentCommunication communication, Context context) {
        this.employeeList = employeeList;
        this.communication = communication;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RoomAdapterLayoutBinding adapterLayoutBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.room_adapter_layout, parent, false);
        return new RoomAdapter.MyViewHolder(adapterLayoutBinding,communication);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.adapterLayoutBinding.setData(employeeList.get(position));
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RoomAdapterLayoutBinding adapterLayoutBinding;
        DbResponse.ad ad1;
        MainFragment.roomFragmentCommunication fragmentCommunication;
        public MyViewHolder(@NonNull RoomAdapterLayoutBinding itemView, MainFragment.roomFragmentCommunication communication) {
            super(itemView.getRoot());
            adapterLayoutBinding = itemView;
            fragmentCommunication = communication;
            itemView.cardView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            fragmentCommunication.respond(getAdapterPosition(),adapterLayoutBinding.getData());
        }
    }

    }
