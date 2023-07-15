package gamez.nikaman.sprites;

import java.awt.Graphics;   
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import gamez.nikaman.utils.GameContants;

public class Player extends CommonPlayer implements GameContants {
	
	private BufferedImage idleImages[]=new BufferedImage[3];
	private BufferedImage walkImages[]=new BufferedImage[1];
	private BufferedImage kickImages[]=new BufferedImage[5];
	private BufferedImage hitImages[]=new BufferedImage[3];
	
	private int force;
	public Player() throws Exception{
		x=150;
		y=GROUND;
		w=125;
		h=300;
		speed=0;
		force=0;
		playerImg=ImageIO.read(Player.class.getResource("luffy.png"));
		loadIdleImages(); 
		loadWalkImages();
		loadKickImages();
		loadHitImages();
	}
	
	private void loadHitImages() {
		hitImages[0]= playerImg.getSubimage(456,559,112,164);
		hitImages[1]= playerImg.getSubimage(249,594,99,127);
		hitImages[2]= playerImg.getSubimage(58,593,97,127);
	}
	
	private void loadIdleImages() {
		idleImages[0]= playerImg.getSubimage(58,32,85,176);
		idleImages[1]= playerImg.getSubimage(249,31,87,177);
		idleImages[2]= playerImg.getSubimage(442,31,86,176);
	}
	
	private void loadWalkImages() {
		walkImages[0]= playerImg.getSubimage(62,307,156,158);
		
	}
	
	private void loadKickImages() {
		kickImages[0]= playerImg.getSubimage(49,862,140,143);
		kickImages[1]= playerImg.getSubimage(760,844,249,159);
		kickImages[2]= playerImg.getSubimage(760,620,212,160);
		kickImages[3]= playerImg.getSubimage(760,396,187,158);
		kickImages[4]= playerImg.getSubimage(258,857,120,147);
		//kickImages[5]= playerImg.getSubimage(49,862,140,143);
	}
	
	
	public void jump() {
		force=-40;
		y=y+force;
	}
	
	public void fall() {
		if(y + force  >GROUND) {
			return;
		}
		force=force+GRAVITY;
		y=y+force;
	}
	
	
	
	public BufferedImage printIdle() {
		isAttacking=false;
				if(imageIndex > 2) {
					imageIndex=0;
				}
				BufferedImage img = idleImages[imageIndex];
				imageIndex++;
				return img;
		
	}
	
	public BufferedImage printWalk() {
		isAttacking=false;
				if(imageIndex > 0) {
					imageIndex=0;
					currentMove=IDLE;
				
				}
				BufferedImage img = walkImages[imageIndex];
				imageIndex++;
				return img;
	}		
	
	public BufferedImage printKick() {
		
				if(imageIndex > 4) {
					imageIndex=0;
					currentMove=IDLE;
					isAttacking=false;
				}
				isAttacking=true;
				BufferedImage img = kickImages[imageIndex];
				imageIndex++;
				return img;
	}
	
public BufferedImage printHitImages() {
		
		if(imageIndex > 2) {
			imageIndex=0;
			currentMove=IDLE;
			
		}
		
		BufferedImage img = hitImages[imageIndex];
		imageIndex++;
		return img;
}
	
	
	
	
	@Override
	public BufferedImage defaultImage() {
		if(currentMove==WALK) {
			return printWalk();
		}
		else if(currentMove==KICK) {
			return printKick();
		}
		else if(currentMove==HIT) {
			return printHitImages();
		}
		else {
			return printIdle();
		}
	}
	  
}




















/*kickImages[0]= playerImg.getSubimage(49,862,140,143);
kickImages[1]= playerImg.getSubimage(258,857,120,147);
kickImages[2]= playerImg.getSubimage(760,172,156,159);
kickImages[3]= playerImg.getSubimage(760,396,187,158);
kickImages[4]= playerImg.getSubimage(760,620,212,160);
kickImages[5]= playerImg.getSubimage(760,844,249,159);
*/