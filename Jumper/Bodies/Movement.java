package Bodies;

import Core.Hitbox;
import Maps.Maps;

public class Movement {
    public int x, y;

    public double xVel, yVel, rightVel, leftVel, velDecayRate = 1;

    private static double gravityMultiplier = -0.1;
    public boolean touchingGround = false;

    //public int touchingWall = 999;

    private int velLimit = 2;
    private final double JUMP_VELOCITY = -200;
    private final double MOVE_SPEED = 1;

    private double jumping = 0;

    private Hitbox hitbox;

    public Movement(int x, int y, Hitbox hitbox){
        this.x = x;
        this.y = y;
        xVel = 0;
        yVel = 0;
        this.hitbox = hitbox;
    }

    public Movement(Hitbox hitbox){
        this(0, 0, hitbox);
    }

    public void jump(){
        if(touchingGround){
            jumping = JUMP_VELOCITY;
        }
    }

    public int borderCollide(int num){
        switch(num){
            case(16):
                yVel = 0;
                break;
            case(32):
                leftVel = 0;
                break;
            case(64):
                rightVel = 0;
                break;
        }

        return num;
    }

    public void tick(){
        /*
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
        */

        int collision = hitbox.checkCollision();
        touchingGround = hitbox.bottom.checkWithin(Maps.testing.ground.hitbox.top);

        for(int i = 0; i < 16; i++){
            collision = collision - 16 == i ? collision - borderCollide(16) : (collision - 32 == i ? collision - borderCollide(32) : (collision - 64 == i ? collision - borderCollide(64) : collision));
        }

        switch(collision){
            case(1):
                yVel = 0;
                break;
            case(2):
                yVel = 0;
                break;
            case(4):
                rightVel = 0;
                break;
            case(8):
                leftVel = 0;
                break;
        }

        xVel = rightVel - leftVel;
        yVel += jumping;
        jumping = 0;
        x += xVel;
        y += yVel;

        hitbox.update(x, y);

        rightVel = (rightVel - velDecayRate < 0 ? 0 : rightVel - velDecayRate);
        leftVel = (leftVel - velDecayRate < 0 ? 0 : leftVel - velDecayRate);

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

    public void moveRight(double speed){
        if(rightVel < velLimit){
            rightVel += speed;
        }
    }

    public void moveLeft(){
        if(leftVel < velLimit){
            leftVel += MOVE_SPEED;
        }
    }

    public void moveLeft(int speed){
        if(leftVel < velLimit){
            leftVel += speed;
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
