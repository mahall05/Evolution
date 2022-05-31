package Menus;

import Core.Constants;
import java.awt.*;

public class LineSelectMenu{
    public Button[] buttons = new Button[2];
    private String[] buttonLabels = {"Yes", "No"};
    private int[][] offsets = {{40, -10}, {38, -10}};

    public Button[] speedMods = new Button[2];
    private String[] modLabels = {"-", "+"};
    private int[][] modOffsets = {{10, -25}, {0, -25}};

    public int lineSpeed = 5;

    public LineSelectMenu(){

        for(int i = 0; i < buttons.length; i++){
            if(i == 0){
                buttons[i] = new Button(Constants.WIDTH/2-100, Constants.HEIGHT/2+50, 200, 75); // Create the first buttons
                buttons[i].bold = false;

                speedMods[i] = new Button(Constants.WIDTH/2 - 125, Constants.HEIGHT/2-40, 50, 50);
                speedMods[i].bold = false;
            }
            else{
                buttons[i] = buttons[i-1].copy(buttons[i-1].x, buttons[i-1].y+buttons[i-1].height+30); // Other buttons are copies of the first in different positions
                speedMods[i] = speedMods[i-1].copy(Constants.WIDTH/2 + 75, speedMods[i-1].y);
            }
            buttons[i].setLabel(buttonLabels[i], Color.red, 50, true);
            buttons[i].offsetLabel(offsets[i][0], offsets[i][1]);

            speedMods[i].setLabel(modLabels[i], Color.red, 50, true);
            speedMods[i].offsetLabel(modOffsets[i][0], modOffsets[i][1]);
        }
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 80));
        g.setColor(Color.red);
        g.drawString("Line?", (Constants.WIDTH/2)-100, (Constants.HEIGHT/2)-150);

        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 50));
        g.drawString(""+lineSpeed, Constants.WIDTH/2-15, Constants.HEIGHT/2);

        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString("Speed:", Constants.WIDTH/2-30, Constants.HEIGHT/2-60);

        for(int i = 0; i < buttons.length; i++){
            buttons[i].render(g);
            speedMods[i].render(g);
        }
    }

    public void speedChange(int speed){
        lineSpeed += speed;
    }
}
