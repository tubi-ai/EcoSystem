package com.company;

public class Fish implements Animal{
    @Override
    public String getAnimalName() {
        return "Fish";
    }

    @Override
    public int getGender() {
        return (int) (Math.random()*1);
    }

    //@Override
    public int getStrength(){
        return 0;
    }
}
