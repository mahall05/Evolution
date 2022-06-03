package Core;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import Bodies.Actions;
import Bodies.Body;
import Bodies.Population;
import Core.Files.FileHandler;
import Core.Files.SaveSettings;
import Maps.*;
import Menus.*;

public class Main extends Canvas implements Runnable{

    private Thread thread; // The game runs on this thread
    private boolean running = false;

    public static Window window;

    public Population pop;
    //public Body test;
    public Map activeMap;

    public HUD hud;
    public StartMenu start;
    public LineSelectMenu lineMenu;
    public SettingsMenu settingsMenu;
    public SaveMenu saveMenu;
    public LoadMenu loadMenu;
    public PauseMenu pauseMenu;

    public enum STATE{
        Start,
        LineSelect,
        Settings,
        Save,
        Load,
        Running,
        Paused,
    };

    public STATE gameState;
    public STATE prevState = STATE.Start;

    public Main() throws IOException{
        activeMap = Maps.testing;
        pop = new Population();
        //test = new Body(5000);

        hud = new HUD(pop);
        start = new StartMenu();
        lineMenu = new LineSelectMenu();
        settingsMenu = new SettingsMenu();
        saveMenu = new SaveMenu();
        loadMenu = new LoadMenu();
        pauseMenu = new PauseMenu();

        window = new Window(Constants.WIDTH, Constants.HEIGHT, "Evolution", this);
        loadSettings(FileHandler.loadSettings());
    }

    private void tick(){
        if(gameState == STATE.Start){
            start.tick();
        }else if(gameState == STATE.LineSelect){
            lineMenu.tick();
        }else if(gameState == STATE.Running){
            activeMap.tick();
            
            if(pop.allBodiesDead()){
                pop.calculateFitness(activeMap);
                pop.naturalSelection(activeMap);
                pop.mutate();
                pop.skipSteps();
            }else{
                pop.tick(activeMap);
            }
            
            hud.tick();
            //test.tick(activeMap);
        }else if(gameState == STATE.Settings){
            settingsMenu.tick();
        }else if(gameState == STATE.Save){
            saveMenu.tick();
        }else if(gameState == STATE.Load){
            loadMenu.tick();
        }else if(gameState == STATE.Paused){
            pauseMenu.tick();
        }

        // TODO remove this if this causes things not to work
        if(gameState == STATE.Running && prevState != STATE.Paused){
            prevState = STATE.Paused;
        }else if(gameState == STATE.Paused && prevState != STATE.Running){
            prevState = STATE.Running;
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
        }else if(gameState == STATE.LineSelect){
            lineMenu.render(g);
        }else if(gameState == STATE.Running){
            g.setColor(Color.white);
            g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
            activeMap.render(g);
            pop.render(g);
            //test.render(g);
            hud.render(g);
        }else if(gameState == STATE.Settings){
            settingsMenu.render(g);
        }else if(gameState == STATE.Save){
            saveMenu.render(g);
        }else if(gameState == STATE.Load){
            loadMenu.render(g);
        }else if(gameState == STATE.Paused){
            pauseMenu.render(g);
        }

        g.dispose();
        bs.show();
    }

    @Override
    public void run() { // The game loop
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
            }
        }
    }

    public void newSimulation(){
        pop = new Population();
        hud = new HUD(pop);
    }

    public void loadSimulation(Actions[] loadedActions, Map map, int loadedGen){
        this.activeMap = map;
        pop = new Population(loadedActions, loadedGen);
        hud = new HUD(pop);
    }

    public void setMap(Map map){
        this.activeMap = map;
    }

    public void loadSettings(SaveSettings settings){
        Settings.calcBestStep = settings.calcBestStep;
        Settings.populationSize = settings.populationSize;
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;

        gameState = STATE.Start;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        new Main();
    }

    public static int randomInt(int minInclusive, int maxInclusive){
        int range = maxInclusive - minInclusive + 1;
        int rng = (int) (int)(Math.random() * range) + minInclusive;

        return rng;
    }

    public static double randomDouble(double minInclusive, double maxInclusive){
        double range = maxInclusive - minInclusive + 1;
        double rng = (Math.random() * range) + minInclusive;

        return rng;
    }
}
