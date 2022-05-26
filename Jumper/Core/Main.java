package Core;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import Bodies.Body;
import Bodies.Population;
import Maps.*;

public class Main extends Canvas implements Runnable{

    private Thread thread; // The game runs on this thread
    private boolean running = false;

    public static Window window;

    public Population pop;
    //public Body test;
    public Map activeMap;

    public enum STATE{
        Start,
        Running,
    };

    public STATE gameState;
    public STATE prevState = STATE.Start;

    public Main() throws IOException{
        activeMap = Maps.testing;
        pop = new Population();
        //test = new Body(5000);
        window = new Window(Constants.WIDTH, Constants.HEIGHT, "Evolution", this);
    }

    private void tick(){
        if(gameState == STATE.Start){
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
            
            
            //test.tick(activeMap);
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
        }else if(gameState == STATE.Running){
            g.setColor(Color.white);
            g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
            activeMap.render(g);
            pop.render(g);
            //test.render(g);
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
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;

        gameState = STATE.Running;
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
