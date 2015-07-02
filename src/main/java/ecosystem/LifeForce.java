package main.java.ecosystem;

import main.java.objects.*;

public class LifeForce {

	private int lifeForce, startEnergy;

	public LifeForce(int energy) {
		lifeForce = energy;
		startEnergy = energy;
	}

	public int get() {
		return lifeForce;
	}

	public void useForce(int n) {
		Ecosystem.giveBack(Math.min(n, lifeForce));
		lifeForce -= Math.min(n, lifeForce);
	}

	public boolean canBreed() {
		return lifeForce > startEnergy * 1.5;
	}

	public void useForceBreed() {
        // give half of startEnergy to new Organism
		lifeForce -= startEnergy / 2;

        // lose another fourth because breeding process
		Ecosystem.giveBack(startEnergy / 4);
		lifeForce -= startEnergy / 4;
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
