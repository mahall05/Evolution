package Core;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;
import javax.swing.text.AbstractDocument.ElementEdit;


public class MyMouseListener extends JComponent implements MouseInputListener{
    private Main game;
    private Window window;
    public MyMouseListener(Main game, Window window){
        this.game = game;
        this.window = window;
    }
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        Point mousePos = window.frame.getMousePosition();

        if(game.gameState == Main.STATE.Running){
            System.out.println("x: " + mousePos.getX() + ", y: " + mousePos.getY());
        }
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        Point mousePos = window.frame.getMousePosition();
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        Point mousePos = window.frame.getMousePosition();
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent arg0) {
        
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent arg0) {
        
    }

}