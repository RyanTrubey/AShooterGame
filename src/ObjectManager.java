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
	Random random;
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
				Enemy e = new Enemy(GamePanel.tempW - 100,
						r.nextInt(GamePanel.tempH - 50) + GamePanel.adjustment, -3, "alien", 1);
				addEnemy(e);
			} else if (GamePanel.team == GamePanel.alien) {
				Random r = new Random();
				Enemy e = new Enemy(50, r.nextInt(GamePanel.tempH - 50) + GamePanel.adjustment, 1, "human", 2);
				addEnemy(e);
			}

		}
	}
	public void spawnBigEnemy() {
		if(GamePanel.team == GamePanel.human) {
			Random r = new Random();
			int num = r.nextInt(3);
			if(num == 1) {
				BigEnemy b = new BigEnemy(GamePanel.tempW - 100, GamePanel.tempH/3 + GamePanel.adjustment, "alien", 11);
				addBigEnemy(b);
			} else if(num == 2) {
				BigEnemy b = new BigEnemy(GamePanel.tempW - 100, GamePanel.tempH/2 + GamePanel.adjustment, "alien", 11);
				addBigEnemy(b);
			} else {
				BigEnemy b = new BigEnemy(GamePanel.tempW - 100, GamePanel.tempH - GamePanel.tempH/3 + GamePanel.adjustment, "alien", 11);
				addBigEnemy(b);
			}
		} else {
			Random r = new Random();
			int num = r.nextInt(3);
			if(num == 1) {
				BigEnemy b = new BigEnemy(50, GamePanel.tempH/3 + GamePanel.adjustment, "human", 21);
				addBigEnemy(b);
			} else if(num == 2) {
				BigEnemy b = new BigEnemy(50, GamePanel.tempH/2 + GamePanel.adjustment, "human", 21);
				addBigEnemy(b);
			} else {
				BigEnemy b = new BigEnemy(50, GamePanel.tempH - GamePanel.tempH/3 + GamePanel.adjustment, "human", 21);
				addBigEnemy(b);
			}
		}
	}

	public void clear() {
		eList.clear();
		pList.clear();
		bList.clear();
	}

	public void addPlayer(Player player) {
		this.player = player;
	}

	public void movePlayer(String direction) {
		if (direction.equals("up")) {
			player.y -= playerspeed;
			if(player.y < 0) {
				player.y=GamePanel.tempH-25;
			}
		}
		if (direction.equals("down")) {
			player.y += playerspeed;
			if(player.y > GamePanel.tempH-50) {
				player.y= -25;
			}
		}
		if (direction.equals("left") && player.x > 0) {
			player.x -= playerspeed;
		}
		if (direction.equals("right") && player.x < GamePanel.tempW-50) {
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
					if(p.type.equalsIgnoreCase("alien")) {
					p.isAlive = false;
					} else {
						if(random.nextInt(2) == 0) {
							p.y-=50;
							p.yVelocity = -4;
						} else {
							p.yVelocity = 4;
							p.y+=50;
						}
					}
					e.health--;
					if(e.health <= 0) {
						GamePanel.score++;
					}
				}
			}
		}
		for (BigEnemy b : bList) {
			for(Projectile p : pList) {
				if(p.colBox.intersects(b.colBox)) {
					if(p.type.equalsIgnoreCase("alien")) {
					p.isAlive = false;
					} else {
						if(random.nextInt(2) == 0) {
							p.y-=160;
							p.yVelocity = -2;
						} else {
							p.y+=160;
							p.yVelocity = 2;
						}
					}
					b.health--;
					if(b.health <= 0) {
						GamePanel.score+=2;
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
				if(e.x+20 > GamePanel.tempW-GamePanel.tempW/8) {
					e.isAlive = false;
					health-=1;
				}
			} else if(e.direction < 0) {
				if(e.x < GamePanel.tempW/8) {
					e.isAlive = false;
					health-=1;
				}
			}
		}
		for (BigEnemy b : bList) {
			if(b.type.equals("human")) {
				if(b.x+b.w > GamePanel.tempW - GamePanel.tempW/8) {
					health-=3;
					b.isAlive = false;
				}
			} else {
				if(b.x < GamePanel.tempW/8) {
					health-=3;
					b.isAlive = false;
				}
			}
		}
		for (Projectile p : pList) {
			if(p.type.equalsIgnoreCase("human")) {
				if(p.x > GamePanel.tempW) {
					p.isAlive = false;
				}
			} else {
				if(p.x < 0) {
					p.isAlive = false;
				}
			}
		}
		if(player.type == GamePanel.human) {
			if(player.x+50 > GamePanel.tempW-GamePanel.tempW/8) {
				player.isAlive = false;
				if(GamePanel.score > GamePanel.highscore) {
					GamePanel.highscore = GamePanel.score;
				}
			}
		} else if(player.type == GamePanel.alien) {
			if(player.x < GamePanel.tempW/8) {
				player.isAlive = false;
				if(GamePanel.score > GamePanel.highscore) {
					GamePanel.highscore = GamePanel.score;
				}
			}
		}
		if(health <= 0) {
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
		for(int i = 0; i < bList.size(); i++) {
			if(bList.get(i).isAlive == false) {
				bList.remove(bList.get(i));
			}
		}
	}
}
