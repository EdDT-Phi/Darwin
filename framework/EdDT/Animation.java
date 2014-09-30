package framework.EdDT;

import java.awt.Color;

public class Animation {
	int posX, posY, vX, vY, time;
	String text;
	Color color;

	public Animation(int px, int py, int vx, int vy, int t, String s, Color c) {
		posX = px;
		posY = py;
		vX = vx;
		vY = vy;
		time = t;
		text = s;
		color = c;
	}
	
	public Animation(int px, int py, int t, String s, Color c) {
		posX = px;
		posY = py;
		time = t;
		text = s;
		color = c;
	}
}
