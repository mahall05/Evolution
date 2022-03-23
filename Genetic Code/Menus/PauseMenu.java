package Menus;
import java.awt.*;

import Core.Constants;

public class PauseMenu {
    public Button settings;
    public Button save;
    public Button quit;

    public PauseMenu(){
        settings = new Button(Constants.WIDTH/2-100, Constants.HEIGHT/2-50, 200, 75);
        settings.setButtonColor(Color.white);
        settings.setFontColor(Color.red);
        settings.setFontSize(35);
        settings.setLabel("Settings");
        settings.setBold(true);
        settings.offsetLabel(30, -15);

        save = new Button(settings.x, settings.y+settings.height+30, 200, 75);
        save.setButtonColor(Color.white);
        save.setFontColor(Color.red);
        save.setFontSize(35);
        save.setLabel("Save");
        save.setBold(true);
        save.offsetLabel(54, -15);

        quit = new Button(settings.x+40, save.y+save.height+30, 120, 40);
        quit.setButtonColor(Color.white);
        quit.setFontColor(Color.red);
        quit.setFontSize(30);
        quit.setLabel("Quit");
        quit.setBold(true);
        quit.offsetLabel(27, -35);
    }

    public void tick(){
        //TODO check if button is clicked
    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 60));
        g.setColor(Color.white);
        g.drawString("Paused", (Constants.WIDTH/2)-100, (Constants.HEIGHT/2)-110);

        settings.render(g);
        save.render(g);
        quit.render(g);
    }
}
