package Core;
import java.util.Map;
import java.util.Random;
import javax.swing.JFrame;
import java.awt.*;

import Dots.AccelVector;
import Dots.Body;
import Dots.Brain;
import Dots.Population;
import Menus.HUD;
import Menus.LoadMenu;
import Menus.PauseMenu;
import Menus.SaveMenu;
import Menus.SettingsMenu;
import Menus.StartMenu;
import Maps.OriginalMap;

import java.awt.image.BufferStrategy;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    /* MAPS */
    private OriginalMap map1;

    private Map activeMap;

    /* MENUS */
    public PauseMenu pause;
    public StartMenu start;
    public SettingsMenu settingsMenu;
    public LoadMenu loadMenu;
    public SaveMenu saveMenu;

    public Population pop;
    //private Body test;

    public static Window window;
    
    public enum STATE{
        Start,
        Game,
        Settings,
        Save,
        Load,
    };
    
    public STATE gameState = STATE.Start;

    public Main(){
        /* MAPS */
        map1 = new OriginalMap();

        /* MENUS */
        pause = new PauseMenu();
        start = new StartMenu();
        settingsMenu = new SettingsMenu();
        loadMenu = new LoadMenu();
        saveMenu = new SaveMenu();

        pop = new Population(POPULATION_SIZE);

        hud = new HUD(pop);
        window = new Window(Constants.WIDTH, Constants.HEIGHT, "Evolution", this);

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
        if(gameState == STATE.Start){
            start.tick();
        }else if(gameState == STATE.Game){
            if(!paused){
                //test.tick(obs);
                map1.tick();
                if(pop.allDotsDead()){
                    pop.calculateFitness(map1);
                    pop.naturalSelection(map1);
                    pop.mutate();
                    //pop.skipSteps();
                }else{
                    pop.tick(map1);
                }
                hud.tick();
            }
            else{
                pause.tick();
            }
        }else if(gameState == STATE.Settings){
            settingsMenu.tick();
        }else if(gameState == STATE.Save){
            saveMenu.tick();
        }else if(gameState == STATE.Load){
            loadMenu.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        if(gameState == STATE.Start){
            start.render(g);
        }else if(gameState == STATE.Game){
            if(!paused){
                // render HUD, and all bodies
                //test.render(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
                map1.render(g);
                if(!pop.allDotsDead()){
                    pop.render(g);
                }
                hud.render(g);
            }else{
                //g.setColor(Color.black);
                //g.fillRect(0, 0, WIDTH, HEIGHT);
                pause.render(g);
            }
		}else if(gameState == STATE.Settings){
            settingsMenu.render(g);
        }else if(gameState == STATE.Save){
            saveMenu.render(g);
        }else if(gameState == STATE.Load){
            loadMenu.render(g);
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

    public void load(int slot){
        pop = new Population(POPULATION_SIZE, true, slot);
        gameState = STATE.Game;
    }
	
	public static void main(String args[]) {
		new Main();
	}

    // try to read through this for help: https://stackoverflow.com/questions/54292078/saving-and-loading-an-object-in-java-that-isnt-a-string-or-int
}
