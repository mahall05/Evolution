package Testing;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args){
        Brain brain = new Brain();

        FileHandler.save(brain.paths, 1);

        System.out.println("Initial brain:");
        for(int i = 0; i < brain.paths.length; i++){
            System.out.println(brain.paths[i].xAcc+", "+brain.paths[i].yAcc);
        }

        System.out.println("\nLoaded brain:");
        Brain loadedBrain = new Brain(FileHandler.load(1));
        for(int i = 0; i < loadedBrain.paths.length; i++){
            System.out.println(loadedBrain.paths[i].xAcc+", "+loadedBrain.paths[i].yAcc);
        }
    }
}
