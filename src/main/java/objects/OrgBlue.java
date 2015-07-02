package main.java.objects;

import java.util.Random;

import main.java.ecosystem.*;

public class OrgBlue extends Organism {

	Organism target;

	public OrgBlue() {
		super();

		Random r = new Random();

		speed = r.nextInt(5) + 2;
		attack = r.nextInt(3) + 1;
		defense = r.nextInt(3) + 1;

		instantiate(Organism.BLUE);
	}

	public OrgBlue(OrgBlue org1, OrgBlue org2) {
		Random r = new Random();

		posX = (org1.posX + org2.posX) / 2;
		posY = (org1.posY + org2.posY) / 2;

		if (r.nextInt(2) == 1) {
			speed = org1.speed;
		} else {
			speed = org2.speed;
		}
		if (r.nextInt(2) == 1) {
			defense = org1.defense;
		} else {
			defense = org2.defense;
		}
		if (r.nextInt(2) == 1) {
			attack = org1.attack;
		} else {
			attack = org2.attack;
		}
		instantiate(Organism.BLUE);
	}

	public void closeTo(Organism org) {
		if (species == org.species) {
			breed(org);

        }
	}

	public void move() {

		for (Organism org : Ecosystem.getRed()) {
			if (getDist(org) < 50) {
				int x = posX - org.posX;
				int y = posY - org.posY;
				double d = Math.sqrt(x * x + y * y);

				movX += 10 * (x / d);
				movY += 10 * (y / d);
			}
		}

		for (Organism org : Ecosystem.getYellow()) {
			if (getDist(org) < sight) {
				int x = posX - org.posX;
				int y = posY - org.posY;
				double d = Math.sqrt(x * x + y * y);

				movX += 10 * (x / d);
				movY += 10 * (y / d);
			}
		}
		int dist = 1000;
		for (Organism g : Ecosystem.getGreen()) {
			if (getDist(g) < dist) {
				target = g;
				dist = getDist(g);
			}
		}

		if (!(target == null)) {
			if (getDist(target) < 20) {
				life.getFrom(target, 1000);
			} else {
				int x = posX - target.posX;
				int y = posY - target.posY;

				double d = Math.sqrt(x * x + y * y);

				movX = -(x / d);
				movY = -(y / d);
			}

			if (target.life.get() <= 0) {
				target = null;
			}
		}
	}

	public void baby(Organism org) {
		Ecosystem.addOrg(new OrgBlue(this, (OrgBlue) org));
	}
}