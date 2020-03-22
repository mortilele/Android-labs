package com.example.fragmentlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.fragmentlab.data.model.Mail;
import com.example.fragmentlab.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    public List<Mail> mails;
    private TextView textViewEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        textViewEmail = (TextView)findViewById(R.id.userEmail);
        if (intent.hasExtra("EMAIL")) {
            String emailFromIntent = getIntent().getStringExtra("EMAIL");
            textViewEmail.setText("Welcome " + emailFromIntent);
        } else {
            String email = PreferenceUtils.getEmail(this);
            textViewEmail.setText("Welcome " + email);
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                PreferenceUtils.savePassword("", this);
                PreferenceUtils.saveEmail("", this);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return true;
    }
}
