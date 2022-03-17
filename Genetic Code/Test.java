public class Test {
    private static int[] paths = new int[10];
    private static int[] newPaths = new int[10];

    public static void main(String[] args){
        for(int i = 0; i < paths.length; i++){
            paths[i] = randomInt(0, 10);
        }

        for(int i = 0; i < paths.length; i++){
            System.out.print(paths[i] + " ");
        }
        System.out.println();
        skipStep(0.5);
        for(int i = 0; i < paths.length; i++){
            System.out.print(paths[i] + " ");
        }
        System.out.println();
    }

    public static void skipStep(double chance){
        for(int i = 0; i < paths.length; i++){
          double random = Main.randomDouble(0, 1);
          if(random < chance){
            //skip a step
            for(int j = 0; j < i; j++){ // fill in every step before 
              newPaths[j] = paths[j];
            }

            for(int j = i; j < paths.length-i-1; j++){ // replace every step with the step afterwards
                newPaths[j] = paths[j+1];
            }

            newPaths[paths.length-1] = randomInt(0, 10);

            paths = newPaths;
          }
        }
      }

      public static int randomInt(int minInclusive, int maxInclusive){
        int range = maxInclusive - minInclusive + 1;
        int rng = (int)(Math.random() * range) + minInclusive;
        return rng;
    }
}
