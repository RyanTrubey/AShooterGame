import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener {
	Timer timer;
	final int menustate = 0;
	final int gamestate = 1;
	final int endstate = 2;
	final int optionstate = 3;
	static int optX = AShooterRunner.fWidth / 2 - 150;
	static int optY = AShooterRunner.fHeight / 2 - 25;
	static int boxW = 300;
	static int boxH = 50;
	static int startX = AShooterRunner.fWidth / 2 - 150;
	static int startY = AShooterRunner.fHeight / 2 - 150;
	static int backX = AShooterRunner.fWidth / 15;
	static int backY = AShooterRunner.fHeight - AShooterRunner.fHeight / 6;
	static int backW = boxW - 100;
	static int adjustment = 25;
	int teamX = AShooterRunner.fWidth / 2 - 150;
	int teamY = AShooterRunner.fHeight / 2 - 75;
	int screenBtnX = AShooterRunner.fWidth / 2 - 200;
	int screenBtnY = AShooterRunner.fHeight / 2;
	int screenBtnW = 400;
	int menuX = AShooterRunner.fWidth / 2 - boxW / 2;
	int menuY = AShooterRunner.fHeight / 2 - boxH / 2 - 50;
	static int tempW = AShooterRunner.fWidth;
	static int tempH = AShooterRunner.fHeight;
	int upX = screenBtnX + screenBtnW + 15;
	int downX = screenBtnX - 65;
	int applyX = backX;
	int applyY = backY - 75;
	int applyW = backW;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int currentstate = menustate;
	int spawnTime = 120;
	int timerTick = 0;
	ObjectManager om;
	Random r = new Random();
	static final int human = 1;
	static final int alien = 2;
	static int team = human;
	Font textfont = new Font("Impact", Font.PLAIN, 40);
	Font gamefont = new Font("Impact", Font.PLAIN, 30);
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	int projX;
	int projY;
	Date projectileTime = new Date();
	int shootTime = 250;
	static int score = 0;
	static int highscore = 0;
	static JFrame frame;
	static BufferedImage humanShip;
	static BufferedImage alienShip;
	static BufferedImage titleScreen;

	public void paintComponent(Graphics g) {
		if (currentstate == menustate) {
			drawMenuState(g);
		} else if (currentstate == gamestate) {
			drawGameState(g);
		} else if (currentstate == endstate) {
			drawEndState(g);
		} else if (currentstate == optionstate) {
			drawOptionState(g);
		}
	}

	public GamePanel(JFrame frame) {
		addKeyListener(this);
		this.frame = frame;
		timer = new Timer(1000 / 60, this);
		om = new ObjectManager();
		try {
			humanShip = ImageIO.read(this.getClass().getResourceAsStream("Human Ship.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			alienShip = ImageIO.read(this.getClass().getResourceAsStream("Alien Ship.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			titleScreen = ImageIO.read(this.getClass().getResourceAsStream("Title_Screen.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER && currentstate != optionstate) {
			currentstate++;
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER && currentstate == optionstate) {
			currentstate = menustate;
		}
		if (e.getKeyCode() == KeyEvent.VK_W && currentstate == gamestate) {
			up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S && currentstate == gamestate) {
			down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A && currentstate == gamestate) {
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D && currentstate == gamestate) {
			right = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_W && currentstate == gamestate) {
			up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S && currentstate == gamestate) {
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A && currentstate == gamestate) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D && currentstate == gamestate) {
			right = false;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timerTick++;
		if (timerTick % spawnTime == 0 && currentstate == gamestate) {
			om.spawn();
		}
		if (currentstate != gamestate) {
			om.clear();
		}
		repaint();
		if (up == true) {
			om.movePlayer("up");
		}
		if (down == true) {
			om.movePlayer("down");
		}
		if (left == true) {
			om.movePlayer("left");
		}
		if (right == true) {
			om.movePlayer("right");
		}
		if (currentstate == gamestate) {
			om.checkCollision();
			om.cleanObjects();
			if (currentstate == gamestate && !om.player.isAlive) {
				end();
			}
		}
		if(score > 10) {
			om.enemyNumber = 15;
			spawnTime = 60;
		} else if(score > 20) {
			om.enemyNumber = 20;
			spawnTime = 30;
		} else if(score > 30) {
			om.enemyNumber = 30;
			spawnTime = 20;
		} else if(score > 50) {
			om.enemyNumber = 60;
			spawnTime = 1;
		}
	}

	public void startGame() {
		timer.start();
	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(0, 0, tempW, tempH);
		g.drawImage(titleScreen, 0, -20, tempW, tempH, null);
		g.setColor(Color.gray);
		g.fillRect(optX, optY, boxW, boxH);
		g.fillRect(startX, startY, boxW, boxH);
		g.setColor(Color.black);
		g.setFont(textfont);
		g.drawString("Start", AShooterRunner.fWidth / 2 - 50, AShooterRunner.fHeight / 2 - 110);
		g.drawString("Options", AShooterRunner.fWidth / 2 - 70, AShooterRunner.fHeight / 2 + 15);
	}

	public void drawGameState(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, AShooterRunner.fWidth, AShooterRunner.fHeight);
		g.setColor(new Color(0, 150, 0));
		g.fillRect(0, 0, AShooterRunner.fWidth / 8, AShooterRunner.fHeight);
		g.setColor(new Color(100, 0, 125));
		g.fillRect(AShooterRunner.fWidth - AShooterRunner.fWidth / 8, 0, AShooterRunner.fWidth / 8,
				AShooterRunner.fHeight);
		om.update();
		om.draw(g);
		g.setFont(gamefont);
		g.setColor(Color.white);
		g.drawString("Score: " + score, tempW/2-50, 60);
		g.drawString("Base Health: " + om.health + "/" + om.totalhealth, tempW/2-100, 100);
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, AShooterRunner.fWidth, AShooterRunner.fHeight);
		g.setColor(Color.gray);
		g.fillRect(menuX, menuY, boxW, boxH);
		g.setColor(Color.black);
		g.setFont(textfont);
		g.drawString("Back to Menu", AShooterRunner.fWidth / 2 - 110, AShooterRunner.fHeight / 2 - 35);
		g.drawString("You Died", AShooterRunner.fWidth / 2 - 75, AShooterRunner.fHeight / 2 - 175);
		g.drawString("Score: " + score, AShooterRunner.fWidth / 2 - 250, AShooterRunner.fHeight / 2 - 100);
		g.drawString("Highscore: " + highscore, AShooterRunner.fWidth / 2 + 50, AShooterRunner.fHeight / 2 - 100);
	}

	public void drawOptionState(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, AShooterRunner.fWidth, AShooterRunner.fHeight);
		g.setColor(Color.gray);
		g.fillRect(backX, backY, backW, boxH);
		g.fillRect(teamX, teamY, boxW, boxH);
		g.fillRect(screenBtnX, screenBtnY, screenBtnW, boxH);
		g.fillRect(upX, screenBtnY, boxH, boxH);
		g.fillRect(downX, screenBtnY, boxH, boxH);
		g.fillRect(applyX, applyY, applyW, boxH);
		g.setColor(Color.black);
		g.setFont(textfont);
		g.drawString("<", downX+10, screenBtnY+boxH-10);
		g.drawString(">", upX+10, screenBtnY+boxH-10);
		g.drawString(tempW + "x" + tempH, screenBtnX + 100, screenBtnY + 40);
		g.drawString("Back", backX + 50, backY + 40);
		g.drawString("Apply", applyX + 50, applyY + 40);
		if (team == alien) {
			g.setColor(Color.black);
			g.drawString("Team Alien", teamX + 35, teamY + 40);
		} else if (team == human) {
			g.setColor(Color.black);
			g.drawString("Team Human", teamX + 35, teamY + 40);
		}
	}

	public void end() {
		om.eList.clear();
		om.pList.clear();
		currentstate = endstate;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (currentstate == menustate) {
			if (e.getX() > startX && e.getX() < startX + boxW && e.getY() > startY + adjustment
					&& e.getY() < startY + boxH + adjustment) {
				e.consume();
				up = down = right = left = false;
				score = 0;
				currentstate = gamestate;
				if (team == human) {
					Player player = new Player(25, AShooterRunner.fHeight / 2 - 50, human);
					om.addPlayer(player);
					om.health = 10;
					om.totalhealth = om.health;
					shootTime = 250;
					om.playerspeed = 3;
				} else {
					Player player = new Player(AShooterRunner.fWidth - 75, AShooterRunner.fHeight / 2 - 50, alien);
					om.addPlayer(player);
					om.health = 5;
					om.playerspeed = 7;
					om.totalhealth = om.health;
					shootTime = 50;
				}
			}
			if (e.getX() > optX && e.getX() < optX + boxW && e.getY() > optY + adjustment
					&& e.getY() < optY + boxH + adjustment) {
				currentstate = optionstate;
			}
		} else if (currentstate == optionstate) {
			if (e.getX() > backX && e.getX() < backX + backW && e.getY() > backY + adjustment
					&& e.getY() < backY + boxH + adjustment) {
				currentstate = menustate;
			} else if (e.getX() > teamX && e.getX() < teamX + 300 && e.getY() > teamY + adjustment
					&& e.getY() < teamY + 50 + adjustment) {
				if (team == human) {
					team = alien;
				} else {
					team = human;
				}
			} else if (e.getX() > upX && e.getX() < upX + boxH && e.getY() > screenBtnY + adjustment
					&& e.getY() < screenBtnY + boxH + adjustment) {
				if (tempW == 1280) {
					tempW = 1920;
					tempH = 1080;
				} else if (tempW == 1920) {
					tempW = 2560;
					tempH = 1440;
				} else if (tempW == 2560) {
					tempW = 3840;
					tempH = 2160;
				} else if (tempW == 3840) {
					tempW = 1280;
					tempH = 720;
				}
			} else if (e.getX() > downX && e.getX() < downX + boxH && e.getY() > screenBtnY + adjustment
					&& e.getY() < screenBtnY + boxH + adjustment) {
				if (tempW == 3840) {
					tempW = 2560;
					tempH = 1440;
				} else if (tempW == 2560) {
					tempW = 1920;
					tempH = 1080;
				} else if (tempW == 1920) {
					tempW = 1280;
					tempH = 720;
				} else if (tempW == 1280) {
					tempW = 3840;
					tempH = 2160;
				}
			} else if (e.getX() > applyX && e.getX() < applyX + applyW && e.getY() > applyY + adjustment
					&& e.getY() < applyY + boxH + adjustment) {
				if (tempW > screenSize.width || tempH > screenSize.height) {
					tempW = screenSize.width;
					tempH = screenSize.height;
				}
				AShooterRunner.fWidth = tempW;
				AShooterRunner.fHeight = tempH;
				optX = AShooterRunner.fWidth / 2 - 150;
				optY = AShooterRunner.fHeight / 2 - 25;
				boxW = 300;
				boxH = 50;
				startX = AShooterRunner.fWidth / 2 - 150;
				startY = AShooterRunner.fHeight / 2 - 150;
				backX = AShooterRunner.fWidth / 15;
				backY = AShooterRunner.fHeight - AShooterRunner.fHeight / 6;
				backW = boxW - 100;
				adjustment = 25;
				teamX = AShooterRunner.fWidth / 2 - 150;
				teamY = AShooterRunner.fHeight / 2 - 75;
				screenBtnX = AShooterRunner.fWidth / 2 - 200;
				screenBtnY = AShooterRunner.fHeight / 2;
				screenBtnW = 400;
				tempW = AShooterRunner.fWidth;
				tempH = AShooterRunner.fHeight;
				upX = screenBtnX + screenBtnW + 15;
				downX = screenBtnX - 65;
				applyX = backX;
				applyY = backY - 75;
				applyW = backW;
				frame.setSize(tempW, tempH);
				menuX = AShooterRunner.fWidth / 2 - boxW / 2;
				menuY = AShooterRunner.fHeight / 2 - boxH / 2 - 50;

			}
		} else if (currentstate == gamestate) {
			Date currentTime = new Date();
			if (currentTime.getTime() - projectileTime.getTime() >= shootTime) {
				om.createProjectile();
				projectileTime = currentTime;
			}
		} else if (currentstate == endstate) {
			if (e.getX() > menuX && e.getX() < menuX + boxW && e.getY() > menuY + adjustment
					&& e.getY() < menuY + boxH + adjustment) {

				currentstate = menustate;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
