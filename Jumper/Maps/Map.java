package Maps;

import java.awt.*;

public class Map {
    public Obstacle[] obstacles;
    public Ground ground;
    private String name;

    public Map(Obstacle[] obstacles, Ground ground, String name){
        this.obstacles = obstacles;
        this.ground = ground;
        this.name = name;
    }

    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(ground.x, ground.y, ground.width, ground.height);
    }

    public void tick(){
        
    }
}
