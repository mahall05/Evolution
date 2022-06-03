package Core.Files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import Bodies.Actions;
import Bodies.Population;
import Maps.Map;

public class FileHandler {
    public static void save(Population pop, Map map, int brainNum){
        Actions[] actions = pop.getBestActions();
        SaveInfo info = new SaveInfo(LocalDate.now(), map, pop.gen, pop.ableToReachGoal, pop.bestSteps);
        SaveSettings settings = new SaveSettings();

        try (FileOutputStream fos = new FileOutputStream("Exports/Brain"+brainNum+"/actions"+brainNum+".ser"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(actions);
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
            System.out.println("Problem saving Brain settings "+brainNum);
            ioe.printStackTrace();
        }
    }

    public static void saveSettings(){
        SaveSettings settings = new SaveSettings();

        try (FileOutputStream fos = new FileOutputStream("Core/settings.ser"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(settings);
        }catch (IOException ioe){
            System.out.println("Problem saving settings");
            ioe.printStackTrace();
        }
    }

    public static void saveInfo(SaveInfo info, int brainNum){
        try (FileOutputStream fos = new FileOutputStream("Exports/Brain"+brainNum+"/info"+brainNum+".ser"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(info);
        }catch (IOException ioe){
            System.out.println("Problem saving Brain info "+brainNum);
            ioe.printStackTrace();
        }
    }

    public static Actions[] loadActions(int brainNum){
        Actions[] loadedActions = new Actions[2];

        try (FileInputStream fis = new FileInputStream("Exports/Brain"+brainNum+"/actions"+brainNum+".ser"); ObjectInputStream ois = new ObjectInputStream(fis);) {
            loadedActions = (Actions[]) ois.readObject();
        } catch (IOException ioe){
            System.out.println("Error reading file " + brainNum);
            ioe.printStackTrace();
        }catch (ClassNotFoundException cnfe){
            System.out.println("Error loading brains");
            cnfe.printStackTrace();
        }
        return loadedActions;
    }

    public static SaveInfo loadInfo(int brainNum){
        SaveInfo info = new SaveInfo();

        try (FileInputStream fis = new FileInputStream("Exports/Brain"+brainNum+"/info"+brainNum+".ser"); ObjectInputStream ois = new ObjectInputStream(fis);) {
            info = (SaveInfo) ois.readObject();
            System.out.println("Succesfully loaded");
        } catch (IOException ioe){
            saveInfo(info, brainNum);
            System.out.println("Error reading file " + brainNum);
            ioe.printStackTrace();
            info = null;
        }catch (ClassNotFoundException cnfe){
            System.out.println("Error loading brains");
            cnfe.printStackTrace();
            info = null;
        }
        return info;
    }

    public static SaveSettings loadBrainSettings(int brainNum){
        SaveSettings settings = new SaveSettings();

        try (FileInputStream fis = new FileInputStream("Exports/Brain"+brainNum+"/settings"+brainNum+".ser"); ObjectInputStream ois = new ObjectInputStream(fis);) {
            settings = (SaveSettings) ois.readObject();
        } catch (IOException ioe){
            System.out.println("Error reading file " + brainNum);
            ioe.printStackTrace();
        }catch (ClassNotFoundException cnfe){
            System.out.println("Error loading brains");
            cnfe.printStackTrace();
        }
        return settings;
    }

    public static SaveSettings loadSettings(){
        SaveSettings settings = new SaveSettings();

        try (FileInputStream fis = new FileInputStream("Core/settings.ser"); ObjectInputStream ois = new ObjectInputStream(fis);) {
            settings = (SaveSettings) ois.readObject();
        } catch (IOException ioe){
            System.out.println("Error reading file");
            ioe.printStackTrace();
        }catch (ClassNotFoundException cnfe){
            System.out.println("Error loading brains");
            cnfe.printStackTrace();
        }
        return settings;
    }
}
