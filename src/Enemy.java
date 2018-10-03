import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

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
	if(direction < 0) {
		if(x+20 < 0) {
			x=AShooterRunner.fWidth - 100;
			Random r = new Random();
			y = r.nextInt(AShooterRunner.fHeight - 50);
		}
	} else if(direction > 0) {
		if(x > AShooterRunner.fWidth) {
			x=80;
			Random r = new Random();
			y = r.nextInt(AShooterRunner.fHeight - 50);
		}
	}
}
public void drawEnemy(Graphics g) {
	g.setColor(color);
	g.fillRect(x, y, 20, 20);
}
}
