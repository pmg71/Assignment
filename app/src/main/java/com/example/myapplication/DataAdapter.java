package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.AdapterLayoutBinding;
import com.example.myapplication.room.Employee;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    ArrayList<DataResponse> data;
    List<Employee> employeeList;
    DbResponse.ad ad;
    Context context;
    MainFragment.FragmentCommunication communication;

    public DataAdapter(ArrayList<DataResponse> data, DbResponse.ad ad,
                       Context context, MainFragment.FragmentCommunication communication) {
        this.data = data;
        this.ad = ad;
        this.context = context;
        this.communication = communication;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterLayoutBinding adapterLayoutBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_layout, parent, false);
        return new MyViewHolder(adapterLayoutBinding, ad,communication);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.adapterLayoutBinding.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AdapterLayoutBinding adapterLayoutBinding;
        DbResponse.ad ad1;
        MainFragment.FragmentCommunication fragmentCommunication;
        public MyViewHolder(@NonNull AdapterLayoutBinding itemView, DbResponse.ad ad, MainFragment.FragmentCommunication communication) {
            super(itemView.getRoot());
            adapterLayoutBinding = itemView;
            fragmentCommunication = communication;
            ad1 = ad;
            itemView.cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            fragmentCommunication.respond(getAdapterPosition(),adapterLayoutBinding.getData(),ad1);
        }
    }
}
