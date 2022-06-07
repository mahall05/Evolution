package Core;

import java.awt.*;
import java.awt.Polygon;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Main extends Canvas implements Runnable{

    private Thread thread; // The game runs on this thread
    private boolean running = false;

    private int[] xpoints = {100, 234, 75};
    private int[] ypoints = {400, 2, 56};

    private Polygon poly = new Polygon(xpoints, ypoints, 3);

    public static Window window;

    public enum STATE{
        Running,
    };

    public STATE gameState;
    public STATE prevState = STATE.Running;

    public Main() throws IOException{

        window = new Window(Constants.WIDTH, Constants.HEIGHT, "Evolution", this);
    }

    private void tick(){
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawPolygon(poly);

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
