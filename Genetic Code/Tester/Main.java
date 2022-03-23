package Tester;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Dots.Population;

public class Main {
    public static Building firstBuilding;
    public static Building secondBuilding;
    public static Population testDots;
    public static void main(String[] args){
        //save(firstBuilding);
        //loadBuilding();
        testDots = new Population(100);
        testDots.save();
    }

    public static void save(Building b){
        try{
            // Create file output stream
            FileOutputStream fileOutStr = new FileOutputStream("theBuilding.ser");
            // Create an object output stream and write object
            ObjectOutputStream objOutStr = new ObjectOutputStream(fileOutStr);
            objOutStr.writeObject(firstBuilding);
            //Close all streams
            objOutStr.close();
            fileOutStr.close();
            System.out.print("Serialized data is saved in a file - theBuilding.ser");
        }catch(IOException exp){
            System.out.println("Error IOException");
            exp.printStackTrace();
        }
    }

    public static void loadBuilding(){
        try{
            FileInputStream fileInStr = new FileInputStream("theBuilding.ser");
            ObjectInputStream objInStr = new ObjectInputStream(fileInStr);
            secondBuilding = (Building) objInStr.readObject();
            objInStr.close();
            fileInStr.close();
        }catch(IOException  exp){
            System.out.println("Error IOException");
            exp.printStackTrace();
            return;
        }catch(ClassNotFoundException cexp){
            System.out.println("BuildingGUI class not found");
            cexp.printStackTrace();
            return;
        }
        System.out.println(", theBuilding has been deserialized");
    }
}
