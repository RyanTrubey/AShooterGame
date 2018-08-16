import javax.swing.JFrame;

public class AShooterRunner {
static int fWidth = 1280;
static int fHeight = 720;
JFrame frame;
GamePanel panel;
public static void main(String[] args) {
	new AShooterRunner();
}
public AShooterRunner() {
	panel = new GamePanel();
	frame = new JFrame();
	frame.setTitle("AShooterGame");
	frame.add(panel);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setSize(fWidth, fHeight);
}

}
