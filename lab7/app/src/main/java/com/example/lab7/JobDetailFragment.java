package com.example.lab7;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

public class JobDetailFragment extends Fragment {

    public String id;
    public TextView type;
    public TextView url;
    public TextView created_at;
    public TextView company;
    public TextView company_url;
    public TextView location;
    public TextView title;
    public TextView description;
    public TextView how_to_apply;
    public ImageView company_logo;

    public static JobDetailFragment newInstance(Job job) {
        JobDetailFragment fragment = new JobDetailFragment();
        Bundle data = new Bundle();
        data.putString("id", job.getId());
        data.putString("type", job.getType());
        data.putString("url", job.getUrl());
        data.putString("created_at", job.getCreated_at());
        data.putString("company", job.getCompany());
        data.putString("company_url", job.getCompany_url());
        data.putString("location", job.getLocation());
        data.putString("title", job.getTitle());
        data.putString("description", job.getDescription());
        data.putString("how_to_apply", job.getHow_to_apply());
        data.putString("company_logo", job.getCompany_logo());
        fragment.setArguments(data);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.job_detail_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        company = view.findViewById(R.id.job_company);
        company_logo = view.findViewById(R.id.job_company_logo);
        created_at = view.findViewById(R.id.job_created_at);
        title = view.findViewById(R.id.job_detail_title);
        location = view.findViewById(R.id.job_location);
        type = view.findViewById(R.id.job_type);
        description = view.findViewById(R.id.job_detail_description);
        how_to_apply = view.findViewById(R.id.job_detail_how_to_apply);
        company.setText(getArguments().getString("company"));
        Picasso.get()
                .load(getArguments().getString("company_logo"))
                .placeholder(R.drawable.ic_business_center_black_24dp)
                .error(R.drawable.ic_business_center_black_24dp)
                .resize(200, 200)
                .into(company_logo);
        created_at.setText(getArguments().getString("created_at"));
        title.setText(getArguments().getString("title"));
        location.setText(getArguments().getString("location"));
        type.setText(getArguments().getString("type"));
        description.setText(getArguments().getString("description"));
        how_to_apply.setText(getArguments().getString("how_to_apply"));
    }
}