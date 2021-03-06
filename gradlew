package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.assignment.databinding.ActivityMainBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements HomeFragment.FragmentCommunication {
    // RecyclerView recyclerView;
    //  RecyclerView.LayoutManager layoutManager;
    MainAdapter adapter;
    ArrayList<Details> data = new ArrayList<Details>();
    DataResponse dataResponse;
    ApiInterface apiInterface;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
       /* binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setHasFixedSize(true);
        getDeatails();*/
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container,new HomeFragment(),null);
        transaction.commit();
    }

    @Override
    public void respond(int position, Details data, DataResponse.ad ad) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("company",ad.getCompany());
        bundle.putString("url",ad.getUrl());
        bundle.putString("text",ad.getText());
        detailsFragment.setArguments(bundle);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,detailsFragment,null);
        transaction.addToBackStack(null);
        transaction.commit();
    }

/*    public void getDeatails() {
        try {
            apiInterface = ApiClient.getApliClient().create(ApiInterface.class);
            Call<DataResponse> call = apiInterface.getListOfData();
            call.enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                    dataResponse = response.body();
                    Log.d("TAG", "onResponse: " + dataResponse.getData());
                    try {
                        for (int i = 0; i < dataResponse.getData().size(); i++) {
                            data.add(dataResponse.getData().get(i));
                            //Log.d("TAG", "onCreate: "+dataResponse.getData()[0].getId());
                        }
                        //Log.d("TAG", "onCreate: "+dataResponse.getData().length);
                        adapter = new MainAdapter(data, getApplicationContext());
                        //recyclerView.setAdapter(adapter);
                        binding.recyclerview.setAdapter(adapter);
                    } catch (Exception e) {
                        Log.d("TAG", "onCreate:1 " + e);
                    }
                }
                @Override
                public void onFailure(Call<DataResponse> call, Throwable t) {
                    Log.d("TAG", "onCreate:2 " + t);
                }
            });
        } catch (Exception e) {
            Log.d("TAG", "onCreate:3 " + e);
        }
    }*/
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     