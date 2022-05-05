package Bodies;
import java.awt.*;

import Maps.Map;
import Maps.Obstacle;

public class Body {
    public boolean isBest = false;

    private int width = 15, height = 50;
    public Movement move = new Movement();

    public void tick(Map map){
      move.touchingGround = checkTouchingGround(map.ground);
      move.tick();
    }

    public void render(Graphics g){
      if(isBest){
        g.setColor(Color.green);
        g.fillOval(move.x, move.y, width+3, height+3);
      }else{
        g.setColor(Color.black);
        g.fillOval(move.x, move.y, width, height);
      }
    }

    public boolean checkTouchingGround(Obstacle o){
      if(move.x+width > o.x && move.y+height > o.y && move.x < o.width+o.x && move.y < o.height+o.y){
        return true;
      }else{
        return false;
      }
    }
      
}
