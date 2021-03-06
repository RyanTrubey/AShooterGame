import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile {
int x;
int y;
Rectangle colBox;
String type;
Boolean isAlive = true;
int yVelocity;
public Projectile(int x, int y, String type) {
	this.x=x;
	this.y=y;
	this.type=type;
	colBox = new Rectangle(x, y, 20, 4);
}
public void update() {
	y+=yVelocity;
	if(type.equalsIgnoreCase("alien")) {
		x-=5;
	} else if(type.equalsIgnoreCase("human")) {
		x+=5;
	}
	if(type.equalsIgnoreCase("human")) {
	colBox.setBounds(x, y, 20, 8);
	} else {
		colBox.setBounds(x, y, 20, 2);
	}
}
public void drawProjectile(Graphics g) {
	if(type.equalsIgnoreCase("alien")) {
		g.setColor(Color.magenta);
		g.fillRect(x, y, 20, 2);
	} else if(type.equalsIgnoreCase("human")) {
		g.setColor(Color.red);
		g.fillRect(x, y, 20, 8);
	}
}
}
