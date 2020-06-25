package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class DetailsFragment extends Fragment {
    private String mParam1;
    private String mParam2, mParam3,fullName,Email,img_path;
    ImageView profile_image;
    TextView name, email, company, url,txt;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            img_path = getArguments().getString("image_path");
            fullName = getArguments().getString("fullname");
            Email = getArguments().getString("email");
            mParam1 = getArguments().getString("company");
            mParam2 = getArguments().getString("url");
            mParam3 = getArguments().getString("text");
            Log.d("TAG", "onCreate: " +img_path);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        profile_image = v.findViewById(R.id.profile_image);
        name = v.findViewById(R.id.name);
        email = v.findViewById(R.id.email);
        company = v.findViewById(R.id.company);
        url = v.findViewById(R.id.url);
        txt = v.findViewById(R.id.text);
        Log.d("TAG", "onCreate:1 " +img_path);
        Glide.with(getContext()).load(img_path).into(profile_image);
        name.setText(fullName);
        email.setText(Email);
        company.setText(mParam1);
        url.setText(mParam2);
        txt.setText(mParam3);
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(mParam2));
                startActivity(i);
            }
        });
        return v;
    }
}