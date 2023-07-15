package gamez.nikaman.gaming;

import javax.swing.JFrame;

import gamez.nikaman.utils.GameContants;

public class GameScreen extends JFrame implements GameContants{
	
	public GameScreen() throws Exception {
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(SCREENWIDTH,SCREENHEIGHT);
 		setLocationRelativeTo(null);
		setTitle(TITLE); 
		GameBoard board = new GameBoard();
		add(board);
		setVisible(true); 
	}
	
	

}
