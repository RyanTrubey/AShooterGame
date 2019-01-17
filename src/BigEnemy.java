import java.awt.Graphics;
import java.awt.Rectangle;

public class BigEnemy {
int x;
int y;
int health;
String type;
Boolean isAlive = true;
Rectangle colBox;
int w = 150;
int h = 150;
public BigEnemy(int x, int y, String type, int health) {
	this.x = x;
	this.y = y;
	this.type = type;
	this.health = health;
	colBox = new Rectangle(x, y, w, h);
}
public void update() {
	if(health <= 0) {
		isAlive = false;
	}
	if(type.equalsIgnoreCase("human")) {
		x+=1;
	} else if(type.equalsIgnoreCase("alien")) {
		x-=1;
	}
	colBox.setBounds(x, y, w, h);
}
public void drawBigEnemy(Graphics g) {
	if(type.equalsIgnoreCase("human")) {
		g.drawImage(GamePanel.humanShip, x, y, w, h, null);
	} else {
		g.drawImage(GamePanel.alienShip, x, y, w, h, null);
	}
}
}
