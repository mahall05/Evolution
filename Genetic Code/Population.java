import java.awt.Graphics;

public class Population {
    private Body[] bodies;
    private int brainSize = 500;

    public static int gen = 1;

    public Population(int size){
        bodies = new Body[size];
        for(int i = 0; i < size; i++){
            bodies[i] = new Body(brainSize);
        }
    }

    public void render(Graphics g){
        for(int i =  0; i < bodies.length; i++){
            bodies[i].render(g);
        }
    }

    public void tick(Obstacles obs){
        for(int i = 0; i < bodies.length; i++){
            if(bodies[i].brain.step > brainSize){
                bodies[i].dead = true;
            }
            else{
                bodies[i].tick(obs);
            }
        }
    }

    public boolean allDotsDead(){
        for(int i = 0; i < bodies.length; i++){
            if(!bodies[i].dead && !bodies[i].reachedGoal){ // If there is still even one dot alive, then all of them aren't dead so return false
                return false;
            }
        }
        return true; // If it never returns false, then they are all dead so return true
    }
}
