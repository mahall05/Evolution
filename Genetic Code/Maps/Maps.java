package Maps;

import Core.Constants;

public class Maps {
    private static Obstacle[] map1Obstacles = {
        new Obstacle(Constants.WIDTH/2 - 50/2 - 40 -300, 0, 20, 80),                          //   Walls around the goal
        new Obstacle(Constants.WIDTH/2 - 50/2 - 40 -300, 80, 40, 20),  //   Walls around the goal
        new Obstacle(Constants.WIDTH/2 + 50/2 + 20 -300, 0, 20, 80),                          //   Walls around the goal
        new Obstacle(Constants.WIDTH/2 + 50/2 -300, 80, 40, 20),       //   Walls around the goal
        new Obstacle(0, 200, 200, 20),
        new Obstacle(150, 300, Constants.WIDTH - 150, 20),
        new Obstacle(0, 400, 300, 20),
        //new Obstacle(Constants.WIDTH/2 - 100, Constants.HEIGHT - 140, 20, 100),
        //new Obstacle(Constants.WIDTH/2 - 100, Constants.HEIGHT - 140, 200, 20),
        new Obstacle(Constants.WIDTH/2 - 100, Constants.HEIGHT - 200, 200, 20),
    };
    public static Map originalMap = new Map(map1Obstacles, new Goal((Constants.WIDTH/2)-(50/2)-300, 10, 50, 50), "Original Map");
}
