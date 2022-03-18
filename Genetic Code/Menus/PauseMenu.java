package Menus;
import java.awt.*;

public class PauseMenu {
    private int width;
    private int height;

    public PauseMenu(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void tick(){
        //TODO check if button is clicked
    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 40));
        g.setColor(Color.white);
        g.drawString("Paused", (width/2)-60, (height/2)-100);

        /* Button */
        g.setColor(Color.white);
        g.fillRect(width/2-100, height/2-50, 200, 75);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 35));
        g.setColor(Color.red);
        g.drawString("Settings", width/2-100+30, height/2-50+50);
    }
}
