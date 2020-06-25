package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.databinding.FragmentMainBinding;
import com.example.myapplication.room.Employee;
import com.example.myapplication.room.RoomAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    DataAdapter adapter;
    ArrayList<DataResponse> data;
    DbResponse.ad ad;
    DbResponse dbResponse;
    ApiInterface apiInterface;
    private FragmentMainBinding binding;
    connectivityReceiver receiver;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    RoomAdapter roomAdapter;
    Employee employee;
    List<Employee> employees;
    FragmentCommunication fragmentCommunication = new FragmentCommunication() {
        @Override
        public void respond(int position, DataResponse data, DbResponse.ad ad) {
            DetailsFragment detailsFragment = new DetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("image_path", data.getAvatar());
            bundle.putString("fullname", data.getFirst_name().concat(" ").concat(data.getLast_name()));
            bundle.putString("email", data.getEmail());
            bundle.putString("company", ad.getCompany());
            bundle.putString("url", ad.getUrl());
            bundle.putString("text", ad.getText());
            detailsFragment.setArguments(bundle);
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.container, detailsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };
    roomFragmentCommunication roomFragmentCommunication = new roomFragmentCommunication() {
        @Override
        public void respond(int position, Employee employee) {
            DetailsFragment detailsFragment = new DetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("image_path", employee.getAvatar());
            bundle.putString("fullname", employee.getFirst_name().concat(" ").concat(employee.getLast_name()));
            bundle.putString("email", employee.getEmail());
            bundle.putString("company", employee.getCompany());
            bundle.putString("url", employee.getUrl());
            bundle.putString("text", employee.getText());
            detailsFragment.setArguments(bundle);
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.container, detailsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };

    public MainFragment() {
        // Required empty public constructor
    }

    public interface FragmentCommunication {
        void respond(int position, DataResponse data, DbResponse.ad ad);
    }

    public interface roomFragmentCommunication {
        void respond(int position, Employee employee);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View v = binding.getRoot();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerview.setHasFixedSize(true);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new connectivityReceiver();
        getActivity().registerReceiver(receiver, filter);
        employee = new Employee();
        employees = MainActivity.myDatabase.employeeDao().getAll();
        return v;
    }

    public void getDeatails() {
        try {
            data = new ArrayList<>();
            apiInterface = ApiClient.getApliClient().create(ApiInterface.class);
            Call<DbResponse> call = apiInterface.getListOfData();
            call.enqueue(new Callback<DbResponse>() {
                @Override
                public void onResponse(Call<DbResponse> call, Response<DbResponse> response) {
                    dbResponse = response.body();
                    try {
                        for (int i = 0; i < dbResponse.getData().size(); i++) {
                            data.add(dbResponse.getData().get(i));
                        }
                        if (employees.size() == 0) {

                            for (int i = 0; i < dbResponse.getData().size(); i++) {
                                employee.setId(dbResponse.getData().get(i).getId());
                                employee.setFirst_name(dbResponse.getData().get(i).getFirst_name());
                                employee.setLast_name(dbResponse.getData().get(i).getLast_name());
                                employee.setEmail(dbResponse.getData().get(i).getEmail());
                                employee.setAvatar(dbResponse.getData().get(i).getAvatar());
                                employee.setCompany(dbResponse.getAd().getCompany());
                                employee.setUrl(dbResponse.getAd().getUrl());
                                employee.setText(dbResponse.getAd().getText());
                                MainActivity.myDatabase.employeeDao().addEmployee(employee);
                            }
                        } else {

                        }
                        ad = dbResponse.getAd();
                        adapter = new DataAdapter(data, ad, getContext(), fragmentCommunication);
                        binding.recyclerview.setAdapter(adapter);
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(Call<DbResponse> call, Throwable t) {

                }
            });
        } catch (Exception e) {
        }
    }

    public class connectivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            isNetworkAvaliable(context);
        }

        private boolean isNetworkAvaliable(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                getDeatails();
                return true;
            } else {
                List<Employee> newList = new ArrayList<>();
                for (Employee employee : employees) {
                    newList.add(employee);
                }
                roomAdapter = new RoomAdapter(newList, roomFragmentCommunication, getActivity());
                binding.recyclerview.setAdapter(roomAdapter);
                return false;
            }
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(receiver);
    }
}