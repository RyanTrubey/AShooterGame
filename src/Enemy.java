import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy {
int x;
int y;
int direction;
int health;
Color color;
Rectangle colBox;
Boolean isAlive = true;
public Enemy(int x, int y, int direction, Color color, int health) {
	this.x = x;
	this.y = y;
	this.direction = direction;
	this.color = color;
	this.health = health;
	colBox = new Rectangle(x, y, 20, 20);
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
	colBox.setBounds(x, y, 20, 20);
}
public void drawEnemy(Graphics g) {
	g.setColor(color);
	g.fillRect(x, y, 20, 20);
}
}
