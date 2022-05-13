package Maps;
import java.io.Serializable;
import Bodies.Hitbox;


public class Obstacle{
    public int x = 0;
    public int y = 0;
    public int width = 0;
    public int height = 0;
    public boolean deadly;
    public Hitbox hitbox;
    
    public Obstacle(int x, int y, int width, int height, boolean deadly, Hitbox hitbox){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.deadly = deadly;
        this.hitbox = hitbox;
    }

    public Obstacle(int x, int y, int width, int height, boolean deadly){
        this(x, y, width, height, deadly, new Hitbox(x, y, width, height));
    }

    public Obstacle(int x, int y, int width, int height){
        this(x, y, width, height, false);
    }
}
