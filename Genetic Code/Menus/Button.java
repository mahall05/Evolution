package Menus;
import java.awt.*;

public class Button{
    public String label;
    public int x;
    public int y;
    public int width;
    public int height;
    public int fontSize;
    public Color color;

    public Button(String label, int x, int y, int width, int height, int fontSize, Color color){
        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Button(String label, int x, int y, int width, int height, int fontSize){
        this(label, x, y, width, height, fontSize, Color.white);
    }

    public void render(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);

        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, fontSize));
        g.setColor(Color.red);
        g.drawString(label, x+3, y+5 /*y+height/8*/);
    }
}
