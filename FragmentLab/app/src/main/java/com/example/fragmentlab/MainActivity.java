package com.example.fragmentlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.fragmentlab.data.model.Mail;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    public List<Mail> mails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username = getIntent().getStringExtra("username");
        mails = generateMail();
        Fragment mailListFragment = new MailListFragment(mails);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mail_list, mailListFragment)
                .commitAllowingStateLoss();
        Fragment mailDetailFragment = new MailDetailFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mail_detail, mailDetailFragment)
                .commitAllowingStateLoss();
    }

    public List<Mail> generateMail() {
        String from = "Alik";
        String to = "me";
        String title = "Mail Title";
        String content = "Some content of mail";
        List<Mail> mails = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Mail mail = new Mail(from + i, to, title + i, content + i);
            mails.add(mail);
        }
        return mails;
    }
}
