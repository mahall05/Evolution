package Maps;

import Core.Constants;

public class Maps {
    private static Obstacle[] testingObstacles = {new Obstacle(264, 536, 200, 20), new Obstacle(600, 426, 100, 20), new Obstacle(780, 320, 100, 20)};
    private static Goal goal = new Goal(Constants.WIDTH - 100, 100, 50, 50);
    public static Map testing = new Map(goal, testingObstacles, new Ground(0, Constants.HEIGHT/2+300, Constants.WIDTH, 40), "Testing");
}
