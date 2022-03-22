package Core;
import java.util.Random;
import javax.swing.JFrame;

import Dots.AccelVector;
import Dots.Body;
import Dots.Brain;
import Dots.Population;
import Menus.HUD;
import Menus.PauseMenu;
import Menus.SettingsMenu;
import Menus.StartMenu;
import More.Obstacles;

import java.awt.*;
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
    private Obstacles obs;
    public PauseMenu pause;
    public StartMenu start;
    public SettingsMenu settingsMenu;

    private Population pop;
    //private Body test;

    public static Window window;

    private static Body[] loadedBodies;
    
    public enum STATE{
        Start,
        Game,
        Settings,
    };
    
    public STATE gameState = STATE.Settings;

    public Main(){
        if(!Constants.load){
            pop = new Population(POPULATION_SIZE);
        }else{
            load();
            pop = new Population(loadedBodies);
        }
        //test = new Body(500);
        hud = new HUD(pop);
        obs = new Obstacles();
        pause = new PauseMenu();
        start = new StartMenu();
        settingsMenu = new SettingsMenu();
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
        }else if(gameState == STATE.Settings){
            settingsMenu.tick();
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
		}else if(gameState == STATE.Settings){
            settingsMenu.render(g);
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

    /* Save Brains */
    public static void save(Body[] bodies){
        try
		  {
			 //Create file output stream
			 FileOutputStream fileOutStr =  new FileOutputStream("bodies.ser"); 
			//Create object output stream and write object
			 ObjectOutputStream objOutStr = new ObjectOutputStream(fileOutStr);
			 objOutStr.writeObject(bodies);
			 //Close all streams
			 objOutStr.close();
			 fileOutStr.close();
			 //System.out.printf("Serialized data is saved in a file - bodies.ser");
		  }catch(IOException exp)
		  {
			  System.out.println("Error IOException");
			  exp.printStackTrace();
		  }
    }

    public static void load(){
        try{
			FileInputStream fileInStr = new FileInputStream("bodies.ser");
		 	ObjectInputStream objInStr = new ObjectInputStream(fileInStr);
		 	loadedBodies = (Body[]) objInStr.readObject();
		 	objInStr.close();
		 	fileInStr.close();
			//System.out.println(", theBuilding has been deserialized");
	  	}catch(IOException exp){
		  	System.out.println("Error IOException");
		 	exp.printStackTrace();
		 	return;
	  	}catch(ClassNotFoundException cexp){
			System.out.println("BuildingGUI class not found");
			cexp.printStackTrace();
			return;
	  	}
    }
    // try to read through this for help: https://stackoverflow.com/questions/54292078/saving-and-loading-an-object-in-java-that-isnt-a-string-or-int
}
