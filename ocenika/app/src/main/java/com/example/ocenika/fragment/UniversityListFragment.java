package com.example.ocenika.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ocenika.R;
import com.example.ocenika.adapter.UniversityAdapter;
import com.example.ocenika.model.UniversityList;
import com.example.ocenika.service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UniversityListFragment extends Fragment {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.5:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIService apiService = retrofit.create(APIService.class);
    public UniversityAdapter adapter;
    public UniversityAdapter.universityItemClickListener listener = (position, universityId) -> getFragmentManager()
            .beginTransaction()
            .replace(R.id.container, ProfessorListFragment.newInstance(universityId))
            .addToBackStack("second")
            .commitAllowingStateLoss();
    public List<UniversityList> universityList = new ArrayList<>();
    public RecyclerView recyclerView;


    public UniversityListFragment() {
        // Required empty public constructor
    }

    public static UniversityListFragment newInstance() {
        return new UniversityListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_universities, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Университеты");
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new UniversityAdapter(universityList, listener);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void getUniversities() {
        Call<List<UniversityList>> call = apiService.getUniversities();
        call.enqueue(new Callback<List<UniversityList>>() {
            @Override
            public void onResponse(Call<List<UniversityList>> call, Response<List<UniversityList>> response) {
                fetchResponse(response);
            }
            @Override
            public void onFailure(Call<List<UniversityList>> call, Throwable t) {
                Log.e("fail get universities", t.getMessage());
            }
        });
    }

    public void fetchResponse(Response<List<UniversityList>> response) {
        if (!response.isSuccessful()) {
            Log.e("get universities, Code:", ""+response.code());
            return;
        }
        if (response.body() != null) {
            universityList.clear();
            universityList.addAll(response.body());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getUniversities();
    }
}
