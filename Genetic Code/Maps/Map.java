package Maps;
import java.awt.*;
import java.io.Serializable;

public class Map implements Serializable{
    public Obstacle[] obstacles;
    public Goal goal;
    private String name;

    public Map(Obstacle[] obstacles, Goal goal, String name){
        this.obstacles = obstacles;
        this.goal = goal;
        this.name = name;
    }

    public void tick(){
        
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(goal.x, goal.y, goal.width, goal.height);

        g.setColor(Color.gray);
        for(int i = 0; i < obstacles.length; i++){
            g.fillRect(obstacles[i].x, obstacles[i].y, obstacles[i].width, obstacles[i].height);
        }
    }

    public String getName(){
        return name;
    }
}