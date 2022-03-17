import java.awt.Color;
import java.awt.Graphics;

public class Obstacles {
    public static int goalOffset = -300;
    public class Goal{
        public int x = 0;
        public int y = 0;
        public int width = 0;
        public int height = 0;
        public Goal(int x, int y, int width, int height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    public class Obstacle{
        public int x = 0;
        public int y = 0;
        public int width = 0;
        public int height = 0;

        public Point[] topSide;
        public Point[] rightSide;
        public Point[] bottomSide;
        public Point[] leftSide;
        public Obstacle(int x, int y, int width, int height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;

            topSide = new Point[width];
            bottomSide = new Point[width];
            rightSide = new Point[height];
            leftSide = new Point[height];

            for(int i = 0; i < width; i++){
                topSide[i] = new Point(x+i, y);
                bottomSide[i] = new Point(x+i, y+height-1);
            }
            for(int i = 0; i < height; i++){
                rightSide[i] = new Point(x+width-1, y+i);
                leftSide[i] = new Point(x, y+i);
            }
        }
    }

    public class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    int goalXD = 50;
    int goalYD = 50;
    public Goal goal = new Goal((Main.WIDTH/2)-(goalXD/2)+goalOffset, 10, goalXD, goalYD);

    public Obstacle[] obstacles = {
        new Obstacle(Main.WIDTH/2 - goal.width/2 - 40 + goalOffset, 0, 20, 80),                          //   Walls around the goal
        new Obstacle(Main.WIDTH/2 - goal.width/2 - 40 + goalOffset, goal.height + goal.y + 20, 40, 20),  //   Walls around the goal
        new Obstacle(Main.WIDTH/2 + goal.width/2 + 20 + goalOffset, 0, 20, 80),                          //   Walls around the goal
        new Obstacle(Main.WIDTH/2 + goal.width/2 + goalOffset, goal.height + goal.y + 20, 40, 20),       //   Walls around the goal
        new Obstacle(0, 200, 200, 20),
        new Obstacle(150, 300, Main.WIDTH - 150, 20),
        new Obstacle(0, 400, 300, 20),
        //new Obstacle(Main.WIDTH/2 - 100, Main.HEIGHT - 140, 20, 100),
        //new Obstacle(Main.WIDTH/2 - 100, Main.HEIGHT - 140, 200, 20),
        new Obstacle(Main.WIDTH/2 - 100, Main.HEIGHT - 200, 200, 20),
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
