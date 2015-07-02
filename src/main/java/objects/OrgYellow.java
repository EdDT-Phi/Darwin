package main.java.objects;

import java.util.Random;

import main.java.ecosystem.*;

public class OrgYellow extends Organism {

	Organism target;

	public OrgYellow() {
		super();

		Random r = new Random();

		speed = r.nextInt(5) + 7;
		attack = r.nextInt(5) + 4;
		defense = r.nextInt(5) + 6;

		instantiate(Organism.YELLOW);
	}

	public OrgYellow(OrgYellow org1, OrgYellow org2) {

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

		instantiate(Organism.YELLOW);
	}

	public void attack() {
		int dmg = getDmg(target);
		life.getFrom(target, dmg);
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

		for (Organism org : Ecosystem.getOrange()) {
			if (getDist(org) < sight) {
				int x = posX - org.posX;
				int y = posY - org.posY;
				double d = Math.sqrt(x * x + y * y);

				movX += 10 * (x / d);
				movY += 10 * (y / d);
			}
		}

		int dist = 1000;
		for (Organism g : Ecosystem.getBlue()) {
			if (getDist(g) < dist) {
				target = g;
				dist = getDist(g);
			}
		}

		if (!(target == null)) {
			if (getDist(target) < 20) {
				attack();
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
		Ecosystem.addOrg(new OrgYellow(this, (OrgYellow) org));
	}
}