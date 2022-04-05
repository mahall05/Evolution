package Maps;
import java.awt.*;

public class Map{
    public Obstacle[] obstacles;
    public Goal goal;

    public Map(Obstacle[] obstacles, Goal goal){
        this.obstacles = obstacles;
        this.goal = goal;
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
}