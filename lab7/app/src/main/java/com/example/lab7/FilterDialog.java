package com.example.lab7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.DialogFragment;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class FilterDialog extends DialogFragment {

    private EditText description;
    private EditText location;
    private Button submit;
    private CheckBox full_time;

    public FilterDialog() {

    }

    public static FilterDialog newInstance() {
        return new FilterDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filter_popup, container, false);
        description = view.findViewById(R.id.filter_description);
        location = view.findViewById(R.id.filter_location);
        full_time = view.findViewById(R.id.filter_full_time);
        submit = view.findViewById(R.id.filter_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String desc = description.getText().toString();
                String loc = location.getText().toString();
                boolean is_full_time = full_time.isChecked();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance(desc, loc, is_full_time))
                        .commitAllowingStateLoss();
                dismiss();
            }
        });
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        getDialog().getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat_Dialog);

    }
}