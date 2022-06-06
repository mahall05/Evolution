package Core;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

import Core.Files.FileHandler;
import Core.Files.SaveInfo;
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
                            game.prevState = Main.STATE.Start;
                            break;
                        case(1):
                            game.loadMenu.load();
                            game.gameState = Main.STATE.Load;
                            game.prevState = Main.STATE.Start;
                            break;
                        case(2):
                            game.prevState = Main.STATE.Start;
                            game.gameState = Main.STATE.Settings;
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
                            //Start with line
                            game.activeMap.movingObstacles[0].activate(true);
                            game.activeMap.movingObstacles[0].setSpeed(game.lineMenu.lineSpeed/10, 0);
                            game.prevState = Main.STATE.Paused;
                            game.gameState = Main.STATE.Running;
                            break;
                        case(1):
                            //Start without line
                            game.prevState = Main.STATE.Paused;
                            game.gameState = Main.STATE.Running;
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
        }else if(game.gameState == Main.STATE.Settings){
            Button back = game.settingsMenu.back;
            Button fitnessToggle = game.settingsMenu.fitnessToggle;
            Button[] populationModifiers = game.settingsMenu.populationModifiers;
            Button save = game.settingsMenu.save;

            if(back.checkWithinButton(mousePos)){
                game.gameState = game.prevState;
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
            Button[] buttons = game.loadMenu.getButtons();

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    SaveInfo loadedInfo = FileHandler.loadInfo(i+1);
                    game.loadSimulation(FileHandler.loadActions(i+1), loadedInfo.map, loadedInfo.generation);
                    game.loadSettings(FileHandler.loadBrainSettings(i+1));
                    game.gameState = Main.STATE.Running;
                }
            }
        }else if(game.gameState == Main.STATE.Paused){
            Button[] buttons = game.pauseMenu.buttons;

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    switch(i){
                        case(0):
                            game.gameState = Main.STATE.Settings;
                            break;
                        case(1):
                            game.gameState = Main.STATE.Save;
                            break;
                        case(2):
                            Main.window.closeWindow();
                            game.stop();
                            break;
                    }

                    game.prevState = Main.STATE.Paused;
                }
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
        }else if(game.gameState == Main.STATE.Settings){
            Button back = game.settingsMenu.back;
            Button fitnessToggle = game.settingsMenu.fitnessToggle;
            Button[] populationModifiers = game.settingsMenu.populationModifiers;
            Button save = game.settingsMenu.save;

            if(back.checkWithinButton(mousePos)){
                back.setButtonColor(Color.GRAY);
            }else if(fitnessToggle.checkWithinButton(mousePos)){
                fitnessToggle.checkWithinButton(mousePos);
            }else if(save.checkWithinButton(mousePos)){
                save.setButtonColor(Color.GRAY);
            }else{
                for(int i = 0; i < populationModifiers.length; i++){
                    if(populationModifiers[i].checkWithinButton(mousePos)){
                        populationModifiers[i].setButtonColor(Color.GRAY);
                    }
                }
            }
        }else if(game.gameState == Main.STATE.Save){
            Button[] buttons = game.saveMenu.getButtons();

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    buttons[i].setButtonColor(Color.GRAY);
                }
            }
        }else if(game.gameState == Main.STATE.Load){
            Button[] buttons = game.loadMenu.getButtons();

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    buttons[i].setButtonColor(Color.GRAY);
                }
            }
        }else if(game.gameState == Main.STATE.Paused){
            Button[] buttons = game.pauseMenu.buttons;

            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].checkWithinButton(mousePos)){
                    buttons[i].setButtonColor(Color.GRAY);
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
        }else if(game.gameState == Main.STATE.Settings){
            Button back = game.settingsMenu.back;
            Button fitnessToggle = game.settingsMenu.fitnessToggle;
            Button[] populationModifiers = game.settingsMenu.populationModifiers;
            Button save = game.settingsMenu.save;

            back.setButtonColor(Color.WHITE);
            fitnessToggle.setButtonColor(Color.WHITE);
            save.setButtonColor(Color.WHITE);
            for(int i = 0; i < populationModifiers.length; i++){
                populationModifiers[i].setButtonColor(Color.WHITE);
            }
        }else if(game.gameState == Main.STATE.Save){
            Button[] buttons = game.saveMenu.getButtons();

            for(int i = 0; i < buttons.length; i++){
                buttons[i].setButtonColor(Color.WHITE);
            }
        }else if(game.gameState == Main.STATE.Load){
            Button[] buttons = game.loadMenu.getButtons();

            for(int i = 0; i < buttons.length; i++){
                buttons[i].setButtonColor(Color.WHITE);
            }
        }else if(game.gameState == Main.STATE.Paused){
            Button[] buttons = game.pauseMenu.buttons;

            for(int i = 0; i < buttons.length; i++){
                buttons[i].setButtonColor(Color.WHITE);
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