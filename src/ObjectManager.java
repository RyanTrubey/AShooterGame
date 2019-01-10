import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<Enemy> eList = new ArrayList<Enemy>();
	ArrayList<Projectile> pList = new ArrayList<Projectile>();
	ArrayList<BigEnemy> bList = new ArrayList<BigEnemy>();
	int enemyNumber = 10;
	Player player;
	int health;
	int totalhealth;
	int playerspeed;
	public void addEnemy(Enemy e) {
		eList.add(e);
	}
	public void addBigEnemy(BigEnemy b) {
		bList.add(b);
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
		for (BigEnemy b : bList) {
			b.drawBigEnemy(g);
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
		for (BigEnemy b : bList) {
			b.update();
		}
	}

	public void spawn() {
		if (eList.size() < enemyNumber) {
			if (GamePanel.team == GamePanel.human) {
				Random r = new Random();
				Enemy e = new Enemy(GamePanel.frame.getWidth() - 100,
						r.nextInt(GamePanel.frame.getHeight() - 50) + GamePanel.adjustment, -3, "alien", 1);
				addEnemy(e);
			} else if (GamePanel.team == GamePanel.alien) {
				Random r = new Random();
				Enemy e = new Enemy(50, r.nextInt(GamePanel.frame.getHeight() - 50) + GamePanel.adjustment, 1, "human", 2);
				addEnemy(e);
			}

		}
	}
	public void spawnBigEnemy() {
		if(GamePanel.team == GamePanel.human) {
			Random r = new Random();
			int num = r.nextInt(3);
			if(num == 1) {
				BigEnemy b = new BigEnemy(GamePanel.frame.getWidth() - 100, GamePanel.frame.getHeight()/3 + GamePanel.adjustment, "alien", 5);
				addBigEnemy(b);
			} else if(num == 2) {
				BigEnemy b = new BigEnemy(GamePanel.frame.getWidth() - 100, GamePanel.frame.getHeight()/2 + GamePanel.adjustment, "alien", 5);
				addBigEnemy(b);
			} else {
				BigEnemy b = new BigEnemy(GamePanel.frame.getWidth() - 100, GamePanel.frame.getHeight() - GamePanel.frame.getHeight()/3 + GamePanel.adjustment, "alien", 5);
				addBigEnemy(b);
			}
		} else {
			Random r = new Random();
			int num = r.nextInt(3);
			if(num == 1) {
				BigEnemy b = new BigEnemy(50, GamePanel.frame.getHeight()/3 + GamePanel.adjustment, "human", 5);
				addBigEnemy(b);
			} else if(num == 2) {
				BigEnemy b = new BigEnemy(50, GamePanel.frame.getHeight()/2 + GamePanel.adjustment, "human", 5);
				addBigEnemy(b);
			} else {
				BigEnemy b = new BigEnemy(50, GamePanel.frame.getHeight() - GamePanel.frame.getHeight()/3 + GamePanel.adjustment, "human", 5);
				addBigEnemy(b);
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
			player.y -= playerspeed;
			if(player.y < 0) {
				player.y=GamePanel.frame.getHeight()-25;
			}
		}
		if (direction.equals("down")) {
			player.y += playerspeed;
			if(player.y > GamePanel.frame.getHeight()-50) {
				player.y= -25;
			}
		}
		if (direction.equals("left") && player.x > 0) {
			player.x -= playerspeed;
		}
		if (direction.equals("right") && player.x < GamePanel.frame.getWidth()-50) {
			player.x += playerspeed;
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
					p.isAlive = false;
					e.health--;
					if(e.health <= 0) {
						GamePanel.score++;
					}
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
			if(e.direction > 0) {
				if(e.x+20 > GamePanel.frame.getWidth()-GamePanel.frame.getWidth()/8) {
					health-=1;
				}
			} else if(e.direction < 0) {
				if(e.x < GamePanel.frame.getWidth()/8) {
					health-=1;
				}
			}
		}
		if(player.type == GamePanel.human) {
			if(player.x+50 > GamePanel.frame.getWidth()-GamePanel.frame.getWidth()/8) {
				player.isAlive = false;
				if(GamePanel.score > GamePanel.highscore) {
					GamePanel.highscore = GamePanel.score;
				}
			}
		} else if(player.type == GamePanel.alien) {
			if(player.x < GamePanel.frame.getWidth()/8) {
				player.isAlive = false;
				if(GamePanel.score > GamePanel.highscore) {
					GamePanel.highscore = GamePanel.score;
				}
			}
		}
		if(health == 0) {
			player.isAlive = false;
			if(GamePanel.score > GamePanel.highscore) {
				GamePanel.highscore = GamePanel.score;
			}
		}
		for(BigEnemy b : bList) {
			if(b.colBox.intersects(player.colBox)) {
				b.isAlive = false;
				player.isAlive = false;
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
