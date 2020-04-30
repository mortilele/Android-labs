package com.example.ocenika.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ocenika.R;
import com.example.ocenika.activity.LoginActivity;
import com.example.ocenika.activity.MainActivity;
import com.example.ocenika.model.Login;
import com.example.ocenika.model.Token;
import com.example.ocenika.service.UserService;
import com.example.ocenika.util.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.5:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    UserService userService = retrofit.create(UserService.class);

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Войти");
        view.findViewById(R.id.login).setOnClickListener(v -> login(view));
        view.findViewById(R.id.login_skip).setOnClickListener(v -> skip());
        view.findViewById(R.id.register).setOnClickListener(v -> getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, RegisterFragment.newInstance())
                .addToBackStack("second")
                .commitAllowingStateLoss());
    }

    public void login(View view) {
        String username = ((EditText)view.findViewById(R.id.username)).getText().toString();
        String password = ((EditText)view.findViewById(R.id.password)).getText().toString();
        Login login = new Login(username, password);
        Call<Token> call = userService.login(login);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    Token token = response.body();
                    String tokenKey = token.getToken();
                    if (tokenKey != null && !tokenKey.isEmpty()) {
                        PreferenceUtils.saveToken(tokenKey, getActivity());
                        Toast.makeText(getActivity(), tokenKey, Toast.LENGTH_SHORT).show();
                        skip();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "login not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void skip() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


}
