import java.awt.*;

public class Body {
    int width = 7;
    int height = 7;
  public Brain brain;
  private Path path;

  boolean dead = false;
  boolean reachedGoal = false;
  boolean isBest = false;

  public Body(int brainSize){
    brain = new Brain(brainSize);

    path = new Path(Main.WIDTH/2+20, Main.HEIGHT-75);
    path.setVelLimit(7);
  }

    public void tick(Obstacles obs){
      if(!dead && !reachedGoal){
        move();
        if(path.x < 2 || path.y < 2 || path.x > Main.WIDTH-2 || path.y > Main.HEIGHT-45){ // Check for screen boundaries
          dead = true;
        }
        else if(checkCollision(obs.goal)){
          reachedGoal = true;
          System.out.println("Win");
        }
        else{
          for (int i = 0; i < obs.obstacles.length; i++){
            if(checkCollision(obs.obstacles[i])){
              System.out.println("Dead");
              dead = true;
            }
          }
        }
      }
    }

  public void move(){
    if(brain.paths.length > brain.step){
      path.accelerate(brain.paths[brain.step]);
      brain.step++;
    }
    else{
      dead = true;
    }
    path.moveAtCurrentVel();
  }
  
  public void render(Graphics g){
      g.setColor(Color.black);
      g.fillOval(path.x, path.y, width, height);
  }

  public boolean checkCollision(Obstacles.Obstacle o){
    if(path.x > o.x && path.y > o.y && path.x < o.width+o.x && path.y < o.height+o.y){
      return true;
    }
    else{
      return false;
    }
  }
  public boolean checkCollision(Obstacles.Goal o){
    if(path.x > o.x && path.y > o.y && path.x < o.width+o.x && path.y < o.height+o.y){
      return true;
    }
    else{
      return false;
    }
  }
}
