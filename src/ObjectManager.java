import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<Enemy> eList = new ArrayList<Enemy>();
	int enemyNumber = 10;
	Player player;
	public void addEnemy(Enemy e) {
		eList.add(e);
	}
	
	public void draw(Graphics g) {
		for (Enemy a : eList) {
			a.drawEnemy(g);
		}
		player.drawPlayer(g);
	}

	public void update() {
		for (Enemy a : eList) {
			a.update();
		}
		
	}

	public void spawn() {
		if (eList.size() < enemyNumber) {
			if (GamePanel.team == GamePanel.human) {
				Random r = new Random();
				Enemy e = new Enemy(AShooterRunner.fWidth - 100,
						r.nextInt(AShooterRunner.fHeight - 50) + GamePanel.adjustment, -1, Color.white);
				addEnemy(e);
			} else if (GamePanel.team == GamePanel.alien) {
				Random r = new Random();
				Enemy e = new Enemy(50, r.nextInt(AShooterRunner.fHeight - 50) + GamePanel.adjustment, 1, Color.yellow);
				addEnemy(e);
			}

		}

	}
	public void clear() {
		eList.clear();
	}
	public void addPlayer(Player player) {
		this.player = player;
	}
	public void movePlayer(String direction) {
		if(direction.equals("up")) {
			player.y-=5;
		}
		if(direction.equals("down")) {
			player.y+=5;
		}
		if(direction.equals("left")) {
			player.x-=5;
		}
		if(direction.equals("right")) {
			player.x+=5;
		}
	}
}
