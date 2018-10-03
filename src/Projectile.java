import java.awt.Color;
import java.awt.Graphics;

public class Projectile {
int x;
int y;
String type;
public Projectile(int x, int y, String type) {
	this.x=x;
	this.y=y;
	this.type=type;
}
public void update() {
	if(type.equalsIgnoreCase("alien")) {
		x-=1;
	} else if(type.equalsIgnoreCase("human")) {
		x+=1;
	}
}
public void drawProjectile(Graphics g) {
	if(type.equalsIgnoreCase("alien")) {
		g.setColor(Color.magenta);
		g.fillRect(x, y, 10, 2);
	} else if(type.equalsIgnoreCase("human")) {
		g.setColor(Color.red);
		g.fillRect(x, y, 10, 2);
	}
}
}
