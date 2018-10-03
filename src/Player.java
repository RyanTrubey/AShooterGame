import java.awt.Color;
import java.awt.Graphics;

public class Player {
int x;
int y;
int type;
public Player(int x, int y, int type) {
	this.x=x;
	this.y=y;
	this.type=type;
}
public void drawPlayer(Graphics g) {
	if(type == GamePanel.human) {
	g.setColor(Color.red);
	g.fillRect(x, y, 50, 50);
	} else {
		g.setColor(Color.cyan);
		g.fillRect(x, y, 50, 50);
	}
}
public int getX() {
	return x;
}
public int getY() {
	return y;
}
}
