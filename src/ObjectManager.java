import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<Enemy> eList = new ArrayList<Enemy>();
	ArrayList<Projectile> pList = new ArrayList<Projectile>();
	int enemyNumber = 10;
	Player player;

	public void addEnemy(Enemy e) {
		eList.add(e);
	}

	public void addProjectile(Projectile p) {
		pList.add(p);
	}

	public void draw(Graphics g) {
		for (Enemy a : eList) {
			a.drawEnemy(g);
		}
		for (Projectile p : pList) {
			p.drawProjectile(g);
		}
		player.drawPlayer(g);
	}

	public void update() {
		for (Enemy a : eList) {
			a.update();
		}
		for (Projectile p : pList) {
			p.update();
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
		if (direction.equals("up")) {
			player.y -= 5;
		}
		if (direction.equals("down")) {
			player.y += 5;
		}
		if (direction.equals("left") && player.x > 0) {
			player.x -= 5;
		}
		if (direction.equals("right") && player.x < AShooterRunner.fWidth-50) {
			player.x += 5;
		}
		player.updateColBox();
	}

	public void createProjectile() {
		if (GamePanel.team == GamePanel.human) {
			Projectile projectile = new Projectile(player.x + 50, player.y + 25, "human");
			pList.add(projectile);
		} else if (GamePanel.team == GamePanel.alien) {
			Projectile projectile = new Projectile(player.x - 20, player.y + 25, "alien");
			pList.add(projectile);
		}
	}

	public void checkCollision() {
		for (Projectile p : pList) {
			for (Enemy e : eList) {
				if (p.colBox.intersects(e.colBox)) {
					e.isAlive = false;
					p.isAlive = false;
					GamePanel.score++;
				}
			}
		}
		for (Enemy e : eList) {
			if(e.colBox.intersects(player.colBox)) {
				player.isAlive = false;
				e.isAlive = false;
				if(GamePanel.score > GamePanel.highscore) {
					GamePanel.highscore = GamePanel.score;
				}
			}
		}
	}
	public void cleanObjects() {
		for(int i = 0; i < pList.size(); i++) {
			if(pList.get(i).isAlive == false) {
				pList.remove(pList.get(i));
			}
		}
		for(int i = 0; i < eList.size(); i++) {
			if(eList.get(i).isAlive == false) {
				eList.remove(eList.get(i));
			}
		}
	}
}
