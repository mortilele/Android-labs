package com.example.fragmentlab.data.model;

import java.util.Date;

public class Mail {
    public String from;
    public String to;
    public String title;
    public String content;
    public Date date;

    public Mail(String from, String to, String title, String content) {
        this.from = from;
        this.to = to;
        this.title = title;
        this.content = content;
        this.date = new Date();
    }
}
