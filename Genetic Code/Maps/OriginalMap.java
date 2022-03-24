package Maps;
import java.awt.Color;
import java.awt.Graphics;

import Core.Constants;

public class OriginalMap extends Map{
    public OriginalMap(){
        goalOffset = -300;
        goalXD = 50;
        goalYD = 50;
        goal = new Goal((Constants.WIDTH/2)-(goalXD/2)+goalOffset, 10, goalXD, goalYD);
    }

    public Obstacle[] obstacles = {
        new Obstacle(Constants.WIDTH/2 - goal.width/2 - 40 + goalOffset, 0, 20, 80),                          //   Walls around the goal
        new Obstacle(Constants.WIDTH/2 - goal.width/2 - 40 + goalOffset, goal.height + goal.y + 20, 40, 20),  //   Walls around the goal
        new Obstacle(Constants.WIDTH/2 + goal.width/2 + 20 + goalOffset, 0, 20, 80),                          //   Walls around the goal
        new Obstacle(Constants.WIDTH/2 + goal.width/2 + goalOffset, goal.height + goal.y + 20, 40, 20),       //   Walls around the goal
        new Obstacle(0, 200, 200, 20),
        new Obstacle(150, 300, Constants.WIDTH - 150, 20),
        new Obstacle(0, 400, 300, 20),
        //new Obstacle(Constants.WIDTH/2 - 100, Constants.HEIGHT - 140, 20, 100),
        //new Obstacle(Constants.WIDTH/2 - 100, Constants.HEIGHT - 140, 200, 20),
        new Obstacle(Constants.WIDTH/2 - 100, Constants.HEIGHT - 200, 200, 20),
    };

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
