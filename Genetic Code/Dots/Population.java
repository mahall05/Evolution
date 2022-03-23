package Dots;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import Core.Constants;
import Core.Main;
import Core.Settings;
import More.Obstacles;
import Saves.SaveInfo;

public class Population {
    private Body[] bodies;
    private int brainSize = 400;

    public int gen = 1;

    private double fitnessSum;
    private int best;

    public double oldBestScore = 0;
    public double bestScore = 0;
    private int timesWithinScore = 0;

    public double mutationRate = 0.1;
    public double skipChance = 0.001;

    private Brain loadedBrain;
    private boolean ableToReachGoal = false;
    private int bestSteps = brainSize;

    public Population(int size, boolean load, int slot){
        if(load){
            load(slot);
            bodies = new Body[size];
            for(int i = 0; i < size; i++){
                bodies[i] = new Body(brainSize);
            }
            bodies[0].brain = loadedBrain;
            bodies[0].isBest = true;
        }else{
            bodies = new Body[size];
            for(int i = 0; i < size; i++){
                bodies[i] = new Body(brainSize);
            }
        }
    }

    public Population(int size){
        this(size, false, 0);
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

        if(Settings.calcBestStep){
            newBodies[0] = bodies[best].cloneFromBestStep();
        }else{
            newBodies[0] = bodies[best].clone();
        }

        newBodies[0].isBest = true;
        for(int i = 1; i < newBodies.length; i++){
            Body parent = selectParent();
            if(Settings.calcBestStep){
                parent.brain.randomizeFromBest();
            }

            newBodies[i] = parent.clone();
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
            bodies[i].brain.mutate(mutationRate);
        }
    }

    public void skipSteps(){
        for(int i = 1; i < bodies.length; i++){
            bodies[i].brain.skipStep(skipChance);
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

    public void save(int slot){
        SaveInfo info = new SaveInfo(LocalDate.now(), gen, ableToReachGoal, bestSteps);
        try{
            // Create file output stream
            FileOutputStream fileOutStr = new FileOutputStream("Saves/brain"+slot+".ser");
            FileOutputStream infoFileOutStr = new FileOutputStream("Saves/info"+slot+".ser");
            // Create an object output stream and write object
            ObjectOutputStream objOutStr = new ObjectOutputStream(fileOutStr);
            ObjectOutputStream infoOutStr = new ObjectOutputStream(infoFileOutStr);
            objOutStr.writeObject(bodies[0].brain);
            infoOutStr.writeObject(info);
            //Close all streams
            objOutStr.close();
            fileOutStr.close();
            infoFileOutStr.close();
            infoOutStr.close();
            System.out.print("Serialized data is saved in a file - Saves/brain"+slot+".ser");
        }catch(IOException exp){
            System.out.println("Error IOException");
            exp.printStackTrace();
        }
    }

    public void load(int slot){
        try{
            FileInputStream fileInStr = new FileInputStream("Saves/brain"+slot+".ser");
            ObjectInputStream objInStr = new ObjectInputStream(fileInStr);
            loadedBrain = (Brain) objInStr.readObject();
            objInStr.close();
            fileInStr.close();
        }catch(IOException  exp){
            System.out.println("Error IOException");
            exp.printStackTrace();
            return;
        }catch(ClassNotFoundException cexp){
            System.out.println("Brian class not found");
            cexp.printStackTrace();
            return;
        }
        System.out.println(", brain has been deserialized");
    }
}
