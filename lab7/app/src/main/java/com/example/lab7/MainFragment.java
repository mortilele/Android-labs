package com.example.lab7;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
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
import android.widget.SearchView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
    public List<Job> jobs = new ArrayList<>();
    private Retrofit retrofit;
    private APIService apiService;
    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public static MainFragment newInstance(String description, String location, boolean is_full_time) {
        MainFragment mainFragment = new MainFragment();
        Bundle data = new Bundle();
        data.putString("description", description);
        data.putString("location", location);
        data.putBoolean("is_full_time", is_full_time);
        mainFragment.setArguments(data);
        return mainFragment;
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
        listener = (position, job) -> getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, JobDetailFragment.newInstance(job))
                .addToBackStack("second")
                .commitAllowingStateLoss();
        adapter = new JobAdapter(jobs, listener);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void initService() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APIService.class);
    }

    public void getJobs() {
        Call<List<Job>> call = apiService.getJobs();
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                fetchResponse(response);
            }
            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e("fail get jobs", t.getMessage());
            }
        });
    }

    public void getFilteredJobs(String description, String location, boolean is_full_time) {
        Call<List<Job>> call = apiService.getFilteredJobs(description, location, is_full_time);
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                fetchResponse(response);
            }
            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e("fail get jobs", t.getMessage());
            }
        });
    }

    public void fetchResponse(Response<List<Job>> response) {
        if (!response.isSuccessful()) {
            Log.e("get jobs, Code:", ""+response.code());
            return;
        }
        if (response.body() != null) {
            jobs.clear();
            jobs.addAll(response.body());
            adapter.notifyDataSetChanged();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initService();
        try {
            String description = getArguments().getString("description", "");
            String location = getArguments().getString("location", "");
            boolean is_full_time = getArguments().getBoolean("is_full_time");
            this.getFilteredJobs(description, location, is_full_time);
        }
        catch (Exception e) {
            this.getJobs();
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.action_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Call<List<Job>> call = apiService.getJobsFromSearch(query);
                call.enqueue(new Callback<List<Job>>() {
                    @Override
                    public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                        fetchResponse(response);
                    }

                    @Override
                    public void onFailure(Call<List<Job>> call, Throwable t) {
                        Log.e("failed search", t.getMessage());
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_filter) {
            showDialog();
            return true;
        }
        return false;
    }

    public void showDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        FilterDialog newFragment = FilterDialog.newInstance();
        newFragment.show(ft, "dialog");
    }
}