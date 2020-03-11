package com.example.midtermalik;

import java.util.Date;

public class ToDo {
    public int id;
    public String title;
    public String description;
    public String status;
    public String category;
    public Date duration;

    public ToDo(int id, String title, String description, String status, String category, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.category = category;
        this.duration = date;
    }
    public ToDo(String title) {
        this.title = title;
        this.id =123;
        this.description = "asa";
        this.status = "active";
        this.category = "defualt";
        this.duration = new Date();
    }
}
