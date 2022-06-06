package Maps;
import java.io.Serializable;

import Core.Hitbox;
import Core.Box;


public class Obstacle implements Serializable{
    public int x = 0;
    public int y = 0;
    public int width = 0;
    public int height = 0;
    public boolean deadly;
    public Hitbox hitbox;

    private final int BOX_SIZE = 4;
    private final int SIDE_SIZE = 100;
    
    public Obstacle(int x, int y, int width, int height, boolean deadly, Hitbox hitbox){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.deadly = deadly;
        this.hitbox = hitbox;

        hitbox.bottom = new Box(this.x+7, this.y+(height-(height/BOX_SIZE)), this.width-7, this.height/BOX_SIZE);
        hitbox.top = new Box(this.x+7, this.y, this.width-7, this.height/BOX_SIZE);
        hitbox.right = new Box(this.x+(width-(width/SIDE_SIZE)), this.y, this.width/SIDE_SIZE, this.height);
        hitbox.left = new Box(this.x, this.y, this.width/SIDE_SIZE, this.height);
    }

    public Obstacle(int x, int y, int width, int height, boolean deadly){
        this(x, y, width, height, deadly, new Hitbox(x, y, width, height));
    }

    public Obstacle(int x, int y, int width, int height){
        this(x, y, width, height, false);
    }
}
