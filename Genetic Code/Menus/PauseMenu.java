package Menus;
import java.awt.*;

import Core.Constants;

public class PauseMenu {

    public void tick(){
        //TODO check if button is clicked
    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 40));
        g.setColor(Color.white);
        g.drawString("Paused", (Constants.WIDTH/2)-60, (Constants.HEIGHT/2)-100);

        /* Button */
        g.setColor(Color.white);
        g.fillRect(Constants.WIDTH/2-100, Constants.HEIGHT/2-50, 200, 75);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 35));
        g.setColor(Color.red);
        g.drawString("Settings", Constants.WIDTH/2-100+30, Constants.HEIGHT/2-50+50);
    }
}
