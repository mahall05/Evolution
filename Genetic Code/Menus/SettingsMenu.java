package Menus;
import java.awt.*;

import Core.Constants;
import Core.Settings;

public class SettingsMenu {
    public Button back;
    public Button fitnessToggle;
    public Button populationPlus;
    public Button populationMinus;

    public SettingsMenu(){
        /* Back Button */
        back = new Button(40, Constants.HEIGHT-100, 75, 25);
        back.setButtonColor(Color.white);
        back.setFontColor(Color.red);
        back.setFontSize(20);
        back.setLabel("Back");
        back.setBold(true);
        back.offsetLabel(11, -45);

        /* Start Button */
        fitnessToggle = new Button(80, 40, 75, 30);
        fitnessToggle.setButtonColor(Color.white);
        fitnessToggle.setFontColor(Color.red);
        fitnessToggle.setFontSize(20);
        fitnessToggle.setLabel("Toggle");
        fitnessToggle.setBold(true);
        fitnessToggle.offsetLabel(3, -45);

        /* Population Plus */
        populationPlus = new Button(155, 113, 30, 20);
        populationPlus.setButtonColor(Color.white);
        populationPlus.setFontColor(Color.red);
        populationPlus.setFontSize(20);
        populationPlus.setLabel("+");
        populationPlus.setBold(true);
        populationPlus.offsetLabel(5, -47);

        /* Population Minus */
        populationMinus = new Button(58, 113, 30, 20);
        populationMinus.setButtonColor(Color.white);
        populationMinus.setFontColor(Color.red);
        populationMinus.setFontSize(20);
        populationMinus.setLabel("+");
        populationMinus.setBold(true);
        populationMinus.offsetLabel(5, -47);
    }

    public void tick(){
        //TODO check if button is clicked
    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        /* Back Button */
        back.render(g);

        /* Toggle Fitness Calc Mode */
        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 16));
        g.setColor(Color.white);
        g.drawString("Fitness Mode: " + (Settings.calcBestStep ? "Closest Ever" : "Closest Death"), 20, 30);
        fitnessToggle.render(g);

        /* Population Size */
        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 16));
        g.setColor(Color.white);
        g.drawString("Population Size", 60, 100);
        populationPlus.render(g);
        g.drawString(""+Settings.populationSize, 100, 130);
        populationMinus.render(g);
    }
}
