import java.awt.*;

public class Body {
    public Brain brain;
    public Path path;

    int width = 7;
    int height = 7;

    boolean dead = false;
    boolean reachedGoal = false;
    //boolean isBest = false;

    public Body(){
        brain = new Brain(Population.brainSize);
        path = new Path();
        path.setVelCap(7);
    }

    private void move(){
        /*
        if(brain.directions.length > brain.step){
            path.addAcceleration(brain.directions[brain.step].xAcc, brain.directions[brain.step].yAcc);
            brain.step++;
        }else{
            dead = true;
        }
        */
        path.addAcceleration(brain.directions[brain.step].xAcc, brain.directions[brain.step].yAcc);
        brain.step++;
        path.accelerate();
        path.move();
    }

    public void tick(){
        if(!dead && !reachedGoal){
            move();
        }
    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillOval(path.x, path.y, width, height);
    }
}
