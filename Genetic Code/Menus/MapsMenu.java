package Menus;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.lang.Object.*;

import javax.imageio.ImageIO;

import Core.Constants;

public class MapsMenu {
    private BufferedImage[] images = new BufferedImage[2];
    private int[][] coordinates;
    private String[] labels = {"Original Map", "New Map"};
    private int imageX = 50, imageY = 225;
    private int mouseOffsetX = -7;
    private int mouseOffsetY = -30;

    //private int imageSizeX

    public MapsMenu() throws IOException{
        coordinates = new int[images.length][2];
        images[0] = ImageIO.read(new File("Menus/MapImages", "map1.png"));
        images[1] = ImageIO.read(new File("Menus/MapImages", "map2.png"));

        coordinates[0][0] = imageX;
        coordinates[0][1] = imageY;

        for(int i = 1; i < coordinates.length; i++){
            coordinates[i][1] = coordinates[0][1];
            coordinates[i][0] = coordinates[i-1][0]+images[i-1].getWidth()+30;
        }
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 60));
        g.setColor(Color.white);
        g.drawString("Select Map", (Constants.WIDTH/2)-165, (Constants.HEIGHT/2)-200);
        
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 20));
        for(int i = 0; i < images.length; i++){
            g.drawImage(images[i], coordinates[i][0], coordinates[i][1], null);
            g.drawString(labels[i], coordinates[i][0]+(images[i].getWidth()/2)-50, coordinates[i][1]+images[i].getHeight()+20);
        }
    }

    public int[][] getCoordinates(){
        return coordinates;
    }

    public boolean checkWithinButton(Point point, int image){
        if(point.getX()+mouseOffsetX >= coordinates[image][0] && point.getX()+mouseOffsetX <= coordinates[image][0]+images[image].getWidth()-1 && point.getY()+mouseOffsetY >= coordinates[image][1] && point.getY()+mouseOffsetY <= coordinates[image][1]+images[image].getHeight()-1){
            return true;
        }else{
            return false;
        }
    }
}
