package Menus;

import Core.Constants;
import java.awt.*;

public class StartMenu{
    public Button[] buttons = new Button[3];
    private String[] buttonLabels = {"Start", "Load", "Settings"};
    private int[][] offsets = {{40, -10}, {38, -10}, {0, -10}};

    public StartMenu(){

        for(int i = 0; i < buttons.length; i++){
            if(i == 0){
                buttons[i] = new Button(Constants.WIDTH/2-100, Constants.HEIGHT/2-50, 200, 75); // Create the first buttons
                buttons[i].bold = false;
            }
            else{
                buttons[i] = buttons[i-1].copy(buttons[i-1].x, buttons[i-1].y+buttons[i-1].height+30); // Other buttons are copies of the first in different positions
            }
            buttons[i].setLabel(buttonLabels[i], Color.red, 50, true);
            buttons[i].offsetLabel(offsets[i][0], offsets[i][1]);
        }
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 80));
        g.setColor(Color.red);
        g.drawString("Evolution", (Constants.WIDTH/2)-160, (Constants.HEIGHT/2)-150);

        for(int i = 0; i < buttons.length; i++){
            buttons[i].render(g);
        }
    }
}
