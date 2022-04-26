package Core.Files;

import java.io.Serializable;
import java.time.LocalDate;

public class SaveInfo implements Serializable{
    public LocalDate date;
    public int generation;
    public boolean ableToReachGoal;
    public int steps;

    public SaveInfo(LocalDate date, int generation, boolean ableToReachGoal, int steps){
        this.date = date;
        this.generation = generation;
        this.ableToReachGoal = ableToReachGoal;
        this.steps = steps;
    }

    public SaveInfo(LocalDate date, int generation){
        this(date, generation, false, 0);
    }

    public SaveInfo(){
        date = null;
        generation = 0;
        ableToReachGoal = false;
        steps = 0;
    }
}
