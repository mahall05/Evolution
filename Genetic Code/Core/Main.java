package Core;

import Core.Files.FileHandler;
import Core.Files.SaveSettings;
import Dots.AccelVector;
import Dots.Population;
import Maps.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import Menus.*;

public class Main extends Canvas implements Runnable{

    private Thread thread; // The game runs on this thread
    private boolean running = false;

    private HUD hud;

    /* MENUS */
    public PauseMenu pause;
    public StartMenu start;
    public SettingsMenu settingsMenu;
    public LoadMenu loadMenu;
    public SaveMenu saveMenu;
    public MapsMenu mapsMenu;
    public SettingsConformation settingsConf;

    public Population pop;

    public Map activeMap;
    public static Window window;

    public enum STATE{
        Start,
        Running,
        Paused,
        Settings,
        Save,
        Load,
        MapSelection,
        SettingsConformation,
    };

    public STATE gameState;
    public STATE prevState = STATE.Start;

    public Main() throws IOException{
        /* MENUS */
        start = new StartMenu();
        pause = new PauseMenu();
        settingsMenu = new SettingsMenu();
        loadMenu = new LoadMenu();
        saveMenu = new SaveMenu();
        mapsMenu = new MapsMenu();
        settingsConf = new SettingsConformation();

        window = new Window(Constants.WIDTH, Constants.HEIGHT, "Evolution", this);
        loadSettings(FileHandler.loadSettings());
    }

    private void tick(){
        if(gameState == STATE.Start){
            start.tick();
        }else if(gameState == STATE.Running){
            activeMap.tick();
            if(pop.allDotsDead()){
                pop.calculateFitness(activeMap);
                pop.naturalSelection(activeMap);
                pop.mutate();
            }else{
                pop.tick(activeMap);
            }
            hud.tick();
        }else if(gameState == STATE.Paused){
            pause.tick();
        }else if(gameState == STATE.Settings){
            settingsMenu.tick();
        }else if(gameState == STATE.Save){
            saveMenu.tick();
        }else if(gameState == STATE.Load){
            loadMenu.tick();
        }else if(gameState == STATE.MapSelection){
            mapsMenu.tick();
        }else if(gameState == STATE.SettingsConformation){
            settingsConf.tick();
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
        }else if(gameState == STATE.Running){
            g.setColor(Color.white);
            g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
            activeMap.render(g);
            if(!pop.allDotsDead()){
                pop.render(g);
            }
            hud.render(g);
        }else if(gameState == STATE.Paused){
            pause.render(g);
        }else if(gameState == STATE.Settings){
            settingsMenu.render(g);
        }else if(gameState == STATE.Save){
            saveMenu.render(g);
        }else if(gameState == STATE.Load){
            loadMenu.render(g);
        }else if(gameState == STATE.MapSelection){
            mapsMenu.render(g);
        }else if(gameState == STATE.SettingsConformation){
            settingsConf.render(g);
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

    public void loadSimulation(AccelVector[] loadedPaths, Map map, int loadedGen){
        this.activeMap = map;
        pop = new Population(loadedPaths, loadedGen);
        hud = new HUD(pop);
    }

    public void setMap(Map map){
        this.activeMap = map;
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

    public void loadSettings(SaveSettings settings){
        Settings.calcBestStep = settings.calcBestStep;
        Settings.populationSize = settings.populationSize;
    }

    public static void main(String[] args) throws IOException{
        new Main();
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
}
