package Menus;

import Core.Constants;
import java.awt.*;

public class SettingsMenu {
    public Button[] buttons = new Button[3];
    private String[] buttonLabels = {"Back", "Toggle", "+", "-"};
    private int[][] offsets = {{30, -15}, {54, -15}, {27, -35}};

    public SettingsMenu(){

        for(int i = 0; i < buttons.length; i++){
            if(i == 0){
                buttons[i] = new Button(Constants.WIDTH/2-100, Constants.HEIGHT/2-50, 200, 75); // Create the first buttons
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
        
    }
}
