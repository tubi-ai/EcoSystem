package com.company;

public class Crocodile implements Animal{
    @Override
    public String getAnimalName() {
        return "Crocodile";
    }

    @Override
    public int getGender() {
        return (int) (Math.random()*1);
    }

    public int getStrength(){
        return (int) (Math.random()*10+5);
    }
}

