package Core.Files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import Dots.AccelVector;
import Dots.Population;

public class FileHandler {
    public static void save(Population pop, int brainNum){
        AccelVector[] paths = pop.getBestPaths();
        SaveInfo info = new SaveInfo(LocalDate.now(), pop.gen, pop.ableToReachGoal, pop.bestSteps);
        SaveSettings settings = new SaveSettings();

        try (FileOutputStream fos = new FileOutputStream("Exports/Brain"+brainNum+"/paths"+brainNum+".ser"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(paths);
        }catch (IOException ioe){
            System.out.println("Problem saving Brain"+brainNum);
            ioe.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream("Exports/Brain"+brainNum+"/info"+brainNum+".ser"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(info);
        }catch (IOException ioe){
            System.out.println("Problem saving Brain"+brainNum);
            ioe.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream("Exports/Brain"+brainNum+"/settings"+brainNum+".ser"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(settings);
        }catch (IOException ioe){
            System.out.println("Problem saving Brain"+brainNum);
            ioe.printStackTrace();
        }
    }

    public static AccelVector[] loadPaths(int brainNum){
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

    public static SaveInfo loadInfo(int brainNum){
        SaveInfo info = new SaveInfo();

        try (FileInputStream fis = new FileInputStream("Exports/Brain"+brainNum+"/info"+brainNum+".ser"); ObjectInputStream ois = new ObjectInputStream(fis);) {
            info = (SaveInfo) ois.readObject();
        } catch (IOException ioe){
            System.out.println("Error reading file");
            ioe.printStackTrace();
        }catch (ClassNotFoundException cnfe){
            System.out.println("Error loading brains");
            cnfe.printStackTrace();
        }
        return info;
    }

    public static AccelVector[] loadSettings(int brainNum){
        AccelVector[] loadedPaths = new AccelVector[2];

        try (FileInputStream fis = new FileInputStream("Exports/Brain"+brainNum+"/settings"+brainNum+".ser"); ObjectInputStream ois = new ObjectInputStream(fis);) {
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
