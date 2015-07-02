package main.java.objects;

import java.util.Random;

import main.java.ecosystem.*;

public class OrgOrange extends Organism {

	private Organism target;

	public OrgOrange() {
		super();

		Random r = new Random();

		speed = r.nextInt(5) + 10;
		attack = r.nextInt(5) + 10;
		defense = r.nextInt(5) + 2;

		instantiate();
	}

	public OrgOrange(OrgOrange org1, OrgOrange org2) {

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

		instantiate();
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
		// if (target == null) {
		int dist = 1000;
		for (Organism g : Ecosystem.getYellow()) {
			if (getDist(g) < dist) {
				target = g;
				dist = getDist(g);
			}
		}
		for (Organism g : Ecosystem.getRed()) {
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
		Ecosystem.addOrg(new OrgOrange(this, (OrgOrange) org));
	}
}