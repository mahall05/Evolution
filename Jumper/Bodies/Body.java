package Bodies;
import java.awt.*;

import Maps.Map;
import Maps.Obstacle;

public class Body {
    public boolean isBest = false;

    private int width = 15, height = 50;
    public Movement move = new Movement();

    public void tick(Map map){
      move.touchingGround = checkTouching(map.ground);
      move.touchingWall = checkTouching(map.borders);
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

    public boolean checkTouching(Obstacle o){
      if(move.x+width > o.x && move.y+height > o.y && move.x < o.width+o.x && move.y < o.height+o.y){
        return true;
      }else{
        return false;
      }
    }

    public int checkTouching(Obstacle[] o){
      //boolean touching = false;
      int touching = 999;
      for(int i = 0; i < o.length; i++){
        if(move.x+width > o[i].x && move.y+height > o[i].y && move.x < o[i].width+o[i].x && move.y < o[i].height+o[i].y){
          //touching =  true;
          touching = i;
        }
      }

      return touching;
    }  
}
