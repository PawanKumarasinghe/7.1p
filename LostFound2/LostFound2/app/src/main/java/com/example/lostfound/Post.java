package com.example.lostfound;

public class Post {
    private int id;
    private String category, name, contact, info, timing, place;

    public Post(int id, String category, String name, String contact, String info, String timing, String place) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.contact = contact;
        this.info = info;
        this.timing = timing;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public String getFormatted() {
        return category + ": " + info + "\n" + timing + "\nLocation: " + place;
    }

    @Override
    public String toString() {
        return category + " - " + info;
    }
}