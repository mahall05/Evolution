package Dots;
import java.awt.*;

import Core.Constants;
import Core.Settings;
import Maps.*;

public class Body{
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

  boolean ableToReachGoal = false;

  double bestDist = 5000000;

  public Body(int brainSize){
    this.brainSize = brainSize;
    brain = new Brain(brainSize);

    path = new Path(Constants.WIDTH/2+20, Constants.HEIGHT-75);
    path.setVelLimit(7);
    start = System.currentTimeMillis();
  }

  public Body(AccelVector[] loadedPaths){
      brain = new Brain(loadedPaths);
      this.brainSize = brain.paths.length;

      path = new Path(Constants.WIDTH/2+20, Constants.HEIGHT-75);
      path.setVelLimit(7);
      start = System.currentTimeMillis();
  }

    public void tick(Map map){
        if(!dead && !reachedGoal){
          move();
          if(detectDist(map.goal) < bestDist){
              bestDist = detectDist(map.goal);
              brain.bestStep = brain.step;
          }

          if(path.x < 2 || path.y < 2 || path.x > Constants.WIDTH-2 || path.y > Constants.HEIGHT-45){ // Check for screen boundaries
              dead = true;
          }
          else if(checkCollision(map.goal)){
              reachedGoal = true;
              ableToReachGoal = true;
              finish = System.currentTimeMillis();
              elapsedTime = finish - start;
          }
          else{
              for (int i = 0; i < map.obstacles.length; i++){
              if(checkCollision(map.obstacles[i])){
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
      g.setColor(Color.green);
      g.fillOval(path.x, path.y, width+3, height+3);
    }else{
      g.setColor(Color.black);
      g.fillOval(path.x, path.y, width, height);
    }
  }

  public double detectDist(Goal g){
    return Math.sqrt(((g.x - path.x) * (g.x - path.x)) + ((g.y - path.y) * (g.y - path.y)));
  }

  public void calculateFitness(Goal g){
    if(reachedGoal){
      fitness = 1.0/16.0 + 10000.0/(double)(brain.step * brain.step); // Bodies that make it to the goal have the highest fitness score
    }else if(Settings.calcBestStep){
      fitness = 1.0/(bestDist * bestDist);
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

  public Body cloneFromBestStep(){
    Body clone = new Body(brainSize);
    brain.randomizeFromBest();
    clone.brain = brain.clone();
    return clone;
  }

  public boolean checkCollision(Obstacle o){
    if(path.x+width > o.x && path.y+height > o.y && path.x < o.width+o.x && path.y < o.height+o.y){
      return true;
    }
    else{
      return false;
    }
  }
  public boolean checkCollision(Goal o){
    if(path.x > o.x && path.y > o.y && path.x < o.width+o.x && path.y < o.height+o.y){
      return true;
    }
    else{
      return false;
    }
  }
}
