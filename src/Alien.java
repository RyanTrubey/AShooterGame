import java.awt.Color;
import java.awt.Graphics;

public class Alien {
int x;
int y;
public Alien(int x, int y) {
	this.x=x;
	this.y=y;
}
public void drawAlien(Graphics g) {
	System.out.println(x + y);
	g.setColor(Color.white);
	g.fillRect(x, y, 20, 20);
}
}
