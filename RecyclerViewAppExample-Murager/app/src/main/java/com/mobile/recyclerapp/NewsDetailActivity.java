package com.mobile.recyclerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NewsDetailActivity extends AppCompatActivity {

    private TextView tvNewsDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        tvNewsDetail = findViewById(R.id.tvNewsDetail);

        News news = (News) getIntent().getParcelableExtra("news");
        tvNewsDetail.setText(news.getDate());
    }
}
