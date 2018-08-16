import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
public void paintComponent(Graphics g) {
	g.setColor(Color.red);
	g.fillRect(0, 0, AShooterRunner.fWidth, AShooterRunner.fHeight);
}
}
