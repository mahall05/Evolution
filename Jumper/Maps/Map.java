package Maps;

import java.awt.*;

import Core.Constants;

public class Map {
    public Obstacle[] obstacles;
    public Ground ground;
    public Border[] borders = new Border[3];
    private String name;

    public Map(Obstacle[] obstacles, Ground ground, String name){
        this.obstacles = obstacles;
        this.ground = ground;
        this.name = name;

        borders[0] = new Border(0, 0, Constants.WIDTH, 1); // Top
        borders[1] = new Border(0, 0, 1, Constants.HEIGHT); // Left
        borders[2] = new Border(Constants.WIDTH-1, 0, 1, Constants.HEIGHT); // Right
    }

    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(ground.x, ground.y, ground.width, ground.height);
    }

    public void tick(){
        
    }
}
