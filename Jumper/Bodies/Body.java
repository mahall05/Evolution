package Bodies;
import java.awt.*;

public class Body {
    public boolean isBest = false;

    private int width = 15, height = 50;
    public Movement move = new Movement();

    public void render(Graphics g){
        if(isBest){
          g.setColor(Color.green);
          g.fillOval(move.x, move.y, width+3, height+3);
        }else{
          g.setColor(Color.black);
          g.fillOval(move.x, move.y, width, height);
        }
      }
}
