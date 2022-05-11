package Bodies;

import Core.Main;

public class Brain {
    public Actions[] actions;
    public int step = 0;
    public int bestStep = 0;

    public Brain(int size){
        actions = new Actions[size];
        randomize();
        
        for(int i = 0; i < size; i++){
            System.out.println(actions[i]);
        }
        
    }

    public void randomize(){
        for(int i = 0; i < actions.length; i++){
            actions[i] = randomStep();
            if(actions[i] != Actions.Jump){
                actions[i+1] = actions[i];
                actions[i+2] = actions[i];
                actions[i+3] = actions[i];
                actions[i+4] = actions[i];
            }
            /*
            if(actions[i] != Actions.Jump){
                for(int j = i; j < i+5; j++){
                    if(j < 500)
                        actions[j] = actions[i];
                }
            }
            */
            i += 4;
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
        Actions action;
        int chance = Main.randomInt(1, 100);
        int chanceOfJump = 20;

        if(chance <= chanceOfJump){
            action = Actions.Jump;
        }else if(chance > chanceOfJump && chance < ((100-chanceOfJump)/2)){
            action = Actions.Left;
        }else{
            action = Actions.Right;
        }

        return action;
    }
}
