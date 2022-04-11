package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/* TODO testing */
import Testing.Brain;

public class FileHandler {
    public static void save(Brain brain, int brainNum){
        try (FileOutputStream fos = new FileOutputStream("brain"+brainNum+".ser"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(brain);
        }catch (IOException ioe){
            System.out.println("Problem saving Brain"+brainNum);
            ioe.printStackTrace();
        }
    }

    public static Brain load(int brainNum){
        Brain loadedBrain = new Brain();

        try (FileInputStream fis = new FileInputStream("brain"+brainNum+".ser"); ObjectInputStream ois = new ObjectInputStream(fis);) {
            loadedBrain = (Brain) ois.readObject();
        } catch (IOException ioe){
            System.out.println("Error reading file");
            ioe.printStackTrace();
        }catch (ClassNotFoundException cnfe){
            System.out.println("Error loading brains");
            cnfe.printStackTrace();
        }
        return loadedBrain;
    }
}
