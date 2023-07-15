package gamez.nikaman.gaming;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JWindow;

import gamez.nikaman.utils.GameContants;

public class SplashScreen extends JFrame implements GameContants{
	private JLabel label=new JLabel();
	public SplashScreen() {
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(SCREENWIDTH,SCREENHEIGHT);
		//setExtendedState(MAXIMIZED_BOTH);
 		setLocationRelativeTo(null);
		setTitle(TITLE); 
		
		Icon icon = new ImageIcon(SplashScreen.class.getResource("cc.png"));
		label.setIcon(icon);
		this.add(label);
		setVisible(true);
		try {
			Thread.sleep(10000);
			GameScreen obj =new GameScreen();
			setVisible(false);
			dispose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SplashScreen screen = new SplashScreen();
	}

}
