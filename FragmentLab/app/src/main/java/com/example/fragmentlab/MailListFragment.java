package com.example.fragmentlab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import com.example.fragmentlab.data.model.Mail;

import java.util.List;

public class MailListFragment extends Fragment {
    public List<Mail> mails;
    private MailAdapter.MailItemClickListener listener = null;

    public MailListFragment(List<Mail> mails) {
        this.mails = mails;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mail_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listener = new MailAdapter.MailItemClickListener() {
            @Override
            public void itemClick(int position, Mail mail) {
                Fragment fragment = getFragmentManager().findFragmentById(R.id.mail_detail);
                ((MailDetailFragment)fragment).draw(mail);
            }
        };
        MailAdapter adapter = new MailAdapter(this.mails, listener);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
