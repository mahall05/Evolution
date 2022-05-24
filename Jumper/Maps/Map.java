package Maps;

import java.awt.*;

import Core.Constants;

public class Map {
    public Obstacle[] obstacles;
    public Ground ground;
    public Border[] borders = new Border[3];
    public Goal goal;
    public String name;

    public Map(Goal goal, Obstacle[] obstacles, Ground ground, String name){
        this.goal = goal;
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

        for(int i = 0; i < obstacles.length; i++){
            if(obstacles[i].deadly){
                g.setColor(Color.ORANGE);
            }else{
                g.setColor(Color.GRAY);
            }
            g.fillRect(obstacles[i].x, obstacles[i].y, obstacles[i].width, obstacles[i].height);
        }

        g.setColor(Color.red);
        g.fillRect(goal.x, goal.y, goal.width, goal.height);
    }

    public void tick(){
        
    }
}
