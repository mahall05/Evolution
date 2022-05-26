package Bodies;

import Core.Main;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

public class Brain {
    public Actions[] actions;
    public int step = 0;
    public int bestStep = 0;

    private int testNum = 5;

    public Brain(int size){
        actions = new Actions[size];
        randomize();
        /*
        for(int i = 0; i < size; i++){
            System.out.println(actions[i]);
        }
        */
    }

    public Brain(Actions[] actions){
        this.actions = actions;
    }

    public void randomize(){
        for(int i = 0; i < actions.length; i++){
            actions[i] = randomStep();
            if(actions[i] != Actions.Jump){
                for(int j = i; j < i+testNum; j++){
                    if(j<actions.length)
                        actions[j] = actions[i];
                }
                i += 4;
            }
        }
    }

    public void randomizeFromBest(){
        for(int i = bestStep+1; i < actions.length; i++){
            actions[i] = randomStep();
        }
    }

    public Brain clone(){
        Brain clone = new Brain(actions.length);
        for(int i = 0; i < actions.length; i++){
            clone.actions[i] = actions[i];
        }
        return clone;
    }

    public void mutate(double mutationRate){
        // % chance each step will have a mutation, where 1 is 100%
        for(int i = 0; i < actions.length; i++){
            double random = Main.randomDouble(0, 1);
            if(random < mutationRate){
                //randomize the step
                actions[i] = randomStep();
            }
        }
    }

    public Actions randomStep(){
        Actions action = Actions.WRONG;
        int chance = Main.randomInt(1, 100);

        if (chance >= 1 && chance <= 20){
            action = Actions.Jump;
        }else if(chance > 20 && chance <= 60){ // TODO CHANGE THIS BACK TO 20 - 60
            action = Actions.Left;
        }else if(chance > 60 && chance <= 100){
            action = Actions.Right;
        }
        return action;
    }

    public void skipStep(double chance){
        for(int i = 0; i < actions.length; i++){
          double random = Main.randomDouble(0, 1);
          Actions[] newActions = new Actions[actions.length];
          if(random < chance){
            //skip a step
            for(int j = 0; j < i; j++){
              newActions[j] = actions[j]; // fill in every step before
            }
    
            for(int j = i; j < actions.length-i-1; j++){
              newActions[j] = actions[j+1]; // replace every step with the following step, effectively removing the chosen step
            }
    
            newActions[actions.length-1] = randomStep();
    
            actions = newActions;
          }
        }
      }
}
