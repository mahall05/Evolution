package Core;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

import Maps.Maps;
import Menus.Button;

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

        if(game.gameState == Main.STATE.Start){
            Button[] buttons = game.start.buttons;
            // Start, Load, Settings

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    game.prevState = Main.STATE.Start;
                    switch(i){
                        case(0):
                            game.gameState = Main.STATE.LineSelect;
                            break;
                        case(1):
                            //game.loadMenu.load();
                            //game.gameState = Main.STATE.Load;
                            break;
                        case(2):
                            //game.settingsMenu.prevState = Main.STATE.Start;
                            //game.gameState = Main.STATE.Settings;
                            break;
                    }
                }
            }
        }else if(game.gameState == Main.STATE.LineSelect){
            Button[] buttons = game.lineMenu.buttons;
            Button[] modButtons = game.lineMenu.speedMods;

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    game.prevState = Main.STATE.LineSelect;
                    switch(i){
                        case(0):
                            //Start with button
                            break;
                        case(1):
                            //Start without button
                            break;
                    }
                }else if(modButtons[i].checkWithinButton(mousePos)){
                    switch(i){
                        case(0):
                            game.lineMenu.speedChange(-1);
                            break;
                        case(1):
                            game.lineMenu.speedChange(1);
                            break;
                    }
                }
            }
        }else if(game.gameState == Main.STATE.Running){
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

        if(game.gameState == Main.STATE.Start){
            Button[] buttons = game.start.buttons;
            // Start, Load, Settings

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    buttons[i].setButtonColor(Color.GRAY);
                }
            }
        }else if(game.gameState == Main.STATE.LineSelect){
            Button[] buttons = game.lineMenu.buttons;
            Button[] modButtons = game.lineMenu.speedMods;

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    buttons[i].setButtonColor(Color.GRAY);
                }else if(modButtons[i].checkWithinButton(mousePos)){
                    modButtons[i].setButtonColor(Color.GRAY);
                }
            }
        }
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        Point mousePos = window.frame.getMousePosition();

        if(game.gameState == Main.STATE.Start){
            Button[] buttons = game.start.buttons;
            // Start, Load, Settings

            for(int i = 0; i < buttons.length; i++){
                buttons[i].setButtonColor(Color.WHITE);
            }
        }else if(game.gameState == Main.STATE.LineSelect){
            Button[] buttons = game.lineMenu.buttons;
            Button[] modButtons = game.lineMenu.speedMods;

            for(int i = 0; i < buttons.length; i++){
                buttons[i].setButtonColor(Color.white);
                modButtons[i].setButtonColor(Color.white);
            }
        }
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent arg0) {
        
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent arg0) {
        
    }

}