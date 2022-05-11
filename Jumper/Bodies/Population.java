package Bodies;

import Core.Settings;
import Maps.Map;
import java.awt.*;

public class Population {
    public Body[] bodies;
    private int brainSize = 500;

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
}
