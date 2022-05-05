package Maps;

import Core.Constants;

public class Maps {
    private static Obstacle[] testingObstacles = {};
    public static Map testing = new Map(testingObstacles, new Ground(0, Constants.HEIGHT/2+300, Constants.WIDTH, 40), "Testing");
}
