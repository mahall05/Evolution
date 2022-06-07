package Core;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;

public class Window extends Canvas{
    public JFrame frame;

    private MyMouseListener mouseListener;

    public Window(int width, int height, String title, Main game){
        frame = new JFrame(title);
        mouseListener = new MyMouseListener(game, this);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.add(game);
        game.addMouseListener((MouseInputListener) mouseListener);
        game.start();
    }

    public void closeWindow(){
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}