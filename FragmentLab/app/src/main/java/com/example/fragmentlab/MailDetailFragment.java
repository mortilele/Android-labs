package com.example.fragmentlab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmentlab.data.model.Mail;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MailDetailFragment extends Fragment {

    private TextView from;
    private TextView to;
    private ImageView sender_avatar;
    private ImageView receiver_avatar;
    private TextView title;
    private TextView content;
    private TextView date;

    public MailDetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mail_detail, container, false);
        from = view.findViewById(R.id.mail_detail_from);
        to = view.findViewById(R.id.mail_detail_to);
        sender_avatar = view.findViewById(R.id.sender_avatar);
        receiver_avatar = view.findViewById(R.id.receiver_avatar);
        title = view.findViewById(R.id.mail_detail_title);
        content = view.findViewById(R.id.mail_detail_long_text);
        date = view.findViewById(R.id.mail_detail_date);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void draw(Mail mail) {
        from.setText(mail.from);
        to.setText(mail.to);
        title.setText(mail.title);
        content.setText(mail.content);
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        date.setText(df.format(mail.date));
    }
}
