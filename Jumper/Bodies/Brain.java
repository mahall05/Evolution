package Bodies;

import Core.Main;

public class Brain {
    public Actions[] actions;
    public int step = 0;
    public int bestStep = 0;

    public Brain(int size){
        actions = new Actions[size];
    }

    public void randomize(){
        for(int i = 0; i < actions.length; i++){
            actions[i] = randomStep();
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
        int num = Main.randomInt(0, 2);

            switch(num){
                case(0):
                    action = Actions.Right;
                    break;
                case(1):
                    action = Actions.Left;
                    break;
                case(2):
                    action = Actions.Jump;
                    break;
                default:
                    action = Actions.Jump;
                    break;
            }
            return action;
    }
}
