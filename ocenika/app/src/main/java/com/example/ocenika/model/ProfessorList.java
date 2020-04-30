package com.example.ocenika.model;

import java.util.List;

public class ProfessorList {
    private int id;
    private String first_name;
    private String last_name;
    private String patronymic;
    private String full_name;
    private String avatar;
    private int average_rating;
    private int rating_count;
    private List<Comment> ratings;
    private List<Subject> subjects;
    private List<ProfessorList> universities;

    public ProfessorList() {
    }

    public ProfessorList(int id, String first_name, String last_name, String patronymic, String avatar, int average_rating, int rating_count, List<Comment> ratings, List<Subject> subjects, List<ProfessorList> universities) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.avatar = avatar;
        this.average_rating = average_rating;
        this.rating_count = rating_count;
        this.ratings = ratings;
        this.subjects = subjects;
        this.universities = universities;
    }

    public String toString() {
        return full_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(int average_rating) {
        this.average_rating = average_rating;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }

    public List<Comment> getRatings() {
        return ratings;
    }

    public void setRatings(List<Comment> ratings) {
        this.ratings = ratings;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<ProfessorList> getUniversities() {
        return universities;
    }

    public void setUniversities(List<ProfessorList> universities) {
        this.universities = universities;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String concatSubjects() {
        StringBuilder result = new StringBuilder();
        for (Subject subject : subjects) {
            result.append(subject.getAbbreviation()).append(" ");
        }
        return result.toString();
    }


}
