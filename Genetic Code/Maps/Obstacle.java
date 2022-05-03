package Maps;
import java.io.Serializable;


public class Obstacle implements Serializable{
    public int x = 0;
    public int y = 0;
    public int width = 0;
    public int height = 0;
    
    public Obstacle(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
