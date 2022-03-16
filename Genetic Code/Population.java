import java.awt.Graphics;

public class Population {
    private Body[] bodies;
    private int brainSize = 400;

    public int gen = 1;

    private double fitnessSum;
    private int best;

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
        bodies[0].render(g);
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

    public void calculateFitness(Obstacles obs){
        for(int i = 0; i < bodies.length; i++){
            bodies[i].calculateFitness(obs.goal);
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

    public void naturalSelection(Obstacles obs){
        Body[] newBodies = new Body[bodies.length];
        setBestDot();
        calculateFitnessSum();

        newBodies[0] = bodies[best].clone();
        newBodies[0].isBest = true;
        for(int i = 1; i < newBodies.length; i++){
            Body parent = selectParent();

            newBodies[i] = parent.clone();
        }

        bodies = newBodies.clone();
        gen++;
    }

    public void calculateFitnessSum(){
        fitnessSum = 0;
        for(int i = 0; i < bodies.length; i++){
            fitnessSum += bodies[i].fitness;
        }
    }

    private Body selectParent(){
        //double random = Main.randomDouble(0, fitnessSum);
        double random = Math.random() - (1 - fitnessSum);

        double runningSum = 0;

        for(int i = 0; i < bodies.length; i++){
            runningSum += bodies[i].fitness;
            if(runningSum > random){
                return bodies[i];
            }
        }

        return null; // This is just in case. Should never return null
    }

    public void mutate(){
        for(int i = 1; i < bodies.length; i++){
            bodies[i].brain.mutate();
        }
    }

    private void setBestDot(){
        double max = 0;
        int maxIndex = 0;
        for(int i = 0; i < bodies.length; i++){
            if(bodies[i].fitness > max){
                max = bodies[i].fitness;
                maxIndex = i;
            }
        }

        best = maxIndex;

        if(bodies[best].reachedGoal){
            brainSize = bodies[best].brain.step;
            System.out.println("Step: " + bodies[best].brain.step);
        }
    }
}
