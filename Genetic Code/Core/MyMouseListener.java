package Core;

import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

import Core.Files.FileHandler;
import Core.Files.SaveInfo;
import Menus.Button;
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
        Point mousePos = window.frame.getMousePosition();

        if(game.gameState == Main.STATE.Start){
            Button[] buttons = game.start.buttons;
            // Start, Load, Settings

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    game.prevState = Main.STATE.Start;
                    switch(i){
                        case(0):
                            game.gameState = Main.STATE.MapSelection;
                            break;
                        case(1):
                            game.loadMenu.load();
                            game.gameState = Main.STATE.Load;
                            break;
                        case(2):
                            game.settingsMenu.prevState = Main.STATE.Start;
                            game.gameState = Main.STATE.Settings;
                            break;
                    }
                }
            }
        }else if(game.gameState == Main.STATE.Running){

        }else if(game.gameState == Main.STATE.Paused){
            Button[] buttons = game.pause.buttons;
            // Settings, save, quit

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    game.prevState = Main.STATE.Paused;
                    switch(i){
                        case(0):
                            game.settingsMenu.prevState = Main.STATE.Paused;
                            game.gameState = Main.STATE.Settings;
                            break;
                        case(1):
                            game.saveMenu.load();
                            game.gameState = Main.STATE.Save;
                            break;
                        case(2):
                            Main.window.closeWindow();
                            game.stop();
                            break;
                    }
                }
            }
        }else if(game.gameState == Main.STATE.Settings){
            Button back = game.settingsMenu.back;
            Button fitnessToggle = game.settingsMenu.fitnessToggle;
            Button[] populationModifiers = game.settingsMenu.populationModifiers;
            Button save = game.settingsMenu.save;

            if(back.checkWithinButton(mousePos)){
                game.gameState = game.settingsMenu.prevState;
            }else if(fitnessToggle.checkWithinButton(mousePos)){
                Settings.calcBestStep = !Settings.calcBestStep;
            }else if(populationModifiers[0].checkWithinButton(mousePos)){
                Settings.populationSize -= 100;
            }else if(populationModifiers[1].checkWithinButton(mousePos)){
                Settings.populationSize += 100;
            }else if(save.checkWithinButton(mousePos)){
                FileHandler.saveSettings();
            }
        }else if(game.gameState == Main.STATE.Save){
            Button[] buttons = game.saveMenu.getButtons();

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    FileHandler.save(game.pop, game.activeMap, (i+1));
                    game.saveMenu.load();
                }
            }

        }else if(game.gameState == Main.STATE.Load){
            Button[] buttons  = game.loadMenu.getButtons();

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    SaveInfo loadedInfo = FileHandler.loadInfo(i+1);
                    game.loadSimulation(FileHandler.loadPaths(i+1), loadedInfo.map, loadedInfo.generation);
                    game.loadSettings(FileHandler.loadBrainSettings(i+1));
                    game.gameState = Main.STATE.Running;
                }
            }
        }else if(game.gameState == Main.STATE.MapSelection){
            int[][] coordinates = game.mapsMenu.getCoordinates();
            Button back = game.mapsMenu.back;
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
                        game.newSimulation();
                        game.gameState = Main.STATE.Running;
                    }
                }
            }
            if(back.checkWithinButton(mousePos)){
                game.gameState = Main.STATE.Start;
            }
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
        
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent arg0) {
        
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent arg0) {
        
    }

}