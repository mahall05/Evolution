package Dots;
import java.awt.*;

import Core.Constants;
import More.Obstacles;

public class Body {
    int width = 7;
    int height = 7;
  public Brain brain;
  private Path path;

  boolean dead = false;
  boolean reachedGoal = false;
  boolean isBest = false;

  double fitness = 0;

  long start;
  long finish;
  long elapsedTime;

  int brainSize;

  double bestDist = 5000000;

  public Body(int brainSize){
    this.brainSize = brainSize;
    brain = new Brain(brainSize);

    path = new Path(Constants.WIDTH/2+20, Constants.HEIGHT-75);
    path.setVelLimit(7);
    start  = System.currentTimeMillis();
  }

    public void tick(Obstacles obs){
      if(!dead && !reachedGoal){
        move();
        if(detectDist(obs.goal) < bestDist){
          bestDist = detectDist(obs.goal);
          brain.bestStep = brain.step;
        }

        if(path.x < 2 || path.y < 2 || path.x > Constants.WIDTH-2 || path.y > Constants.HEIGHT-45){ // Check for screen boundaries
          dead = true;
        }
        else if(checkCollision(obs.goal)){
          reachedGoal = true;
          finish = System.currentTimeMillis();
          elapsedTime = finish - start;
        }
        else{
          for (int i = 0; i < obs.obstacles.length; i++){
            if(checkCollision(obs.obstacles[i])){
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
    if(isBest){
      g.setColor(Color.red);
      g.fillOval(path.x, path.y, width+3, height+3);
    }else{
      g.setColor(Color.black);
      g.fillOval(path.x, path.y, width, height);
    }
  }

  public double detectDist(Obstacles.Goal g){
    return Math.sqrt(((g.x - path.x) * (g.x - path.x)) + ((g.y - path.y) * (g.y - path.y)));
  }

  public void calculateFitness(Obstacles.Goal g){
    if(reachedGoal){
      fitness = 1.0/16.0 + 10000.0/(double)(brain.step * brain.step); // Bodies that make it to the goal have the highest fitness score
    }else{
      double distanceToGoal = detectDist(g);
      fitness = 1.0/(distanceToGoal * distanceToGoal);
    }
  }

  public Body clone(){
    Body clone = new Body(brainSize);
    clone.brain = brain.clone();
    return clone;
  }

  public boolean checkCollision(Obstacles.Obstacle o){
    if(path.x+width > o.x && path.y+height > o.y && path.x < o.width+o.x && path.y < o.height+o.y){
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
