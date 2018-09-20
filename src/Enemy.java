import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
int x;
int y;
int direction;
Color color;
public Enemy(int x, int y, int direction, Color color) {
	this.x = x;
	this.y = y;
	this.direction = direction;
	this.color = color;
}
public void update() {
	x+=direction;
}
public void drawEnemy(Graphics g) {
	g.setColor(color);
	g.fillRect(x, y, 20, 20);
}
}
