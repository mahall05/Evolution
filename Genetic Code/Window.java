import javax.swing.JFrame;
import java.awt.*;

public class Window extends Canvas{
	
	public Window(int width, int height, String title, Main game) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // This basically allows the "X" button in the top right to close the program
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);     // Game starts in the middle of the screen, rather than top left
		frame.add(game);     // Adds the game to the frame
		frame.setVisible(true);
		game.start();     // Start the game
	}

}