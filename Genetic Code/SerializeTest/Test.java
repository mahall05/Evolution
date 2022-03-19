package SerializeTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {
    private static int[] ints = {1, 3, 4, 5, 7};
    private static int[] savedInts;

    public static void main(String[] args){
        //save();
        load();
    }

    private static void save(){
        try{
            FileOutputStream fileOut = new FileOutputStream("ints.ser");//creates a card serial file in output stream
            ObjectOutputStream out = new ObjectOutputStream(fileOut);//routs an object into the output stream.
            out.writeObject(ints);// we designate our array of cards to be routed
            out.close();// closes the data paths
            fileOut.close();// closes the data paths
        }catch(IOException i){//exception stuff
            i.printStackTrace();
        }
    }

    private static void load(){
        try{ // If this doesnt work throw an exception
            FileInputStream fileIn = new FileInputStream("ints"+".ser");// Read serial file.
            ObjectInputStream in = new ObjectInputStream(fileIn);// input the read file.
            savedInts = (int[]) in.readObject();// allocate it to the object file already instanciated.
            in.close();//closes the input stream.
            fileIn.close();//closes the file data stream.
        }catch(IOException i)//exception stuff
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)//more exception stuff
        {
            System.out.println("Error");
            c.printStackTrace();
            return;
        }

        for(int i = 0; i < savedInts.length; i++){
            System.out.println(savedInts[i]);
        }
    }
}
