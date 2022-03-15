public class Path{
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
    // TODO figure out a way to keep the angle of movement the same, but reduce the length of movement
    while(calcVel() > velLimit){
      if(xVel < 0 && yVel < 0){
        xVel += 1;
        yVel += 1;
      }
      else if(xVel > 0 && xVel > 0){
      xVel -= 1;
      yVel -= 1;
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