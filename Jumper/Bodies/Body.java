package Bodies;
import java.awt.*;

import Core.Constants;
import Core.Hitbox;
import Core.Settings;
import Maps.Goal;
import Maps.Map;
import Maps.Obstacle;

public class Body {
  private final int START_X = 10, START_Y = Constants.HEIGHT-300;
  private int width = 15, height = 50, brainSize;

  public Movement move;
  public Brain brain;
  public Hitbox hitbox;

  public boolean isBest = false;
  public boolean dead = false;
  public boolean reachedGoal = false;
  public boolean ableToReachGoal = true;

  public double fitness = 0, bestDist = 5000000;

  private long start, finish, elapsedTime;

  public Body(int brainSize){
    this.brainSize = brainSize;
    brain = new Brain(brainSize);

    hitbox = new Hitbox(START_X, START_Y, width, height);
    move = new Movement(START_X, START_Y, hitbox);
    start = System.currentTimeMillis();
  }

  public Body(Actions[] actions){
    brain = new Brain(actions);
    this.brainSize = actions.length;

    hitbox = new Hitbox(START_X, START_Y, width, height);
    move = new Movement(START_X, START_Y, hitbox);
    start = System.currentTimeMillis();
  }

  public void tick(Map map){
    // ALWAYS UPDATE THE COLLISIONS FIRST
    /*
    move.touchingGround = checkTouching(map.ground);
    move.touchingWall = checkTouching(map.borders);
    */
    move.tick();

    if(!dead && !reachedGoal){
      move();
      if(detectDist(map.goal) < bestDist){
        bestDist = detectDist(map.goal);
        brain.bestStep = brain.step;
      }

      // TODO check collision with obstacles
      if((checkTouching(map.goal))){
        reachedGoal = true;
        ableToReachGoal = true;
        finish = System.currentTimeMillis();
        elapsedTime = finish - start;
      }
    }
  }

  private void move(){
    if(brain.actions.length > brain.step){
      switch(brain.actions[brain.step]){
        case Jump:
          move.jump();
          break;
        case Right:
          move.moveRight();
          break;
        case Left:
          move.moveLeft();
          break;
      }
      brain.step++;
    }else{
      dead = true;
    }
  }

  public void render(Graphics g){
    if(isBest){
      g.setColor(Color.green);
      g.fillOval(move.x, move.y+3, width+3, height+3);
      g.setColor(Color.red);
      g.fillOval(move.x, move.y+3, width+3, width+3);
    }else{
      g.setColor(Color.black);
      g.fillOval(move.x, move.y, width, height);
      g.setColor(Color.red);
      g.fillOval(move.x, move.y, width, width);
    }

    //hitbox.render(g);

    /*
    // TODO testing
    g.setColor(Color.black);
    g.drawString("Right Speed" + move.rightVel, 10, 20);
    g.drawString("Left Speed" + move.leftVel, 10, 40);
    g.drawString("X Velocity: " + move.xVel, 10, 60);
    g.drawString("Y Velocity: " + move.yVel, 10, 80);
    */
  }

  public boolean checkTouching(Obstacle o){
    if(move.x+width > o.x && move.y+height > o.y && move.x < o.width+o.x && move.y < o.height+o.y){
      return true;
    }else{
      return false;
    }
  }

  public int checkTouching(Obstacle[] o){
    //boolean touching = false;
    int touching = 999;
    for(int i = 0; i < o.length; i++){
      if(move.x+width > o[i].x && move.y+height > o[i].y && move.x < o[i].width+o[i].x && move.y < o[i].height+o[i].y){
        //touching =  true;
        touching = i;
      }
    }

    return touching;
  }  

  public double detectDist(Obstacle o){
    return Math.sqrt(((o.x - move.x) * (o.x - move.x)) + ((o.y - move.y) * (o.y - move.y)));
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
    return clone();
  }

  public void calculateFitness(Goal g){
    if(reachedGoal){
      fitness = 1.0/16.0 + 10000.0/(double)(brain.step * brain.step);
    }else if(Settings.calcBestStep){
      fitness = 1.0/(bestDist * bestDist);
    }else{
      double distanceToGoal = detectDist(g);
      fitness = 1.0/(distanceToGoal * distanceToGoal);
    }
  }
}
