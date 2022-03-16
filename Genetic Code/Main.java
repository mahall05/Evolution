import java.util.Random;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable{
    public static final int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;     // Set the size of the window
    public static final int POPULATION_SIZE = 100;

    private Thread thread;  // The game runs on this thread
    private boolean running = false;

    public static boolean paused = false;
    //public int diff;
    
    // 0 = normal
    // 1 = hard

    private Random r;
    private HUD hud;
    private Obstacles obs;

    private Population pop;
    //private Body test;
    
    public enum STATE{
        Menu,
        Game,
    };
    
    public static STATE gameState = STATE.Game;

    public Main(){
        pop = new Population(POPULATION_SIZE);
        //test = new Body(500);
        hud = new HUD();
        obs = new Obstacles();
        new Window(WIDTH, HEIGHT, "Evolution", this);

        r = new Random();
    }

    public synchronized void start(){
        thread = new Thread(this);     // Initialize a new thread
		thread.start();     // Start the thread
		running = true;     // Thread is running
    }

    public synchronized void stop(){
        try{
            thread.join();  // Stops the thread
            running = false;  // Thread is not running
        }catch(Exception e){
            e.printStackTrace();  // Print an error message
        }
    }

    public void run() {     // The game loop
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;     // The amount of ticks/second
		double ns = 1000000000 / amountOfTicks;     // The amount of nanoticks/tick
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

  /*
  public void run(){
    double pastTime = System.currentTimeMillis();
    while(running){
      if(System.currentTimeMillis() - pastTime >= 1000){
        tick();
        render();
      }
    }
  }
  */

    private void tick(){
        if(gameState == STATE.Game){
            if(!paused){
                pop.tick(obs);
                //test.tick(obs);
                obs.tick();
                if(pop.allDotsDead()){
                    // TODO implement these methods
                    /*
                    pop.calculateFitness();
                    pop.naturalSelection();
                    pop.mutate();
                    */
                }
            }
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if(paused){
            g.setColor(Color.black);
            g.drawString("PAUSED", 100, 100);
        }

        if(gameState == STATE.Game){
            // render HUD, and all bodies
            pop.render(g);
            //test.render(g);
            obs.render(g);
		}

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, int max){
        if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
    }

    public static int randomInt(int minInclusive, int maxInclusive){
        int range = maxInclusive - minInclusive + 1;
        int rng = (int)(Math.random() * range) + minInclusive;
        return rng;
    }

    public static double randomDouble(double minInclusive, double maxInclusive){
        double range = maxInclusive - minInclusive + 1;
        double rng = (Math.random() * range) + minInclusive;
        return rng;
    }
	
	public static void main(String args[]) {
		new Main();
	}
}
