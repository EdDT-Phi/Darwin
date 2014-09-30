package objects.EdDT;

import java.util.Random;

import ecosystem.EdDT.LifeForce;
import framework.EdDT.Main;

public class Organism {

	public int size, speed, species, attack, defense, posX, posY, eUse,
			sight = 100;
	double movX, movY;
	public LifeForce life;

	public Organism() {

		Random r = new Random();

		posX = r.nextInt(Main.FRAME_X - Main.EDGE * 2) + Main.EDGE;
		posY = r.nextInt(Main.FRAME_Y - Main.EDGE * 2) + Main.EDGE;
	}

	public void update() {
		double val = Math.sqrt(movX * movX + movY * movY);
		if (val > 0) {
			posX += movX * speed / val;
			posY += movY * speed / val;
		}
		if (posX < Main.EDGE) {
			posX = Main.EDGE;
		}
		if (posY < Main.EDGE) {
			posY = Main.EDGE;
		}
		if (posX > Main.FRAME_X - Main.EDGE) {
			posX = Main.FRAME_X - Main.EDGE;
		}
		if (posY > Main.FRAME_Y - Main.EDGE) {
			posY = Main.FRAME_Y - Main.EDGE;
		}
		action(1);

		movX = 0;
		movY = 0;
	}

	public void action(int times) {
		life.useForce(eUse * times);
	}

	public int getDist(Organism org) {
		return (int) Math.sqrt((posX - org.posX) * (posX - org.posX)
				+ (posY - org.posY) * (posY - org.posY));
	}

	public void breed(Organism org) {
		if (life.canBreed() && org.life.canBreed()) {
			life.useForceBreed();
			org.life.useForceBreed();
			baby(org);
		}
	}

	public int getDmg(Organism org) {
		return Math.max(0, org.life.get()
				* ((((attack - org.defense) * 100) / attack) + 10) / 100);
	}

	public void attack() {
		System.out.println("Oops!! Organism attack?? Nu-uh");
	}

	public void move(Organism org) {
		System.out.println("Oops!! Organism move?? Nu-uh");
	}

	public void closeTo(Organism org) {
		System.out.println("Oops!! Organism closeTo?? Nu-uh");
	}

	public void baby(Organism org) {
		System.out.println("Oops!! Organism baby?? Nu-uh");
	}
}