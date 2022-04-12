package Core;

import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

import org.w3c.dom.events.MouseEvent;
import Menus.Button;
import Menus.LoadMenu;
import Menus.SaveMenu;
import Maps.Maps;

public class MyMouseListener extends JComponent implements MouseInputListener{
    private Main game;
    private Window window;
    public MyMouseListener(Main game, Window window){
        this.game = game;
        this.window = window;
    }
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        Point mousePos = window.frame.getMousePosition();

        if(game.gameState == Main.STATE.Start){
            Button[] buttons = game.start.buttons;
            // Start, Load, Settings

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    switch(i){
                        case(0):
                            game.gameState = Main.STATE.MapSelection;
                            break;
                        case(1):
                            game.loadMenu = new LoadMenu();
                            game.gameState = Main.STATE.Load;
                            break;
                        case(2):
                            game.gameState = Main.STATE.Settings;
                            break;
                    }
                }
            }
        }else if(game.gameState == Main.STATE.Running){

        }else if(game.gameState == Main.STATE.Paused){

        }else if(game.gameState == Main.STATE.Settings){

        }else if(game.gameState == Main.STATE.Save){

        }else if(game.gameState == Main.STATE.Load){

        }else if(game.gameState == Main.STATE.MapSelection){
            int[][] coordinates = game.mapsMenu.getCoordinates();
            // Original Map, New Map
            boolean mapSelected = false;

            for(int i = 0; i < coordinates.length; i++){
                if(game.mapsMenu.checkWithinButton(mousePos, i)){
                    switch(i){
                        case(0):
                            game.setMap(Maps.originalMap);
                            mapSelected = true;
                            break;
                        case(1):
                            // TODO set to the new map
                            break;
                    }
                    if(mapSelected){                          // Don't start the game unless a map has been properly selected
                        game.gameState = Main.STATE.Running;
                    }
                }
            }
        }
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

}