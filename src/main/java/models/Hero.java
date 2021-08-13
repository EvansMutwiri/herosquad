package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Hero {
    private String content;
    private static ArrayList<Hero> instances = new ArrayList<>();
    private boolean published;
    private LocalDateTime createdAt;
    private int id;

    public Hero(String content) {
        this.content = content;
        this.published = false;
        this.createdAt = LocalDateTime.now();
        instances.add(this);
        this.id = instances.size();
    }

    public String getContent() {
        return content;
    }

    public static ArrayList<Hero> getAll(){
        return instances;
    }

    public static void clearAllHeros() {
        instances.clear();
    }

    public boolean getPublished() {
        return this.published;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public static Hero findById(int id){
        return instances.get(id-1);
    }

    public void update(String content) {
        this.content = content;
    }
}

