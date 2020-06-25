package com.example.assignment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment.databinding.ActivityMainBinding;
import com.example.assignment.databinding.FragmentHomeBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    MainAdapter adapter;
    ArrayList<Details> data = new ArrayList<Details>();
    DataResponse.ad ad ;
    DataResponse dataResponse;
    ApiInterface apiInterface;
    private FragmentHomeBinding binding;
    FragmentCommunication fragmentCommunication;
    public HomeFragment() {
        // Required empty public constructor
    }
    public interface FragmentCommunication {
        void respond(int position, Details data, DataResponse.ad ad);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container, false);
        View v = binding.getRoot();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerview.setHasFixedSize(true);
        getDeatails();
        return v;
    }

/*    FragmentCommunication communication=new FragmentCommunication() {
        @Override
        public void respond(int position, Details data, DataResponse.ad ad) {
            DetailsFragment detailsFragment = new DetailsFragment();
            FragmentManager manager=getFragmentManager();
            Bundle b