package Menus;
import java.awt.*;

import Core.Constants;

public class StartMenu {
    public Button startButton;
    public Button loadButton;
    public Button settingsButton;

    public StartMenu(){
        /* Start Button */
        startButton = new Button(Constants.WIDTH/2-100, Constants.HEIGHT/2-50, 200, 75);
        startButton.setButtonColor(Color.white);
        startButton.setFontColor(Color.red);
        startButton.setFontSize(50);
        startButton.setLabel("Start");
        startButton.offsetLabel(45, -10);

        /* Load Button */
        loadButton = new Button(startButton.x, startButton.y+startButton.height+20, startButton.width, startButton.height);
        loadButton.setButtonColor(Color.white);
        loadButton.setFontColor(Color.red);
        loadButton.setFontSize(50);
        loadButton.setLabel("Load");
        loadButton.offsetLabel(40, -10);

        /* Settings Button */
        settingsButton = new Button(loadButton.x, loadButton.y+loadButton.height+20, loadButton.width, loadButton.height);
        settingsButton.setButtonColor(Color.white);
        settingsButton.setFontColor(Color.red);
        settingsButton.setFontSize(50);
        settingsButton.setLabel("Settings");
        settingsButton.offsetLabel(7, -10);
    }

    public void tick(){
        //TODO check if button is clicked
    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 100));
        g.setColor(Color.white);
        g.drawString("EVOLUTION", (Constants.WIDTH/2)-300, (Constants.HEIGHT/2)-150);

        startButton.render(g);
        loadButton.render(g);
        settingsButton.render(g);

        /* Start Button */
        /*
        g.setColor(Color.white);
        g.fillRect(Constants.WIDTH/2-100, Constants.HEIGHT/2-50, 200, 75);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 35));
        g.setColor(Color.red);
        g.drawString("Settings", Constants.WIDTH/2-100+30, Constants.HEIGHT/2-50+50);
        */

        /* Load Button */

    }

    public Button[] getButtons(){
        Button[] buttons = {startButton, loadButton, settingsButton};
        return buttons;
    }
}
