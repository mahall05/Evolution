package Core;

import java.awt.*;

public class Box {
    public int x, y, width, height;

    public Box(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }

    public void update(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean checkWithin(Box box){
        if(x+width > box.x && y+height > box.y && x < box.width+box.x && y < box.height+box.y){
            return true;
          }else{
            return false;
          }
    }
}
