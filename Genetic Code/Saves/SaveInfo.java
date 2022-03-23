package Saves;

import java.time.LocalDate;

public class SaveInfo {
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
}
