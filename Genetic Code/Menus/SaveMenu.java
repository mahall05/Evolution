package Menus;

import Core.Constants;
import Saves.SaveInfo;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SaveMenu {
    public SaveInfo[] info = new SaveInfo[5];
    public Button[] slots = new Button[5];
    public Button back;

    public SaveMenu(){
        slots[0] = new Button(Constants.WIDTH/2-230, Constants.HEIGHT/2-175, 200, 75);
        slots[0].setButtonColor(Color.white);
        slots[0].setFontColor(Color.red);
        slots[0].setFontSize(35);
        slots[0].setLabel("Slot 1");
        slots[0].setBold(true);
        slots[0].offsetLabel(50, -15);

        for(int i = 1; i < slots.length; i++){
            slots[i] = slots[i-1].copy(slots[i-1].x, slots[i-1].y+slots[i-1].height+20);
            slots[i].setLabel("Slot " + (i+1));
        }

        for(int i = 0; i < info.length; i++){
            info[i] = load(i+1);
        }

        back = new Button(30, Constants.HEIGHT-100, 75, 30);
        back.setButtonColor(Color.white);
        back.setFontColor(Color.red);
        back.setFontSize(20);
        back.setLabel("Back");
        back.setBold(true);
        back.offsetLabel(10, -42);
    }

    public void tick(){
        //TODO check if button is clicked
    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        for(int i = 0; i < slots.length; i++){
            slots[i].render(g);
        }

        back.render(g);

        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 60));
        g.setColor(Color.white);
        g.drawString("Save", (Constants.WIDTH/2)-63, (Constants.HEIGHT/2)-250);

        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 15));
        int yPosition = Constants.HEIGHT/2-155;
        int xPosition = Constants.WIDTH/2+0;

        for(int i = 0; i < info.length; i++){
            g.drawString("Date: " + info[i].date, xPosition, yPosition);
            yPosition += 15;
            g.drawString("Generation: " + info[i].generation, xPosition, yPosition);
            yPosition += 15;
            g.drawString((info[i].ableToReachGoal ? "Reaches goal in: " + info[i].steps : "Not able to reach goal"), xPosition, yPosition);
            yPosition += 66;
        }
    }

    private SaveInfo load(int slot){
        try{
            FileInputStream fileInStr = new FileInputStream("Saves/info"+slot+".ser");
            ObjectInputStream objInStr = new ObjectInputStream(fileInStr);
            SaveInfo info = (SaveInfo) objInStr.readObject();
            objInStr.close();
            fileInStr.close();
            return info;
        }catch(IOException  exp){
            System.out.println("Error IOException");
            exp.printStackTrace();
            return null;
        }catch(ClassNotFoundException cexp){
            System.out.println("Brian class not found");
            cexp.printStackTrace();
            return null;
        }
    }
}
