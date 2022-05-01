package Menus;

import Core.Constants;
import Core.Files.FileHandler;
import Core.Files.SaveInfo;

import java.awt.*;

public class FileMenu {
    public Button[] buttons = new Button[5];
    public SaveInfo[] info = new SaveInfo[5];
    protected String title;

    public FileMenu(){
        for(int i = 0; i < buttons.length; i++){
            if(i == 0){
                buttons[i] = new Button(Constants.WIDTH/2-200, Constants.HEIGHT/2-160, 200, 75); // Create the first buttons
                buttons[i].bold = true;
            }else{
                buttons[i] = buttons[i-1].copy(buttons[i-1].x, buttons[i-1].y+buttons[i-1].height+20); // Other buttons are copies of the first in different positions
            }
            buttons[i].setLabel("Slot "+(i+1), Color.red, 50, true);
            buttons[i].offsetLabel(0, 0);
        }

        // Perform load operation
        load();
        load();

        for(int i = 0; i < buttons.length; i++){
            System.out.println(info[i].ableToReachGoal);
        }
    }

    public Button[] getButtons(){
        return buttons;
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 60));
        g.setColor(Color.white);
        g.drawString(title, (Constants.WIDTH/2)-70, (Constants.HEIGHT/2)-250);

        for(int i = 0; i < buttons.length; i++){
            buttons[i].render(g);
        }

        printInfo(g);
    }

    public void printInfo(Graphics g){
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 15));
        g.setColor(Color.white);

        for(int i = 0; i < buttons.length; i++){
            g.drawString(info[i].date==null ? "Empty" : info[i].date.toString(), buttons[i].x+225, buttons[i].y+15);
            g.drawString(info[i].map==null ? "N/A" : info[i].map.getName(), buttons[i].x+225, buttons[i].y+30);
            g.drawString("Generation: " + info[i].generation, buttons[i].x+225, buttons[i].y+45);
            g.drawString(info[i].ableToReachGoal ? "Able to reach goal in " + info[i].steps + " steps": "Not able to reach goal", buttons[i].x+225, buttons[i].y+60);
        }
    }

    public void load(){
        for(int i = 0; i < buttons.length; i++){
            info[i] = FileHandler.loadInfo(i+1);
        }
    }
}
