package Menus;

import Core.Constants;
import java.awt.*;

public class PauseMenu {
    public Button[] buttons = new Button[3];
    private String[] buttonLabels = {"Settings", "Save", "Quit"};
    private int[][] offsets = {{30, -15}, {54, -15}, {60, -15}};

    public PauseMenu(){

        for(int i = 0; i < buttons.length; i++){
            if(i == 0){
                buttons[i] = new Button(Constants.WIDTH/2-100, Constants.HEIGHT/2-50, 200, 75); // Create the first buttons
                buttons[i].bold = false;
            }
            else{
                buttons[i] = buttons[i-1].copy(buttons[i-1].x, buttons[i-1].y+buttons[i-1].height+30); // Other buttons are copies of the first in different positions
            }
            buttons[i].setLabel(buttonLabels[i], Color.red, 35, true);
            buttons[i].offsetLabel(offsets[i][0], offsets[i][1]);
        }
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 60));
        g.setColor(Color.white);
        g.drawString("Paused", (Constants.WIDTH/2)-100, (Constants.HEIGHT/2)-110);

        for(int i = 0; i < buttons.length; i++){
            buttons[i].render(g);
        }
    }
}
