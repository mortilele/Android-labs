package com.example.ocenika.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ocenika.R;
import com.example.ocenika.model.User;
import com.example.ocenika.service.UserService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterFragment extends Fragment {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.ocenika.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    UserService userService = retrofit.create(UserService.class);

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Создать аккаунт");
        view.findViewById(R.id.register_btn).setOnClickListener(v -> register(view));
    }

    public void register(View view) {
        EditText usernameEdit = view.findViewById(R.id.register_username);
        String username = usernameEdit.getText().toString();
        User user = new User(username, "someveryHardPass2", "alik", "akhmetov", "ali.mars.99@gmail.com");
        Call<ResponseBody> call = userService.createUser(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                fetchResponse(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void fetchResponse(Response <ResponseBody> response) {
        if (response.isSuccessful()) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, LoginFragment.newInstance())
                    .addToBackStack("second")
                    .commitAllowingStateLoss();
        }
        else {
            Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
        }
    }
}
