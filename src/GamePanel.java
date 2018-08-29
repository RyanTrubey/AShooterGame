import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	Timer timer;
	final int menustate = 0;
	final int gamestate = 1;
	final int endstate = 2;
	int currentstate = menustate;

	public void paintComponent(Graphics g) {
		if(currentstate == menustate) {
			drawMenuState(g);
		} else if(currentstate == gamestate) {
			drawGameState(g);
		} else if(currentstate == endstate) {
			drawEndState(g);
		}
	}

	public GamePanel() {
		addKeyListener(this);
		timer = new Timer(1000 / 60, this);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER && currentstate != 2) {
			currentstate++;
		} else if(currentstate == 2) {
			currentstate = menustate;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}
	public void startGame() {
		timer.start();
	}
	public void drawMenuState(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, AShooterRunner.fWidth, AShooterRunner.fHeight);
	}
	public void drawGameState(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, AShooterRunner.fWidth, AShooterRunner.fHeight);
	}
	public void drawEndState(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, AShooterRunner.fWidth, AShooterRunner.fHeight);
	}
}
