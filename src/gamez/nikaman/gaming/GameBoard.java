package gamez.nikaman.gaming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import gamez.nikaman.sprites.Health;
import gamez.nikaman.sprites.OpponentPlayer;
import gamez.nikaman.sprites.Player;
import gamez.nikaman.utils.GameContants;

public class GameBoard extends JPanel implements GameContants {
	BufferedImage bgImage;
	private Player player;
	private OpponentPlayer oppPlayer;
	private Timer timer;
	private Health luffy_health;
	private Health zoro_health;
	private boolean isGameOver;
	
	public GameBoard() throws Exception {
		player = new Player();
		oppPlayer=new OpponentPlayer();
		 
		setFocusable(true);
		loadBackground();
		bindEvents();
		loadHealth();
		gameLoop();
		
	}
	
	
	
	
	private void gameLoop() {
		timer = new Timer(300,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint(); 
				oppPlayer.fall();
				player.fall();
				collision();
			}
		});
		timer.start();     
	}
	
	
	private void loadHealth() {
		luffy_health=new Health(30,"MONKEY D. LUFFY");
		zoro_health=new Health(SCREENWIDTH-600,"RORONOA ZORO");
	}
	
	private void printHealth(Graphics pen) {
		luffy_health.printHealth(pen);
		zoro_health.printHealth(pen);
	}
	
	private boolean isCollide() {
		int xDistance = Math.abs(player.getX() - oppPlayer.getX());
		int yDistance = Math.abs(player.getY() - oppPlayer.getY());
		int maxH=Math.max(player.getH(), oppPlayer.getH());
		int maxW=Math.max(player.getW(), oppPlayer.getW());
		return xDistance <= maxW && yDistance <= maxH;
	}
	
	private void collision() {
		if(isCollide()) {
			if(player.isAttacking() && oppPlayer.isAttacking()) {
				
			}
			else if(player.isAttacking()) {
				// oppPlayer.printHitImages();		
				oppPlayer.setCurrentMove(HIT);
				zoro_health.setHealth();
            }
			else if(oppPlayer.isAttacking()) {
				player.setCurrentMove(HIT);
				luffy_health.setHealth();
			}
			
			if(luffy_health.getHealth() <= 0 || zoro_health.getHealth() <=0) {
				isGameOver=true;
			}
			//System.out.println("Collision");
			player.setCollide(true);
			player.setSpeed(0);
			oppPlayer.setCollide(true);
			oppPlayer.setSpeed(0);
		}
		else {
			player.setCollide(false);
			//System.out.println("NO Collision");
			player.setSpeed(SPEED);
			oppPlayer.setCollide(false);
			oppPlayer.setSpeed(SPEED);
		}
	}
	
	
	private void printGameOver(Graphics pen) {
		pen.setColor(Color.BLACK);
		pen.setFont(new Font("times",Font.BOLD,80));
		pen.drawString("GAME OVER", SCREENWIDTH/2-300,SCREENHEIGHT/2-150);
	}
	
	@Override
	public void paintComponent(Graphics pen){
		//System.out.println("Paint Component...");
		paintBackground(pen);
		player.paintPlayer(pen);
		oppPlayer.paintPlayer(pen);
		printHealth(pen);
		if(isGameOver) {
			printGameOver(pen);
			timer.stop();
		}
		
	}
    
	private void paintBackground(Graphics pen) {
		pen.drawImage(bgImage,0,0,SCREENWIDTH, SCREENHEIGHT,null);
	/*pen.setColor(Color.GREEN);
	pen.fillRect(100,10,500,50);
	pen.setColor(Color.GREEN);
	pen.fillRect(900,10,500,50);*/
      }
	
	void bindEvents() {
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				  //System.out.println("Key Typed :" + e.getKeyCode());
				  
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				//System.out.println("Key Released :" + e.getKeyCode());
				player.setSpeed(0);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("Key Pressed :" + e.getKeyCode());
				if(e.getKeyCode()== KeyEvent.VK_LEFT) {
					  player.setSpeed(-SPEED);
					  player.move();
					  player.setCollide(false);
					  player.setCurrentMove(WALK);
					  repaint();
				  }
				  else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
					  player.setSpeed(SPEED);
					  player.move();
					  player.setCurrentMove(WALK);
					  repaint();
				  }
				  else if(e.getKeyCode()== KeyEvent.VK_K) {
					  player.setCurrentMove(KICK);
					  player.setAttacking(true);
					  repaint();
				  }
				
				  else if(e.getKeyCode()==KeyEvent.VK_SHIFT) {
					  player.jump();
					  repaint();
				  }
				
				  else if(e.getKeyCode()==KeyEvent.VK_Y) {
					  oppPlayer.setCurrentMove(SLASH);
					  player.setAttacking(true);
					  repaint();
				  }
				
				  else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
					  oppPlayer.jump();
					  repaint();
				  }
					  
				
				if(e.getKeyCode()== KeyEvent.VK_A) {
					oppPlayer.setSpeed(-SPEED);
					oppPlayer.move();
					
					  repaint();
				  }
				  else if(e.getKeyCode()==KeyEvent.VK_D){
					  oppPlayer.setSpeed(SPEED);
					  oppPlayer.move();
					  oppPlayer.setCollide(false);
					  repaint();
				  }		
				
			}
		});
	}
	private void loadBackground() {
		try {
			bgImage=ImageIO.read(GameBoard.class.getResource("newback.jpg"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this,"something Went Wrong");
			System.out.println("Failed to load background image...");
			System.exit(0);  
		}
	}
     
   
}
