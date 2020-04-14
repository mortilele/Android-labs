package com.example.lab7;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class FavoritesFragment extends Fragment {
    AppDatabase db;
    FavoriteDao favoriteDao;
    List<Favorite> favorites;
    List<Job> jobs = new ArrayList<>();
    JobAdapter.jobItemClickListener listener;
    JobAdapter adapter;
    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Favorites");
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listener = (position, job) -> getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, JobDetailFragment.newInstance(job))
                .addToBackStack("second")
                .commitAllowingStateLoss();

        adapter = new JobAdapter(jobs, listener);
        recyclerView.setAdapter(adapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        for (Favorite favorite : favorites) {
            Call<Job> call = apiService.getJobById(favorite.id);
            call.enqueue(new Callback<Job>() {
                @Override
                public void onResponse(Call<Job> call, Response<Job> response) {
                    if (!response.isSuccessful()) {
                        Log.e("get job, Code:", ""+response.code());
                        return;
                    }
                    Job job = response.body();
                    jobs.add(job);
                    int insertIndex = jobs.size();
                    adapter.notifyItemInserted(insertIndex);
                }
                @Override
                public void onFailure(Call<Job> call, Throwable t) {
                    Log.e("get job, Code:", t.getMessage());
                }
            });
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = MyApplication.getInstance().getDatabase();
        favoriteDao = db.favoriteDao();
        favorites =  favoriteDao.getFavorites();
    }
}