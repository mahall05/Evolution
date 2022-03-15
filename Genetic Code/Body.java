import java.awt.*;

public class Body {
    int x = 0;
    int y = 0;
    int width = 7;
    int height = 7;
  private Brain brain;

  Path[] paths = new Path[400];

  public Body(){
    brain = new Brain();
    for(int i = 0; i < paths.length; i++){
      paths[i] = new Path(Main.randomDouble(-7, 7), Main.randomDouble(-7, 7));
    }
  }

    public void tick(){
        x += paths[brain.step].xAcc;
      y += paths[brain.step].yAcc;
      brain.step++;
    }
  
    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillOval(x, y, width, height);
    }
}
