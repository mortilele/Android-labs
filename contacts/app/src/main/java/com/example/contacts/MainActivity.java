package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private ContactAdapter.ItemClickListener listener = null;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listener = new ContactAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position, Contact contact) {
                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
            }
        };
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ContactAdapter(contactGenerator(), listener);
        recyclerView.setAdapter(adapter);
    }

    private List<Contact> contactGenerator() {
        String firstName = "Alik";
        String lastName = "Akhmetov";
        String phone = "+777759350";
        String address = "KBTU";
        List<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Contact contact = new Contact(
                    firstName + i,
                    lastName + i,
                    phone+i,
                    address+i
            );
            contacts.add(contact);
        }
        return contacts;
    }
}
