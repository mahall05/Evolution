package Core;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

    private Main game;
    private boolean[] keyDown = new boolean[1];
    Main.STATE prevState = Main.STATE.Start;

    public KeyInput(Main game){
        this.game = game;

        for(int i = 0; i < keyDown.length; i++){
            keyDown[i] = false;
        }
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode(); // Gets the key code of the key that was pressed

        if(key == KeyEvent.VK_ESCAPE) {
            if(game.gameState != Main.STATE.Paused){
                prevState = game.gameState;
                game.gameState = Main.STATE.Paused;
            }else{
                game.gameState = prevState;
            }
        }
        
		//if(key == KeyEvent.VK_P) {game.pop.printPath();}
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_ESCAPE) {keyDown[0] = false;}
	}
	
    /*
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		
		this.game = game;
		
		keyDown[0]=false;
		keyDown[1]=false;
		keyDown[2]=false;
		keyDown[3]=false;
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();     // Sets "key" to a number binding that corresponds to the letter key that was just pressed
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {     // Only goes into the "if" statement if the object has the ID of "Player"
				//key events for player 1
				
				if(key == KeyEvent.VK_W) {tempObject.setVelY(-handler.spd); keyDown[0]=true; }     // Check if we're hitting W, and move up
				if(key == KeyEvent.VK_S) {tempObject.setVelY(handler.spd); keyDown[1]=true; }      // Check if we're hitting S, and move down
				if(key == KeyEvent.VK_D) {tempObject.setVelX(handler.spd); keyDown[2]=true; }      // Check if we're hitting D, and move right
				if(key == KeyEvent.VK_A) {tempObject.setVelX(-handler.spd); keyDown[3]=true; }     // Check if we're hitting A, and move left
			}
		}
		
		if (key == KeyEvent.VK_P) Game.paused = !Game.paused;
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		
		if(key == KeyEvent.VK_SPACE) {
			if(Game.gameState == STATE.Game) Game.gameState = STATE.Shop;
			else if(Game.gameState == STATE.Shop) Game.gameState = STATE.Game;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {     // Only goes into the "if" statement if the object has the ID of "Player"
				//key events for player 1
				
				if(key == KeyEvent.VK_W) keyDown[0]=false; 
				if(key == KeyEvent.VK_S) keyDown[1]=false;
				if(key == KeyEvent.VK_D) keyDown[2]=false; 
				if(key == KeyEvent.VK_A) keyDown[3]=false; 
				
				//vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				//horizontal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			}
		}
	}
    */
}
