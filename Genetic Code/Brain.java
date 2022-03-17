public class Brain {
  public int step = 0;
  public AccelVector[] paths;

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
        paths[i] = new AccelVector(Main.randomDouble(-7, 7), Main.randomDouble(-7, 7));
      }
    }
  }
}
