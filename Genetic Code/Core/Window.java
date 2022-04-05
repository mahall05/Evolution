package Core;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;

public class Window extends Canvas{
    public JFrame frame;

    public Window(int width, int height, String title, Main game){
        frame = new JFrame();
        // TODO Create Mouse and Key Listeners
        /*
        MyMouseListener mouseClick = new MyMouseListener(game, this);
        KeyInput input = new KeyInput(game);
        */
        frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.add(game);
        // TODO listeners
        /*
        frame.addMouseListener((MouseInputListener) mouseClick);
        game.addMouseListener(mouseClick);
        game.addKeyListener(input);
        */
        game.start();
    }

    public void closeWindow(){
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}