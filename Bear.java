package com.company;

public class Bear implements Animal{
    @Override
    public String getAnimalName() {
        return "Bear";
    }

    @Override
    public int getGender() {
        return (int) (Math.random()*1);
    }

    public int getStrength(){
        return (int) (Math.random()*10+10);
    }
}

