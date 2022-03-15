import java.awt.*;

public class HUD {
    private static int generation = 0;

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.drawString("Generation: " + generation, 10, 20);
    }

    public int getGeneration(){
        return generation;
    }

    public void nextGeneration(){
        generation++;
    }
}
