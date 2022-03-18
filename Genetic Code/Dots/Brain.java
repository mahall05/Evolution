package Dots;
import Core.Main;

public class Brain {
  public int step = 0;
  public AccelVector[] paths;
  public int bestStep = 0;

  public Brain(int size){
    paths = new AccelVector[size];
    randomize();
  }

  public void randomize(){
    for(int i = 0; i < paths.length; i++){
      paths[i] = new AccelVector(Main.randomDouble(-3, 3), Main.randomDouble(-3, 3));
    }
  }

  public Brain clone(){
    Brain clone = new Brain(paths.length);
    for(int i = 0; i < paths.length; i++){
      clone.paths[i] = paths[i];
    }
    return clone;
  }

  public void mutate(double mutationRate){
    // % chance each step will have a mutation, where 1 is 100%
    for(int i = 0; i < paths.length; i++){
      double random = Main.randomDouble(0, 1);
      if(random < mutationRate){
        //randomize the direction
        paths[i] = new AccelVector(Main.randomDouble(-4, 4), Main.randomDouble(-4, 4));
      }
    }
  }

  public void skipStep(double chance){
    for(int i = 0; i < paths.length; i++){
      double random = Main.randomDouble(0, 1);
      AccelVector[] newPaths = new AccelVector[paths.length];
      if(random < chance){
        //skip a step
        for(int j = 0; j < i; j++){
          newPaths[j] = paths[j]; // fill in every step before
        }

        for(int j = i; j < paths.length-i-1; j++){
          newPaths[j] = paths[j+1]; // replace every step with the following step, effectively removing the chosen step
        }

        newPaths[paths.length-1] = new AccelVector(Main.randomDouble(-3, 3), Main.randomDouble(-3, 3));

        paths = newPaths;
      }
    }
  }
}
