import java.awt.*;

public class Body {
    int width = 7;
    int height = 7;
  private Brain brain;
  private Path path;

  boolean dead = false;
  boolean reachedGoal = false;
  boolean isBest = false;

  public Body(){
    brain = new Brain(500);

    path = new Path(200, 200);
    path.setVelLimit(7);
  }

    public void tick(){
         move();
        System.out.print(path.x + ", ");
        System.out.println(path.y);
    }

  public void move(){
    if(brain.paths.length > brain.step){
      path.accelerate(brain.paths[brain.step]);
      brain.step++;
    }
    else{
      //dead = true;
    }
    path.moveAtCurrentVel();
  }
  
    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillOval(path.x, path.y, width, height);
    }
}
