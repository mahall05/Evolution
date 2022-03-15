public class Brain {
  public int step = 0;
  public AccelVector[] paths;

  public Brain(int size){
    paths = new AccelVector[size];
    randomize();
  }

  public void randomize(){
    for(int i = 0; i < paths.length; i++){
      paths[i] = new AccelVector(Main.randomDouble(-7, 7), Main.randomDouble(-7, 7));
    }
  }
}
