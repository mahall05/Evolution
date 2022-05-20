package Bodies;

import Core.Settings;
import Maps.Map;
import java.awt.*;

public class Population {
    public Body[] bodies;
    private int brainSize = 2000;

    public int gen = 1;
    public int loadedGen;

    private double fitnessSum;
    private int best;

    public double oldBestScore = 0;
    public double bestScore = 0;
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

    public void calculateFitnessSum(){
        fitnessSum = 0;
        for(int i = 0 ; i < bodies.length; i++){
            fitnessSum += bodies[i].fitness;
        }
    }

    private Body selectParent(){
        double random = Math.random() - (1 - fitnessSum);
        double runningSum = 0;

        for(int i = 0 ; i < bodies.length; i++){
            runningSum += bodies[i].fitness;
            if(runningSum > random){
                return bodies[i];
            }
        }

        return null;
    }

    public void mutate(){
        for(int i = 1; i < bodies.length; i++){
            bodies[i].brain.mutate(mutationRate);
        }
    }

    /*
    public void skipSteps(){
        for(int i = 1; i < bodies.length; i++){
            bodies[i].brain.skipStep(skipChance);
        }
    }
    */

    private void setBestDot(){
        double max = 0;
        int maxIndex = 0;
        for(int i = 0 ; i < bodies.length; i++){
            if(bodies[i].fitness > max){
                max = bodies[i].fitness;
                maxIndex = i;
            }
        }

        best = maxIndex;
        oldBestScore = bestScore;
        bestScore = bodies[best].fitness;
        System.out.println("Old best: " + oldBestScore);
        System.out.println("New best: " + bestScore);
        if(!ableToReachGoal){
            withinScoreRange();
        }
        else{
            mutationRate = 0.1;
            timesWithinScore = 0;
        }

        if(bodies[best].reachedGoal){
            brainSize = bodies[best].brain.step;
            System.out.println("Step: " + bodies[best].brain.step);
            if(bodies[best].brain.step < bestSteps){
                bestSteps = bodies[best].brain.step;
            }
        }
    }

    public void withinScoreRange(){
        if(bestScore *1000000 > oldBestScore *1000000 - 0.1 && bestScore *1000000 < oldBestScore *1000000 + 0.1){
            timesWithinScore++;
            System.out.println("Times: " + timesWithinScore);
        }
        else{
            timesWithinScore = 0;  // If the dots reach a new best score outside of the range, everything will reset back to normal
            mutationRate = 0.1;
            System.out.println("Times: " + timesWithinScore);
            System.out.println("Mutation Rate: " + mutationRate);
        }
    }
}
