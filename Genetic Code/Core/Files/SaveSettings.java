package Core.Files;

import java.io.Serializable;

import Core.Settings;

public class SaveSettings implements Serializable{
    public boolean calcBestStep;
    public int populationSize;

    public SaveSettings(){
        calcBestStep = Settings.calcBestStep;
        populationSize = Settings.populationSize;
    }
}
