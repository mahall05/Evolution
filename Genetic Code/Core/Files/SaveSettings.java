package Core.Files;

import Core.Settings;

public class SaveSettings {
    public boolean calcBestStep;
    public int populationSize;

    public SaveSettings(){
        calcBestStep = Settings.calcBestStep;
        populationSize = Settings.populationSize;
    }
}
