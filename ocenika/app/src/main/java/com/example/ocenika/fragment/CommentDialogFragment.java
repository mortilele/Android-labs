package com.example.ocenika.fragment;


import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.ocenika.R;
import com.example.ocenika.model.Comment;
import com.example.ocenika.service.APIService;
import com.example.ocenika.util.PreferenceUtils;
import com.example.ocenika.util.StringUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentDialogFragment extends DialogFragment {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.5:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIService apiService = retrofit.create(APIService.class);

    private Activity currentActivity;
    private int professorId;
    private EditText commentInput;
    private TextView addCommentBtn, cancelBtn;

    public interface OnInputSelected{
        void sendInput(Comment comment);
    }

    public OnInputSelected mOnInputSelected;

    public CommentDialogFragment() {

    }

    public static CommentDialogFragment newInstance(int professorId) {
        CommentDialogFragment fragment = new CommentDialogFragment();
        Bundle args = new Bundle();
        args.putInt("professorId", professorId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);
        addCommentBtn = view.findViewById(R.id.action_ok);
        cancelBtn = view.findViewById(R.id.action_cancel);
        commentInput = view.findViewById(R.id.input);
        cancelBtn.setOnClickListener(v -> getDialog().dismiss());
        addCommentBtn.setOnClickListener(addCommentListener);
        return view;
    }

    public void fetchPostComment(Response<Comment> response) {
        if (!response.isSuccessful()) {
            Log.e("add comment, Code:", "" + response.code());
            Toast.makeText(currentActivity, "Вы уже оставляли отзыв", Toast.LENGTH_SHORT).show();
            return;
        }
        if (response.body() != null) {
            Toast.makeText(currentActivity, "Отзыв успешно добавлен", Toast.LENGTH_SHORT).show();
            mOnInputSelected.sendInput(response.body());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        professorId = getArguments().getInt("professorId");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            currentActivity = (Activity) context;
        }
        try {
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        }
        catch (ClassCastException e) {
            Log.e("Comment dialog", "onAttach: ClassCastException : " + e.getMessage() );
        }
    }



    private TextView.OnClickListener addCommentListener = view -> {
        String input = commentInput.getText().toString();
        if (!input.equals("")){
            String token = PreferenceUtils.getToken(getActivity());
            Comment comment = new Comment();
            comment.setReview(input);
            comment.setEmail(StringUtils.fakeEmailGenerator());
            comment.setProfessor(professorId);
            Call<Comment> call = apiService.addComment(comment, "Token " + token);
            call.enqueue(new Callback<Comment>() {
                @Override
                public void onResponse(Call<Comment> call, Response<Comment> response) {
                    fetchPostComment(response);
                }

                @Override
                public void onFailure(Call<Comment> call, Throwable t) {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            });
        }
        getDialog().dismiss();
    };
}
