import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
ArrayList<Alien> aList = new ArrayList<Alien>();
public void addAlien(Alien a) {
	aList.add(a);
}
public void draw(Graphics g) {
	for(Alien a : aList) {
		a.drawAlien(g);
	}
}
}
