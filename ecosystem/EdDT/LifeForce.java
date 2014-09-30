package ecosystem.EdDT;

import objects.EdDT.Organism;

public class LifeForce {

	public static int[] lifeForces = new int[] { 1000000, 5000, 50000, 20000, 100000};
	private int lifeForce, species;

	public LifeForce(int species) {
		lifeForce = lifeForces[species - 1];
		this.species = species - 1;
	}

	public int get() {
		return lifeForce;
	}

	public void useForce(int n) {
		Ecosystem.giveBack(Math.min(n, lifeForce));
		lifeForce -= Math.min(n, lifeForce);
	}

	public boolean canBreed() {
		return lifeForce > lifeForces[species] * 1.5
				&& Ecosystem.getAll().get(species).size() + Ecosystem.getQ().size() < 1000;
	}

	public void useForceBreed() {
		lifeForce -= lifeForces[species] / 2;
		Ecosystem.giveBack(lifeForces[species] / 4);
		lifeForce -= lifeForces[species] / 4;
	}

	public void getFrom(Organism from, int n) {
		lifeForce += Math.min(n, from.life.lifeForce);
		from.life.lifeForce -= Math.min(n, from.life.lifeForce);
	}

	public boolean isDead() {
		return lifeForce <= 0;
	}

	public void kill() {
		Ecosystem.giveBack(lifeForce);
		lifeForce = 0;
	}
}
