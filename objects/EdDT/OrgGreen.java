package objects.EdDT;

import ecosystem.EdDT.LifeForce;

public class OrgGreen extends Organism {

	public OrgGreen() {
		super();

		life = new LifeForce(1);

		species = 1;
		size = 5;
	}
}