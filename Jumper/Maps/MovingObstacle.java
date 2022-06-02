package Maps;

import Core.Hitbox;

public class MovingObstacle extends Obstacle {
    int xVel, yVel;
    public boolean active = false;

    public MovingObstacle(int x, int y, int width, int height, int xVel, int yVel, boolean deadly, Hitbox hitbox){
        super(x, y, width, height, deadly, hitbox);
        this.xVel = xVel;
        this.yVel = yVel;
    }

    public MovingObstacle(int x, int y, int width, int height, boolean deadly, Hitbox hitbox){
        this(x, y, width, height, 0, 0, deadly, hitbox);
    }

    public MovingObstacle(int x, int y, int width, int height, boolean deadly){
        this(x, y, width, height, deadly, new Hitbox(x, y, width, height));
    }

    public MovingObstacle(int x, int y, int width, int height){
        this(x, y, width, height, false);
    }

    public void activate(){
        this.active = !this.active;
    }

    public void accelerate(int x, int y){
        this.xVel += x;
        this.yVel += y;
    }

    public void setSpeed(int x, int y){
        this.xVel = x;
        this.yVel = y;
    }

    public void tick(){
        this.x += xVel;
        this.y += yVel;

        hitbox.update(this.x, this.y);
    }
}
