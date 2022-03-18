package Menus;
import java.awt.*;

import Dots.Population;

public class HUD {
    private Population pop;

    public HUD(Population pop){
        this.pop = pop;
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.drawString("Generation: " + pop.gen, 10, 20);
    }
}
