package com.company;
import java.util.Random;

public class PreditorBearFish {
    public static final int width = 40;
    public static final int height = 10;
    //public static final double toMove = 0.5;
    public static int begining_fish = 100;
    public static int begining_bear = 45;
    public static int begining_crocodile = 35;
    private static final Animal fish = new Fish();
    private static final Animal bear = new Bear();
    private static final Animal crocodile = new Crocodile();
    public static int Bear_strength = bear.getStrength();
    public static int Crocodile_strength = crocodile.getStrength();
    public static int BearMove = 0;
    public static int CrocodileMove = 0;
    //public static int gender = (int) (Math.random()*1);

    private final Animal [][] ecoArray;
    private int fishcnt = 0;
    private int bearcnt = 0;
    private int crocodilecnt = 0;


    public PreditorBearFish(){
        ecoArray = new Animal [width][height];

        // Add fish randomly in our ecosystem
        while (fishcnt < begining_fish) {
            fishcnt += addNewAnimal (new Fish());

        } // Add bear randomly in our ecosystem
        while (bearcnt < begining_bear) {
            bearcnt += addNewAnimal (new Bear());

        }  // Add crocodile randomly in our ecosystem
        while (crocodilecnt < begining_crocodile) {
            crocodilecnt += addNewAnimal (new Crocodile());

        }
    }
    private int addNewAnimal(Animal animal){
        int addcnt = 0;
        int totalCell = fishcnt+bearcnt+crocodilecnt;
        if (totalCell < width * height){
            while (addcnt == 0) {
                // get a random coordinate: x, y
                // and see if that location is null
                int x = (int) (Math.random()*width);
                int y = (int) (Math.random()*height);

                if (ecoArray[x][y] == null){
                    ecoArray[x][y] = animal;
                    addcnt++;
                }
            }
        }
        return addcnt;
    }
    public void move(){
        // for each fish, bear or crocodile
        // move them 1 cell vertically or horizontally
        for (int y = 0; y<height; y++){
            for (int x = 0; x<width; x++) {
                if (ecoArray[x][y] != null){
                    double chance = Math.random();


                        int xChange = (int) (Math.random()*3-1);// -1,0,1
                        int yChange = (int) (Math.random()*3-1);// -1,0,1

                        int xNew = x + xChange;
                        int yNew = y + yChange;
                        boolean xCanMove = false;
                        boolean yCanMove = false;

                        // Collisions
                        if (xChange !=0 && xNew >= 0 && xNew < width){
                            xCanMove = true;
                        }
                        if (yChange !=0 && yNew >= 0 && yNew < height){
                            yCanMove = true;
                        }
                        if(xCanMove == true || yCanMove == true){
                            int xFinal = x;
                            int yFinal = y;

                            if(xCanMove)
                                xFinal = xNew;
                            if(yCanMove)
                                yFinal = yNew;

                            if(ecoArray[xFinal][yFinal ] == null){
                                ecoArray[xFinal][yFinal ] = ecoArray[x][y];
                                ecoArray[x][y] = null;
                            }
                            else if (ecoArray[x][y].getClass() == ecoArray[xFinal][yFinal].getClass()){
                                // Create baby fish or bear or crocodile
                                if (ecoArray[x][y].getClass().getName().equals("Fish")){
                                    if(ecoArray[x][y].getGender()!=ecoArray[xFinal][yFinal].getGender()){
                                        fishcnt += addNewAnimal(new Fish());
                                    } else{
                                        fishcnt--;
                                        ecoArray[x][y] = null;}
                                }

                                if (ecoArray[x][y].getClass().getName().equals("Bear")){
                                    if(ecoArray[x][y].getGender()!=ecoArray[xFinal][yFinal].getGender())
                                        bearcnt += addNewAnimal(new Bear());
                                    else{
                                        bearcnt--;
                                        ecoArray[x][y] = null;}
                                }

                                if (ecoArray[x][y].getClass().getName().equals("Crocodile")){
                                    if(ecoArray[x][y].getGender()!=ecoArray[xFinal][yFinal].getGender())
                                        crocodilecnt += addNewAnimal(new Crocodile());
                                    else{
                                        crocodilecnt--;
                                        ecoArray[x][y] = null;}
                                }


                            }
                            else if (ecoArray[x][y].getClass() != ecoArray[xFinal][yFinal].getClass()){
                                // if Bear or Crocodile is moving onto a Fish -> eat the fish
                                // or Bear or Crododile is moving onto each other they will fight
                                if (ecoArray[x][y].getClass().getName().equals("Fish")){
                                    if (fishcnt > 0){
                                    ecoArray[x][y] = null;
                                    fishcnt--;
                                    }
                                }
                                if (ecoArray[xFinal][yFinal].getClass().getName().equals("Fish")){
                                    if (fishcnt > 0){
                                        ecoArray[xFinal][yFinal] = ecoArray[x][y];
                                        fishcnt--;
                                    }
                                }

                                else if (ecoArray[x][y].getClass().getName().equals("Bear")){
                                    if (ecoArray[xFinal][yFinal].getClass().getName().equals("Crocodile")){
                                        if (Bear_strength > Crocodile_strength){
                                            ecoArray[xFinal][yFinal] = ecoArray[x][y];
                                            crocodilecnt--;
                                            BearMove++;
                                            if(BearMove == 10){
                                                Bear_strength--;
                                                BearMove = 0;
                                            }
                                        } else {
                                            ecoArray[x][y] = null;
                                            bearcnt--;
                                            CrocodileMove++;
                                            if(CrocodileMove == 15){
                                                Crocodile_strength--;
                                                CrocodileMove = 0;
                                            }
                                        }
                                    }

                                } else if (ecoArray[x][y].getClass().getName().equals("Crocodile")){
                                    if (ecoArray[xFinal][yFinal].getClass().getName().equals("Bear")){
                                        if (Crocodile_strength > Bear_strength){
                                            ecoArray[xFinal][yFinal] = ecoArray[x][y];
                                            bearcnt--;
                                            CrocodileMove++;
                                            if(BearMove == 10){
                                                Bear_strength--;
                                                BearMove = 0;
                                            }
                                        } else {
                                            ecoArray[x][y] = null;
                                            crocodilecnt--;
                                            BearMove++;
                                            if(CrocodileMove == 15){
                                                Crocodile_strength--;
                                                CrocodileMove = 0;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                }
            }
        }
    }
    public void moveAndDisplay(){
        System.out.println(this);
        move();
    }
    @Override
    public String toString(){
        String output = "";
        for (int y = 0; y<height; y++){
            for (int x = 0; x<width; x++){
                if(ecoArray[x][y] == null)
                    output+=".";
                else if(ecoArray[x][y] instanceof Fish)
                    output+= "F";
                else if(ecoArray[x][y] instanceof Bear)
                    output+= "B";
                else if(ecoArray[x][y] instanceof Crocodile)
                    output+= "C";
            } output+= "\n";
        } int totalCell = fishcnt+bearcnt+crocodilecnt;
        output += "\nTotal Cell: "+ totalCell +"\nFish: " + fishcnt+" Bear: "+ bearcnt+ " Crocodile: "+crocodilecnt;
        return output;
    }
}
