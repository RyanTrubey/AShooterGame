import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener {
	Timer timer;
	final int menustate = 0;
	final int gamestate = 1;
	final int endstate = 2;
	final int optionstate = 3;
	static int optX = AShooterRunner.fWidth/2-150;
	static int optY = AShooterRunner.fHeight/2-25;
	static int optW = 300;
	static int optH = 50;
	static int startX = AShooterRunner.fWidth/2-150;
	static int startY = AShooterRunner.fHeight/2-150;
	static int startW = 300;
	static int startH = 50;
	int adjustment = 25;
	int currentstate = menustate;
	Font textfont = new Font("Impact", Font.PLAIN, 40);
	public void paintComponent(Graphics g) {
		if(currentstate == menustate) {
			drawMenuState(g);
		} else if(currentstate == gamestate) {
			drawGameState(g);
		} else if(currentstate == endstate) {
			drawEndState(g);
		} else if(currentstate == optionstate) {
			drawOptionState(g);
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
		if(e.getKeyCode() == KeyEvent.VK_ENTER && currentstate != 3) {
			currentstate++;
		} else if(currentstate == 3) {
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
		g.setColor(Color.gray);
		g.fillRect(optX, optY, optW, optH);
		g.fillRect(startX, startY, startW, startH);
		g.setColor(Color.black);
		g.setFont(textfont);
		g.drawString("Start", AShooterRunner.fWidth/2-50, AShooterRunner.fHeight/2-110);
		g.drawString("Options", AShooterRunner.fWidth/2-70, AShooterRunner.fHeight/2+15);
	}
	public void drawGameState(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, AShooterRunner.fWidth, AShooterRunner.fHeight);
	}
	public void drawEndState(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, AShooterRunner.fWidth, AShooterRunner.fHeight);
	}
	public void drawOptionState(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, AShooterRunner.fWidth, AShooterRunner.fHeight);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(currentstate == menustate) {
		if(e.getX() > startX && e.getX() < startX+startW && e.getY() > startY+adjustment && e.getY() < startY+GamePanel.startH+adjustment) {
			currentstate=gamestate;
		}
		if(e.getX() > optX && e.getX() < optX+optW && e.getY() > optY+adjustment && e.getY() < optY+optH+adjustment) {
			currentstate=optionstate;
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
