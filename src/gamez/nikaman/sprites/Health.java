package gamez.nikaman.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Health extends CommonPlayer{
	Color color;
	String playerName;
	public Health(int x ,String playerName) {
		this.x=x;
		y=20;
		w=MAX_HEALTH;
		h=50;
		health=MAX_HEALTH;
		this.playerName=playerName;
		
	}
	
	public void setHealth() {
		if(health>0) {
			health=health-(int)(MAX_HEALTH * 0.10);
		}
		
	}
	
	@Override
	public BufferedImage defaultImage() {
		return null;
	}
	
	public void printHealth(Graphics pen) {
		pen.setColor(Color.RED);
		pen.fillRect(x, y, w, h);
		pen.setColor(Color.GREEN);
		pen.fillRect(x, y, health, h);
		pen.setColor(Color.GRAY);
		pen.setFont(new Font("times",Font.BOLD,50));
		pen.drawString(playerName,x,y+h+40);
	}
}
