package Tester;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import Dots.Brain;

public class Main {
    private static Brain loadedBrain;
    public static void main(String[] args){
        loadedBrain = load(1);
        printPaths();
    }

    private static void printPaths(){
        for(int i = 0; i < loadedBrain.paths.length; i++){
            System.out.println(loadedBrain.paths[i].xAcc + ", " + loadedBrain.paths[i].yAcc);
        }
    }

    private static Brain load(int slot){
        try{
            FileInputStream fileInStr = new FileInputStream("Saves/brain"+slot+".ser");
            ObjectInputStream objInStr = new ObjectInputStream(fileInStr);
            Brain brain = (Brain) objInStr.readObject();
            objInStr.close();
            fileInStr.close();
            return brain;
        }catch(IOException  exp){
            System.out.println("Error IOException");
            exp.printStackTrace();
            return null;
        }catch(ClassNotFoundException cexp){
            System.out.println("Brian class not found");
            cexp.printStackTrace();
            return null;
        }
    }
}
