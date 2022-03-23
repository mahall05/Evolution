package Menus;
import java.awt.*;

public class Button{
    public int x;
    public int y;
    public int width;
    public int height;

    public int xOffset = 0;
    public int yOffset = 0;

    public Color buttonColor;
    public Color fontColor;

    public int fontSize;

    public String label;

    public boolean bold = false;

    private int mouseOffsetX = -7;
    private int mouseOffsetY = -30;

    public Button(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void tick(){
        // TODO check if button is clicked and add actions
    }

    public void render(Graphics g){
        g.setColor(buttonColor);
        g.fillRect(x, y, width, height);

        g.setFont(new Font(g.getFont().getFontName(), (bold ? Font.BOLD : Font.PLAIN), fontSize));
        g.setColor(fontColor);
        g.drawString(label, x+3+xOffset, y+65+yOffset);
    }

    public boolean checkWithinButton(Point point){
        if(point.getX()+mouseOffsetX >= x && point.getX()+mouseOffsetX <= x+width-1 && point.getY()+mouseOffsetY >= y && point.getY()+mouseOffsetY <= y+height-1){
            return true;
        }else{
            return false;
        }
    }

    public void offsetLabel(int x, int y){
        this.xOffset = x;
        this.yOffset = y;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public void setFontSize(int size){
        this.fontSize = size;
    }

    public void setButtonColor(Color color){
        this.buttonColor = color;
    }
    public void setFontColor(Color color){
        this.fontColor = color;
    }
    public void setBold(boolean b){
        bold = b;
    }
    public Button copy(int x, int y){
        Button b = new Button(x, y, this.width, this.height);
        b.setButtonColor(this.buttonColor);
        b.setFontColor(this.fontColor);
        b.setFontSize(this.fontSize);
        b.setLabel(this.label);
        b.setBold(this.bold);
        b.offsetLabel(this.xOffset, this.yOffset);
        return b;
    }
}
