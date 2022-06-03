package Menus;

import Core.Constants;
import Core.Main;
import Core.Settings;

import java.awt.*;

public class SettingsMenu {
    public Button back;
    public Button fitnessToggle;
    public Button[] populationModifiers = new Button[2];
    public Button save;

    public Main.STATE prevState = Main.STATE.Start;

    public SettingsMenu(){
        /* Back Button */
        back = new Button(40, Constants.HEIGHT-100, 75, 25);
        back.setButtonColor(Color.white);
        back.setLabel("Back", Color.red, 20, true);
        back.offsetLabel(11, -45);

        /* Fitness Toggle Button */
        fitnessToggle = new Button(80, 40, 75, 30);
        fitnessToggle.setButtonColor(Color.white);
        fitnessToggle.setLabel("Toggle", Color.red, 20, true);
        fitnessToggle.offsetLabel(3, -45);

        /* Population Minus */
        populationModifiers[0] = new Button(58, 113, 30, 20);
        populationModifiers[0].setButtonColor(Color.white);
        populationModifiers[0].setLabel("-", Color.red, 20, true);
        populationModifiers[0].offsetLabel(8, -49);

        /* Population Plus */
        populationModifiers[1] = new Button(155, 113, 30, 20);
        populationModifiers[1].setButtonColor(Color.white);
        populationModifiers[1].setLabel("+", Color.red, 20, true);
        populationModifiers[1].offsetLabel(6, -47);

        /* Save Settings Button */
        save = new Button(Constants.WIDTH-125, Constants.HEIGHT-100, 75, 25);
        save.setButtonColor(Color.white);
        save.setLabel("Save", Color.red, 20, true);
        save.offsetLabel(10, -45);
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 60));
        g.setColor(Color.white);
        g.drawString("Settings", (Constants.WIDTH/2)-110, (Constants.HEIGHT/2)-300);

        back.render(g);

        g.drawString("Fitness Mode: " + (Settings.calcBestStep ? "Best Step" : "Best Death"), 10, 35);
        fitnessToggle.render(g);

        g.drawString("Population", 70, 103);
        g.drawString(""+Settings.populationSize, 100, 130);
        for(int i = 0; i < populationModifiers.length; i++){
            populationModifiers[i].render(g);
        }

        save.render(g);
    }
}
