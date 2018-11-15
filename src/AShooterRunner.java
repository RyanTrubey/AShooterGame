
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class AShooterRunner {
	static int fWidth = 1920;
	static int fHeight = 1080;
	JFrame frame;
	GamePanel panel;

	public static void main(String[] args) {
		AShooterRunner idk = new AShooterRunner();
		idk.setup();
	}

	public AShooterRunner() {

	}

	public void setup() {
		panel = new GamePanel();
		frame = new JFrame();
		frame.setTitle("AShooterGame");
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(fWidth, fHeight);
		panel.startGame();
		frame.addKeyListener(panel);
		frame.addMouseListener(panel);
	}
}
