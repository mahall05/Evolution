package Maps;

import Core.Constants;

public class Maps {
    private static Obstacle[] testingObstacles = {new Obstacle(376, 536, 200, 20)};
    private static Goal goal = new Goal(Constants.WIDTH - 100, 100, 50, 50);
    public static Map testing = new Map(goal, testingObstacles, new Ground(0, Constants.HEIGHT/2+300, Constants.WIDTH, 40), "Testing");
}
