package Maps;

import Core.Constants;

public class Maps {
    private static Obstacle[] testingObstacles = {new Obstacle(264, 536, 200, 20), 
                                                new Obstacle(550, 426, 100, 20), 
                                                new Obstacle(730, 320, 100, 20), 
                                                new Obstacle(Constants.WIDTH-450, Constants.HEIGHT/2+300, 450, 40, true)};

    private static MovingObstacle[] movingObstacles = {new MovingObstacle(0, 0, 5, Constants.HEIGHT, true), // Death line
                                                        };
    private static Goal goal = new Goal(Constants.WIDTH - 100, 200, 50, 50);
    public static Map testing = new Map(goal, testingObstacles, movingObstacles, new Ground(0, Constants.HEIGHT/2+300, Constants.WIDTH-450, 40), "Testing");
}
