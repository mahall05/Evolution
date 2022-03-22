package Menus;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import Core.Main;
import Core.Main.STATE;

public class MyMouseListener implements MouseInputListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        Point mousePos = Main.window.frame.getMousePosition();
        System.out.println("Mouse click");

        if(Main.gameState == STATE.Start){
            Button start = Main.start.startButton;
            Button load = Main.start.loadButton;
            if(start.checkWithinButton(mousePos)){
                Main.gameState = STATE.Game;
            }else if(load.checkWithinButton(mousePos)){

            }
        }else if(Main.gameState == STATE.Game){

        }else if(Main.gameState == STATE.Settings){

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
