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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ocenika.R;
import com.example.ocenika.adapter.ProfessorAdapter;
import com.example.ocenika.model.Professor;
import com.example.ocenika.service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfessorListFragment extends Fragment {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.ocenika.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIService apiService = retrofit.create(APIService.class);
    public ProfessorAdapter.professorItemClickListener listener = (position, professorId) -> getFragmentManager()
            .beginTransaction()
            .replace(R.id.container, ProfessorDetailFragment.newInstance(professorId))
            .addToBackStack("second")
            .commitAllowingStateLoss();
    public ProfessorAdapter adapter;
    public List<Professor> professor = new ArrayList<>();
    public RecyclerView recyclerView;


    public ProfessorListFragment() {
        // Required empty public constructor
    }

    public static ProfessorListFragment newInstance() {
        return new ProfessorListFragment();
    }

    public static ProfessorListFragment newInstance(int universityId) {
        ProfessorListFragment fragment = new ProfessorListFragment();
        Bundle data = new Bundle();
        data.putInt("id", universityId);
        fragment.setArguments(data);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_professors, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Преподаватели");
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProfessorAdapter(professor, listener);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void getProfessors() {
        Call<List<Professor>> call = apiService.getProfessors();
        call.enqueue(new Callback<List<Professor>>() {
            @Override
            public void onResponse(Call<List<Professor>> call, Response<List<Professor>> response) {
                fetchResponse(response);
            }

            @Override
            public void onFailure(Call<List<Professor>> call, Throwable t) {
                Log.e("fetch professors", t.getMessage());
            }
        });
    }

    public void getProfessorsByUniversity(int universityId) {
        Call<List<Professor>> call = apiService.getProfessorsByUniversity(universityId);
        call.enqueue(new Callback<List<Professor>>() {
            @Override
            public void onResponse(Call<List<Professor>> call, Response<List<Professor>> response) {
                fetchResponse(response);
            }

            @Override
            public void onFailure(Call<List<Professor>> call, Throwable t) {
                Log.e("fetch professors", t.getMessage());
            }
        });
    }

    public void fetchResponse(Response<List<Professor>> response) {
        if (!response.isSuccessful()) {
            Log.e("get professors, Code:", "" + response.code());
            return;
        }
        if (response.body() != null) {
            professor.clear();
            professor.addAll(response.body());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey("id") ) {
            int universityId = getArguments().getInt("id");
            getProfessorsByUniversity(universityId);
        }
        else {
            getProfessors();
        }
    }
}
