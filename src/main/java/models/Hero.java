package models;

import com.sun.jdi.IntegerValue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.*;

import static java.time.format.DateTimeFormatter.*;

public class Hero {
    private String name;
    private static ArrayList<Hero> instances = new ArrayList<>();
    private String age;
    private String specialPower;
    private String weakness;
    private int id;

    public Hero(String name, String age, String specialPower, String weakness) {
        this.name = name;
        this.age = age;
        this.specialPower = specialPower;
        this.weakness = weakness;
        instances.add(this);
        this.id = instances.size();
    }

    public String getName(){
        return this.name;
    }
    public String getAge(){
        return this.age;
    }
    public String getPower(){
        return this.specialPower;
    }
    public String getWeakness(){
        return this.weakness;
    }

    public static void clearAllHeroes() {
    }

    public static ArrayList<Hero> getAll(){
        return instances;
    }

    public static Hero findById(int id){
        return instances.get(id-1);
    }

    public void update(String newContent) {
    }

    public void deleteHero() {
    }
}

