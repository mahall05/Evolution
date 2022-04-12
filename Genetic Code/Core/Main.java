package Core;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import Dots.Population;
import Maps.*;

import java.awt.*;
import java.awt.event.*;
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

    public Population pop;

    private Map activeMap;
    public static Window window;

    public enum STATE{
        Start,
        Running,
        Paused,
        Settings,
        Save,
        Load,
        MapSelection,
    };

    public STATE gameState;

    public Main() throws IOException{
        /* MENUS */
        start = new StartMenu();
        pause = new PauseMenu();
        settingsMenu = new SettingsMenu();
        loadMenu = new LoadMenu();
        saveMenu = new SaveMenu();
        mapsMenu = new MapsMenu();

        window = new Window(Constants.WIDTH, Constants.HEIGHT, "Evolution", this);
        activeMap = Maps.originalMap; // TODO have this selection happen by the user
        
        /* TESTING */
        pop = new Population();
        hud = new HUD(pop);
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

    public void setMap(Map map){
        this.activeMap = map;
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;

        gameState = STATE.Load;
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
}
