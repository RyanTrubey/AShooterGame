import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
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
	static int optW = 300;
	static int optH = 50;
	static int startX = AShooterRunner.fWidth / 2 - 150;
	static int startY = AShooterRunner.fHeight / 2 - 150;
	static int startW = 300;
	static int startH = 50;
	static int backX = 50;
	static int backY = AShooterRunner.fHeight - 225;
	static int backW = startW - 100;
	static int adjustment = 25;
	int currentstate = menustate;
	int spawnTime = 120;
	int timerTick = 0;
	int teamX = AShooterRunner.fWidth / 2 - 150;
	int teamY = AShooterRunner.fHeight / 2 - 50;
	ObjectManager om;
	Random r = new Random();
	static final int human = 1;
	static final int alien = 2;
	static int team = human;
	Font textfont = new Font("Impact", Font.PLAIN, 40);
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	int projX;
	int projY;

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

	public GamePanel() {
		addKeyListener(this);
		timer = new Timer(1000 / 60, this);
		om = new ObjectManager();
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
	}

	public void startGame() {
		timer.start();
	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, AShooterRunner.fWidth, AShooterRunner.fHeight);
		g.setColor(Color.gray);
		g.fillRect(optX, optY, optW, optH);
		g.fillRect(startX, startY, startW, startH);
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
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, AShooterRunner.fWidth, AShooterRunner.fHeight);
	}

	public void drawOptionState(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, AShooterRunner.fWidth, AShooterRunner.fHeight);
		g.setColor(Color.gray);
		g.fillRect(backX, backY, backW, startH);
		g.setColor(Color.black);
		g.setFont(textfont);
		g.drawString("Back", backX + backW / 2 - 40, backY + startH / 2 + 15);
		g.setColor(Color.gray);
		g.fillRect(teamX, teamY, 300, 50);
		if (team == alien) {
			g.setColor(Color.black);
			g.drawString("Team Alien", AShooterRunner.fWidth / 2 - 90, AShooterRunner.fHeight / 2 - 10);
		} else if (team == human) {
			g.setColor(Color.black);
			g.drawString("Team Human", AShooterRunner.fWidth / 2 - 90, AShooterRunner.fHeight / 2 - 10);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (currentstate == menustate) {
			if (e.getX() > startX && e.getX() < startX + startW && e.getY() > startY + adjustment
					&& e.getY() < startY + GamePanel.startH + adjustment) {
				currentstate = gamestate;
				if (team == human) {
					Player player = new Player(25, AShooterRunner.fHeight / 2 - 50, human);
					om.addPlayer(player);
				} else {
					Player player = new Player(AShooterRunner.fWidth - 75, AShooterRunner.fHeight / 2 - 50, alien);
					om.addPlayer(player);
				}
			}
			if (e.getX() > optX && e.getX() < optX + optW && e.getY() > optY + adjustment
					&& e.getY() < optY + optH + adjustment) {
				currentstate = optionstate;
			}
		} else if (currentstate == optionstate) {
			if (e.getX() > backX && e.getX() < backX + backW && e.getY() > backY + adjustment
					&& e.getY() < backY + startH + adjustment) {
				currentstate = menustate;
				System.out.println(team);
			} else if (e.getX() > teamX && e.getX() < teamX + 300 && e.getY() > teamY + adjustment
					&& e.getY() < teamY + 50 + adjustment) {
				System.out.println("Something" + team);
				if (team == human) {
					team = alien;
				} else {
					team = human;
				}
			}
		} else if (currentstate == gamestate) {
			if (timerTick % 60 == 0) {
				if (team == human) {

				}
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
