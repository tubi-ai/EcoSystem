package com.company;
import javax.swing.Timer;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PreditorBearFish bf = new PreditorBearFish();
        Scanner kb = new Scanner(System.in);
        Timer t = new Timer (500, event -> bf.moveAndDisplay());
        t.start();
        kb.nextLine();
    }
}
