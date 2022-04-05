package Tester;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import Dots.Brain;

public class Main {
    private static House famHouse = new House(5, 5, 1);
    public static void main(String[] args){
        printInfo(famHouse);
    }

    public static <T extends Building> void printInfo(Class<T> b){
        
    }
}
