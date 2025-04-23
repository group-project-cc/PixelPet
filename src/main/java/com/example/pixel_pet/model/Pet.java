// File: Pet.java (Model)
package com.example.pixel_pet.model;

public class Pet {
    private String name;
    private final String type;
    private int hungerLevel;
    private int happinessLevel;

    public Pet(String name, String type) {
        this.name = name;
        this.type = type;
        this.hungerLevel = 50;
        this.happinessLevel = 50;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getHungerLevel() { return hungerLevel; }
    public int getHappinessLevel() { return happinessLevel; }

    public void setName(String name) { this.name = name; }
    public void feed() { hungerLevel = Math.min(hungerLevel + 10, 100); }
    public void play() { happinessLevel = Math.min(happinessLevel + 10, 100); }
}