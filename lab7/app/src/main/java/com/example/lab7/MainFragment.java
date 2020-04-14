package com.example.lab7;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {

    public JobAdapter adapter;
    public JobAdapter.jobItemClickListener listener;
    public RecyclerView recyclerView;
    public List<Job> jobs;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.main_fragment, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Jobs");
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void getJobs() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Job>> call = apiService.getJobs();
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (!response.isSuccessful()) {
                    Log.e("get jobs, Code:", ""+response.code());
                    return;
                }
                jobs = response.body();
                listener = new JobAdapter.jobItemClickListener() {
                    @Override
                    public void itemClick(int position, Job job) {
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, JobDetailFragment.newInstance(job))
                                .addToBackStack("second")
                                .commitAllowingStateLoss();
                    }
                };
                // Hard coded..
                adapter = new JobAdapter(jobs, listener);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e("fail get jobs", t.getMessage());
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getJobs();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_filter:
                // do stuff
                return true;

            case R.id.action_search:
                // do more stuff
                return true;
        }

        return false;
    }
}