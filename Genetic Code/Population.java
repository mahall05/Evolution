import java.awt.*;

public class Population {
    private Body[] bodies;

    public static int brainSize = 400;

    public Population(int size){
        bodies = new Body[size];
        for(int i = 0; i < size; i++){
            bodies[i] = new Body();
        }
    }

    public void tick(){
        for(int i = 0; i < bodies.length; i++){
            if(bodies[i].brain.step > brainSize){
                bodies[i].dead = true;
            }
            else{
                bodies[i].tick();
            }
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < bodies.length; i++){
            bodies[i].render(g);
        }
    }
}
