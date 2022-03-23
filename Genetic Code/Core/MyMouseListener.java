package Core;

import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

import org.w3c.dom.events.MouseEvent;
import Menus.Button;
import Menus.LoadMenu;
import Menus.SaveMenu;

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
        //System.out.println("Mouse click");

        if(game.gameState == Main.STATE.Start){
            Button start = game.start.startButton;
            Button load = game.start.loadButton;
            Button settings = game.start.settingsButton;

            if(start.checkWithinButton(mousePos)){
                game.gameState = Main.STATE.Game;
            }else if(load.checkWithinButton(mousePos)){
                game.loadMenu = new LoadMenu();
                game.gameState = Main.STATE.Load;
                //game.load();
            }else if(settings.checkWithinButton(mousePos)){
                game.gameState = Main.STATE.Settings;
            }

        }else if(game.gameState == Main.STATE.Game){
            Button settings = game.pause.settings;
            Button save = game.pause.save;
            Button quit = game.pause.quit;

            if(Main.paused){
                if(settings.checkWithinButton(mousePos)){
                    game.gameState = Main.STATE.Settings;
                }else if(save.checkWithinButton(mousePos)){
                    game.saveMenu = new SaveMenu();
                    game.gameState = Main.STATE.Save;
                    // TODO Open save screen
                }else if(quit.checkWithinButton(mousePos)){
                    Main.window.closeWindow();
                    game.stop();
                }
            }

        }else if(game.gameState == Main.STATE.Settings){
            Button fitnessToggle = game.settingsMenu.fitnessToggle;
            Button popPlus = game.settingsMenu.populationPlus;
            Button popMin = game.settingsMenu.populationMinus;
            Button back = game.settingsMenu.back;

            if(fitnessToggle.checkWithinButton(mousePos)){
                Settings.calcBestStep = !Settings.calcBestStep;
            }else if(popPlus.checkWithinButton(mousePos)){
                Settings.populationSize += 100;
            }else if(popMin.checkWithinButton(mousePos)){
                Settings.populationSize -= 100;
            }else if(back.checkWithinButton(mousePos)){
                System.out.println("Back");
                if(Main.paused){
                    game.gameState = Main.STATE.Game;
                }else{
                    game.gameState = Main.STATE.Start;
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