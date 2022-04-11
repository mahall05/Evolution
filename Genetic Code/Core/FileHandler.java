package Core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Testing.AccelVector;

public class FileHandler {
    public static void save(AccelVector[] paths, int brainNum){
        try (FileOutputStream fos = new FileOutputStream("Exports/Brain"+brainNum+"/paths"+brainNum+".ser"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(paths);
        }catch (IOException ioe){
            System.out.println("Problem saving Brain"+brainNum);
            ioe.printStackTrace();
        }
    }

    public static AccelVector[] load(int brainNum){
        AccelVector[] loadedPaths = new AccelVector[2];

        try (FileInputStream fis = new FileInputStream("Exports/Brain"+brainNum+"/paths"+brainNum+".ser"); ObjectInputStream ois = new ObjectInputStream(fis);) {
            loadedPaths = (AccelVector[]) ois.readObject();
        } catch (IOException ioe){
            System.out.println("Error reading file");
            ioe.printStackTrace();
        }catch (ClassNotFoundException cnfe){
            System.out.println("Error loading brains");
            cnfe.printStackTrace();
        }
        return loadedPaths;
    }
}
