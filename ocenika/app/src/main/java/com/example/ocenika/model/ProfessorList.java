package com.example.ocenika.model;

import com.google.gson.JsonObject;

import java.util.List;

public class ProfessorList {
    private int id;
    private String first_name;
    private String last_name;
    private String patronymic;
    private String avatar;
    private int average_rating;
    private int rating_count;
    private List<JsonObject> ratings;
    private List<JsonObject> subjects;
    private List<ProfessorList> universities;
}
