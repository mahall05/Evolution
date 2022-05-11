package Bodies;
import java.awt.*;

import Core.Constants;
import Maps.Map;
import Maps.Obstacle;

public class Body {
  private int width = 15, height = 50, brainSize;

  public Movement move;
  public Brain brain;

  public boolean isBest = false;
  public boolean dead = false;
  public boolean reachedGoal = false;
  public boolean ableToReachGoal = true;

  private double fitness = 0, bestDist = 5000000;

  private long start, finish, elapsedTime;

  public Body(int brainSize){
    this.brainSize = brainSize;
    move = new Movement();
    brain = new Brain(brainSize);

    move = new Movement(10, Constants.HEIGHT-100);
    start = System.currentTimeMillis();
  }

  public void tick(Map map){
    // ALWAYS UPDATE THE COLLISIONS FIRST
    move.touchingGround = checkTouching(map.ground);
    move.touchingWall = checkTouching(map.borders);

    if(!dead && !reachedGoal){
      move();
      if(detectDist(map.goal) < bestDist){
        bestDist = detectDist(map.goal);
        brain.bestStep = brain.step;
      }
    }
    
    move.tick();
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
      g.fillOval(move.x, move.y, width+3, height+3);
    }else{
      g.setColor(Color.black);
      g.fillOval(move.x, move.y, width, height);
    }
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
}
