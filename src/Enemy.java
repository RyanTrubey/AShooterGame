import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Enemy {
int x;
int y;
int direction;
int health;
String team;
Rectangle colBox;
Boolean isAlive = true;
BufferedImage humanEnemy;
BufferedImage alienEnemy;
public Enemy(int x, int y, int direction, String team, int health) {
	this.x = x;
	this.y = y;
	this.direction = direction;
	this.team = team;
	this.health = health;
	colBox = new Rectangle(x, y, 20, 20);
	try {
		humanEnemy = ImageIO.read(this.getClass().getResourceAsStream("HumanEnemy.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		alienEnemy = ImageIO.read(this.getClass().getResourceAsStream("AlienEnemy.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void update() {
	x+=direction;
	if(health <= 0) {
		isAlive = false;
	}
	if(direction < 0) {
		if(x+1 < GamePanel.frame.getWidth()/8) {
			x=GamePanel.frame.getWidth() - 100;
			Random r = new Random();
			y = r.nextInt(GamePanel.frame.getHeight() - 50);
		}
	} else if(direction > 0) {
		if(x+19 > GamePanel.frame.getWidth()-GamePanel.frame.getWidth()/8) {
			x=80;
			Random r = new Random();
			y = r.nextInt(GamePanel.frame.getHeight() - 50);
		}
	}
	colBox.setBounds(x, y, 45, 45);
}
public void drawEnemy(Graphics g) {
	if(team.equals("human")) {
		g.drawImage(humanEnemy, x, y, 45, 45, null);
	} else {
		g.drawImage(alienEnemy, x, y, 45, 45, null);
	}
}
}
