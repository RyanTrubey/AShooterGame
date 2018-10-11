import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
int x;
int y;
int type;
Rectangle colBox;
Boolean isAlive = true;
public Player(int x, int y, int type) {
	this.x=x;
	this.y=y;
	this.type=type;
	colBox = new Rectangle(x, y, 50, 50);
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
public void updateColBox() {
	colBox.setBounds(x, y, 50, 50);
}
}
