package Bodies;

public class Movement {
    public int x, y;

    public double xVel, yVel;

    private static double gravityMultiplier = 0.1;
    public boolean touchingGround = true;

    public Movement(){
        x = 0;
        y = 0;
        xVel = 0;
        yVel = 0;
    }

    public Movement(int x, int y){
        this.x = x;
        this.y = y;
        xVel = 0;
        yVel = 0;
    }

    public void jump(){

    }

    public void tick(){
        if(!touchingGround){
            accelY(-gravityMultiplier);
        }
    }

    public void accelY(double acceleration){

    }
}
