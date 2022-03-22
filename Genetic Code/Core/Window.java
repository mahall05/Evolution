package Core;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import java.awt.*;

public class Window extends Canvas{
	public JFrame frame;
	
	public Window(int width, int height, String title, Main game) {
        frame = new JFrame();
        MyMouseListener mouseClick = new MyMouseListener(game, this);
        //MyMouseListener mml = new MyMouseListener();
        frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // This basically allows the "X" button in the top right to close the program
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);     // Game starts in the middle of the screen, rather than top left
        frame.setVisible(true);

		frame.add(game);
        frame.addMouseListener((MouseInputListener) mouseClick);
		game.addMouseListener(mouseClick);
		game.start();
	}
}