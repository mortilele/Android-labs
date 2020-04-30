package com.example.ocenika.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ocenika.R;
import com.example.ocenika.adapter.CommentAdapter;
import com.example.ocenika.adapter.ProfessorAdapter;
import com.example.ocenika.model.Comment;
import com.example.ocenika.model.ProfessorList;
import com.example.ocenika.service.APIService;
import com.example.ocenika.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfessorDetailFragment extends Fragment {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.5:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIService apiService = retrofit.create(APIService.class);
    public RecyclerView recyclerView;
    public CommentAdapter adapter;

    public int professorId;
    public TextView fullNameView;
    public EditText commentEmailEditText;
    public List<Comment> commentList = new ArrayList<>();


    public static ProfessorDetailFragment newInstance(int professorId) {
        ProfessorDetailFragment fragment = new ProfessorDetailFragment();
        Bundle data = new Bundle();
        data.putInt("id", professorId);
        fragment.setArguments(data);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.professor_detail_fragment, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Преподаватели");
        recyclerView = view.findViewById(R.id.comment_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommentAdapter(commentList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        professorId = getArguments().getInt("id");
        fullNameView = view.findViewById(R.id.professor_detail_full_name);
        commentEmailEditText = view.findViewById(R.id.professor_detail_comment_email);
        view.findViewById(R.id.professor_leave_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PreferenceUtils.getToken(getActivity()) != null && !PreferenceUtils.getToken(getActivity()).equals("")) {
                    String token = PreferenceUtils.getToken(getActivity());
                    Comment comment = new Comment();
                    String commentEmail = commentEmailEditText.getText().toString();
                    comment.setEmail(commentEmail);
                    comment.setProfessor(professorId);
                    Call<ResponseBody> call = apiService.addComment(comment, "Token " + token);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            fetchPostComment(response);
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(getActivity(), "Вам нужно войти", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Call<ProfessorList> call = apiService.getProfessorById(professorId);
        call.enqueue(new Callback<ProfessorList>() {
            @Override
            public void onResponse(Call<ProfessorList> call, Response<ProfessorList> response) {
                fetchResponse(response);
            }

            @Override
            public void onFailure(Call<ProfessorList> call, Throwable t) {
                Log.e("fetch professor", t.getMessage());
            }
        });
    }

    public void fetchResponse(Response<ProfessorList> response) {
        if (!response.isSuccessful()) {
            Log.e("get professors, Code:", "" + response.code());
            return;
        }
        if (response.body() != null) {
            ProfessorList professor = response.body();
            commentList.clear();
            commentList.addAll(professor.getRatings());
            adapter.notifyDataSetChanged();
            fullNameView.setText(professor.getFull_name());
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(professor.getFull_name());
        }
    }


    public void fetchPostComment(Response<ResponseBody> response) {
        if (!response.isSuccessful()) {
            Log.e("add comment, Code:", "" + response.code());
            Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
            return;
        }
        if (response.body() != null) {
            Toast.makeText(getActivity(), "Отзыв добавлен", Toast.LENGTH_SHORT).show();
        }
    }
}
