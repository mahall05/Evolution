import java.awt.*;

public class Body {
    int x = 0;
    int y = 0;
    int width = 7;
    int height = 7;

    public void tick(){
        x += 4;
        y += 4;
    }
    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillOval(x, y, width, height);
    }
}
