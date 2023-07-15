package gamez.nikaman.utils;

public interface GameContants {
	String TITLE = configReader.getValue("game.title");
	int SCREENWIDTH=Integer.parseInt(configReader.getValue("game.width"));
	int SCREENHEIGHT=Integer.parseInt(configReader.getValue("game.height"));
	int GROUND = Integer.parseInt(configReader.getValue("game.height"))-400;
	int SPEED=10;
	int IDLE=1;
	int WALK=2;
	int KICK=3;
	int GRAVITY=9;
	int SLASH=5;
	int HIT=4;
	int MAX_HEALTH=500;
			
}
