package gamez.nikaman.sprites;

import java.awt.image.BufferedImage;
   
import javax.imageio.ImageIO;

import gamez.nikaman.utils.GameContants;

public class OpponentPlayer extends CommonPlayer implements GameContants {
	
	private BufferedImage idleImages[]=new BufferedImage[3];
	private BufferedImage slashImages[]=new BufferedImage[4];
	private BufferedImage hitImages[]=new BufferedImage[3];
	
	
	private int force;
	public OpponentPlayer() throws Exception{
		x=SCREENWIDTH-300;
		y=GROUND;
		w=200;
		h=300;
		force=0;
		speed=0;
		playerImg=ImageIO.read(Player.class.getResource("zooorooo.png"));
		loadIdleImages();
		loadSlashImages();
		loadHitImages();
		
	}
	
	private void loadHitImages() {
		hitImages[0]= playerImg.getSubimage(832,306,161,198);
		hitImages[1]= playerImg.getSubimage(538,584,206,167);
		hitImages[2]= playerImg.getSubimage(791,594,206,157);
	}
	
	//public BufferedImage defaultImage() {
	//X = 308 Y = 54 Width = 174 Height = 193
		//return playerImg.getSubimage();
	//}
	
	private void loadIdleImages() {
		idleImages[0]= playerImg.getSubimage(308,54,174,193);
		idleImages[1]= playerImg.getSubimage(564,54,174,193);
		idleImages[2]= playerImg.getSubimage(820,52,174,195);
	}
	
	
	public void jump() {
		force=-50;
		y=y+force;
	}
	
	public void fall() {
		if(y + force  >GROUND) {
			return;
		}
		force=force+GRAVITY;
		y=y+force;
	} 
	
	
	private void loadSlashImages() {
		slashImages[0]= playerImg.getSubimage(803,828,194,186);
		slashImages[1]= playerImg.getSubimage(461,847,205,171);
		slashImages[2]= playerImg.getSubimage(17,856,235,161);
		slashImages[3]= playerImg.getSubimage(22,629,290,100);
		
		
		
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
	
	public BufferedImage printSlash() {
		
		if(imageIndex > 3) {
			imageIndex=0;
			currentMove=IDLE;
			isAttacking=false;
			
		}
		isAttacking=true;
		BufferedImage img = slashImages[imageIndex];
		imageIndex++;
		return img;
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
	
	
	
	@Override
	public BufferedImage defaultImage() {
		
		if(currentMove==SLASH) {
			return printSlash();
		}
		else if(currentMove==HIT) {
			return printHitImages();
		}
		else {
			return printIdle();
		
	}
		
	}
	

}
