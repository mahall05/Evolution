package Maps;

import Core.Constants;
import java.awt.*;

public class DeathLine extends Obstacle{
    public DeathLine(){
        super(0, 0, 5, Constants.HEIGHT, true);
        this.active = false;
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}
