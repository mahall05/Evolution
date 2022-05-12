package Bodies;

import Core.Settings;
import Maps.Map;
import java.awt.*;

public class Population {
    public Body[] bodies;
    private int brainSize = 1500;

    public int gen = 1;
    public int loadedGen;

    private double fitnessSum;
    private int best;

    public double oldBestScore = 0;
    public double newBestScore = 0;
    private int timesWithinScore = 0;

    public double mutationRate = 0.1;
    public double skipChance = 0.001;

    public boolean ableToReachGoal = false;
    public int bestSteps = brainSize;

    public Population(){
        bodies = new Body[Settings.populationSize];
        loadedGen = 0;
        for(int i = 0; i < Settings.populationSize; i++){
            bodies[i] = new Body(brainSize);
        }
    }

    public Population(Actions[] actions, int loadedGen){
        bodies = new Body[Settings.populationSize];
        this.loadedGen = loadedGen;
        for(int i = 0; i < Settings.populationSize; i++){
            bodies[i] = new Body(actions);
        }
        bodies[0].isBest = true;
    }

    public void render(Graphics g){
        for(int i = 0; i < bodies.length; i++){
            bodies[i].render(g);
        }
        bodies[0].render(g); // TODO why is this here?
    }

    public void tick(Map map){
        for(int i = 0; i < bodies.length; i++){
            if(bodies[i].brain.step > brainSize){
                bodies[i].dead = true;
            }else{
                bodies[i].tick(map);
            }
        }
    }

    public void calculateFitness(Map map){
        for(int i = 0; i < bodies.length; i++){
            bodies[i].calculateFitness(map.goal);
        }
    }

    public boolean allBodiesDead(){
        for(int i = 0; i < bodies.length; i++){
            if(!bodies[i].dead && !bodies[i].reachedGoal){
                return false;
            }
        }
        return true;
    }

    public void naturalSelection(Map map){
        Body[] newBodies = new Body[Settings.populationSize];
        setBestDot();
        calculateFitnessSum();

        if(Settings.calcBestStep){
            newBodies[0] = bodies[best].cloneFromBestStep();
        }else{
            newBodies[0] = bodies[best].clone();
        }

        newBodies[0].isBest = true;
        for(int i = 1; i < newBodies.length; i++){
            Body parent = selectParent();
            if(Settings.calcBestStep){
                newBodies[i] = parent.cloneFromBestStep();
            }else{
                newBodies[i] = parent.clone();
            }
        }

        if(timesWithinScore >= 5){
            mutationRate = mutationRate + 0.05;
            System.out.println("Mutation rate: " + mutationRate);
        }

        if(!ableToReachGoal){
            for(int i = 0; i < bodies.length; i++){
                if(bodies[i].ableToReachGoal){
                    ableToReachGoal = true;
                }
            }
        }

        bodies = newBodies.clone();
        gen++;
    }
}
