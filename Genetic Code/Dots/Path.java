package Dots;

import java.io.Serializable;

public class Path implements Serializable{
  public double xVel;
  public double yVel;

  public int x;
  public int y;

  private int velLimit = 1000;

  public Path(){
    x = 0;
    y = 0;
    xVel = 0;
    yVel = 0;
  }

  public Path(int x, int y){
    this.x = x;
    this.y = y;
    xVel = 0;
    yVel = 0;
  }

  public void accelerate(AccelVector a){
    xVel += a.xAcc;
    yVel += a.yAcc;
    
    while(calcVel() > velLimit){
      if(xVel < 0 && yVel < 0){
        xVel += 0.1;
        yVel += 0.1;
      }
      else if(xVel > 0 && yVel > 0){
        xVel -= 0.1;
        yVel -= 0.1;
      }
      else if(xVel > 0 && yVel < 0){
        xVel -= 0.1;
        yVel += 0.1;
      }
      else if(xVel < 0 && yVel > 0){
        xVel += 0.1;
        yVel -= 0.1;
      }
    }
  }

  private double calcVel(){
    double vel = Math.sqrt(((xVel - 0) * (xVel - 0)) + ((yVel - 0) * (yVel - 0)));
    return vel;
  }

  public void moveAtCurrentVel(){
    x += xVel;
    y += yVel;
  }

  public void setVelLimit(int limit){
    this.velLimit = limit;
  }
}