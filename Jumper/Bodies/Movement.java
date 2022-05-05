package Bodies;

public class Movement {
    public int x, y;

    public double xVel, yVel, rightVel, leftVel;

    private static double gravityMultiplier = -0.1;
    public boolean touchingGround = false;
    public int touchingWall = 999;

    private int velLimit = 2;
    private final double JUMP_VELOCITY = -100;
    private final double MOVE_SPEED = 1;

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
        if(touchingGround){
            yVel += JUMP_VELOCITY;
        }
    }

    public void tick(){
        switch(touchingWall){
            case(0):
                yVel = 0;
                break;
            case(1):
                leftVel = 0;
                break;
            case(2):
                rightVel = 0;
                break;
        }

        xVel = rightVel - leftVel;
        x += xVel;
        y += yVel;

        if(!touchingGround){
            yVel -= gravityMultiplier;
        }else{
            yVel = 0;
        }
    }

    public void moveRight(){
        if(rightVel < velLimit){
            rightVel += MOVE_SPEED;
        }
    }

    public void moveLeft(){
        if(leftVel < velLimit){
            leftVel += MOVE_SPEED;
        }
    }

    public void stopRight(){
        rightVel = 0;
    }

    public void stopLeft(){
        leftVel = 0;
    }

    public void setVelLimit(int limit){
        this.velLimit = limit;
    }
}
