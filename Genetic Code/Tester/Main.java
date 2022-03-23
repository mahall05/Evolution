package Tester;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import Saves.SaveInfo;

public class Main {
    private static LocalDate date = LocalDate.now();
    public static void main(String[] args){
        for(int i = 0; i < 5; i++){
            SaveInfo info = new SaveInfo(LocalDate.now(), 0, false, 0);
            try{
                // Create file output stream
                FileOutputStream infoFileOutStr = new FileOutputStream("Saves/info"+(i+1)+".ser");
                // Create an object output stream and write object
                ObjectOutputStream infoOutStr = new ObjectOutputStream(infoFileOutStr);
                infoOutStr.writeObject(info);
                //Close all streams
                infoFileOutStr.close();
                infoOutStr.close();
            }catch(IOException exp){
                System.out.println("Error IOException");
                exp.printStackTrace();
            }
        }
    }
}
