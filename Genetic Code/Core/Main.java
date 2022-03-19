package Core;
import java.util.Random;
import javax.swing.JFrame;

import Dots.Population;
import Menus.HUD;
import Menus.PauseMenu;
import More.Obstacles;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable{
    public static final int POPULATION_SIZE = 5000;

    private Thread thread;  // The game runs on this thread
    private boolean running = false;

    public static boolean paused = false;
    //public int diff;
    
    // 0 = normal
    // 1 = hard

    private Random r;
    private HUD hud;
    private Obstacles obs;
    private PauseMenu pause;

    private Population pop;
    //private Body test;
    
    public enum STATE{
        Game,
        Settings,
    };
    
    public static STATE gameState = STATE.Game;

    public Main(){
        pop = new Population(POPULATION_SIZE);
        //test = new Body(500);
        hud = new HUD(pop);
        obs = new Obstacles();
        pause = new PauseMenu();
        new Window(Constants.WIDTH, Constants.HEIGHT, "Evolution", this);

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
                //test.tick(obs);
                obs.tick();
                hud.tick();
                if(pop.allDotsDead()){
                    pop.calculateFitness(obs);
                    pop.naturalSelection(obs);
                    pop.mutate();
                    //pop.skipSteps();
                }else{
                    pop.tick(obs);
                }
            }
            else{
                pause.tick();
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

        if(gameState == STATE.Game){
            if(!paused){
                // render HUD, and all bodies
                //test.render(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
                obs.render(g);
                hud.render(g);
                if(!pop.allDotsDead()){
                    pop.render(g);
                }
            }
            else{
                //g.setColor(Color.black);
                //g.fillRect(0, 0, WIDTH, HEIGHT);
                pause.render(g);
            }
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