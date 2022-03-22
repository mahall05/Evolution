package Tester;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

public class MainClass{
    public static void main(String[] args){
        String title = "Test";
        int width = 1000, height = width / 12 * 9;
        JFrame frame = new JFrame();
        MyComponent mouseClick = new MyComponent();
        frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // This basically allows the "X" button in the top right to close the program
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);     // Game starts in the middle of the screen, rather than top left
        frame.setVisible(true);

        frame.addMouseListener((MouseInputListener) mouseClick);
    }
}