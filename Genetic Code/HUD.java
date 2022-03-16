import java.awt.*;

public class HUD {

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.drawString("Generation: " + Population.gen, 10, 20);
    }
}
