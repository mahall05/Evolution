public class Brain {
    public Path[] directions;
    public int step = 0;

    public Brain(int size){
        directions = new Path[size];
        randomize();
    }

    private void randomize(){
        for(int i = 0; i < directions.length; i++){
            directions[i] = new Path(Game.randomDouble(-7, 7), Game.randomDouble(-7, 7));
        }
    }
}
