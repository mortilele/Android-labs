package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

@SuppressLint("Registered")
public class ContactDetailActivity extends AppCompatActivity {

    private TextView contactDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        contactDetail = findViewById(R.id.contact_detail);
        Contact contact = (Contact) getIntent().getParcelableExtra("contact");
        contactDetail.setText(contact.toString());
    }
}