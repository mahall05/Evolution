package Menus;

import java.awt.*;

import Core.Constants;

public class SaveMenu {
    public Button[] buttons = new Button[5];

    public SaveMenu(){
        for(int i = 0; i < buttons.length; i++){
            if(i == 0){
                buttons[i] = new Button(Constants.WIDTH/2-100, Constants.HEIGHT/2-160, 200, 75); // Create the first buttons
                buttons[i].bold = true;
            }else{
                buttons[i] = buttons[i-1].copy(buttons[i-1].x, buttons[i-1].y+buttons[i-1].height+20); // Other buttons are copies of the first in different positions
            }
            buttons[i].setLabel("Slot "+(i+1), Color.red, 50, true);
            buttons[i].offsetLabel(0, 0);
        }
    }

    //TODO save and load menus
    public void tick(){

    }
    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 60));
        g.setColor(Color.white);
        g.drawString("Save", (Constants.WIDTH/2)-70, (Constants.HEIGHT/2)-250);

        for(int i = 0; i < buttons.length; i++){
            buttons[i].render(g);
        }
    }
}
