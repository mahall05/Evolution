import java.awt.Graphics;
import java.awt.Color;

public class Body {
    private int angle = 0;
    int num = 1;
    
    public void tick(){
        angle += 45;
        System.out.println(num);
        num++;
    }
    public void render(Graphics g){
        int xPos = 300;
        int yPos = 200;
        int size = 8;

        //g.setColor(Color.black);
        //g.fillOval(xPos, yPos, size, size);

        g.setColor(Color.gray);
        g.fillArc(xPos+(size/2), yPos+(size/2), 20, 20, angle-45, angle+45);
        //g.fillArc(200, 200, 20, 20, 0, 90);
    }
}
