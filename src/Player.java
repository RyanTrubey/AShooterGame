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
	g.drawImage(GamePanel.humanShip, x, y, 60, 60, null);
	} else {
		g.drawImage(GamePanel.alienShip, x, y, 60, 60, null);
	}
}
public int getX() {
	return x;
}
public int getY() {
	return y;
}
public void updateColBox() {
	colBox.setBounds(x, y, 60, 60);
}
}
