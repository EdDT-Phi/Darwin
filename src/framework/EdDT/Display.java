package framework.EdDT;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import objects.EdDT.Organism;
import ecosystem.EdDT.Ecosystem;

@SuppressWarnings("serial")
public class Display extends JPanel {

	static ArrayList<Animation> anims = new ArrayList<Animation>();
	public static int total, lastT;

	@SuppressWarnings("rawtypes")
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 2500, 1000);

		total = 0;

		for (int i = 0; i < Ecosystem.getAll().size(); i++) {
			ArrayList orgs = Ecosystem.getAll().get(i);
			for (int j = 0; j < orgs.size(); j++) {
				Organism org = (Organism) orgs.get(j);
				total += org.life.get();
				switch (org.species) {
				case 1:
					g.setColor(Color.green);
					break;
				case 2:
					g.setColor(Color.blue);
					break;
				case 3:
					g.setColor(Color.red);
					break;
				case 4:
					g.setColor(Color.yellow);
					break;
				case 5:
					g.setColor(Color.orange);
					break;
				}
				drawOrg(org, g);
			}
		}

		for (int i = 0; i < anims.size(); i++) {
			Animation anim = anims.get(i);
			g.setColor(anim.color);
			g.drawString(anim.text, anim.posX, anim.posY);
			anim.time--;
			if (anim.time < 0) {
				anims.remove(anim);
				i--;
			}
		}
		drawStats(g);
	}

	public static void addAnim(Animation anim) {
		if (Main.animations) {
			anims.add(anim);
		}
	}

	public static void drawOrg(Organism org, Graphics g) {
		int size = 15;

		if (org.species == 1) {
			size = 5;
		}

		if (Debug.active) {
			if (org.species > 2)
				g.drawString(org.life.get() + "", org.posX - size / 2,
						org.posY - 15);
		}
		g.fillOval(org.posX - size / 2, org.posY - size / 2, size, size);
	}

	public static void drawStats(Graphics g) {
		int green = Ecosystem.getGreen().size();
		int blue = Ecosystem.getBlue().size();
		int red = Ecosystem.getRed().size();
		int yellow = Ecosystem.getYellow().size();
		int orange = Ecosystem.getOrange().size();

		g.setColor(Color.white);
		g.drawString("Organisms: " + (green + blue + red + yellow + orange), 5,
				10);
		g.drawString("Ecosystem: " + Ecosystem.getEnergy(), 5, 25);
		g.drawString("Total E: " + (total + Ecosystem.getEnergy()), 5, 40);

		g.setColor(Color.green);
		g.drawString("Green: " + green, 5, 55);
		g.setColor(Color.blue);
		g.drawString("Blue: " + blue, 5, 70);
		g.setColor(Color.red);
		g.drawString("Red: " + red, 5, 85); //
		g.setColor(Color.yellow);
		g.drawString("Yellow: " + yellow, 5, 100); //
		g.setColor(Color.orange);
		g.drawString("Orange: " + orange, 5, 115);
	}
}