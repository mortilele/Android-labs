package com.example.ocenika.model;

import com.google.gson.JsonObject;

import java.util.List;

public class University {
    private int id;
    private String name;
    private String abbreviation;
    private String description;
    private String logo;
    private List<JsonObject> professor_set;
    private List<JsonObject> subjects;

    public University() {
    }

    public University(int id, String name, String description, String logo, List<JsonObject> professor_set, List<JsonObject> subjects) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.professor_set = professor_set;
        this.subjects = subjects;
    }

    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<JsonObject> getProfessor_set() {
        return professor_set;
    }

    public void setProfessor_set(List<JsonObject> professor_set) {
        this.professor_set = professor_set;
    }

    public List<JsonObject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<JsonObject> subjects) {
        this.subjects = subjects;
    }
}
