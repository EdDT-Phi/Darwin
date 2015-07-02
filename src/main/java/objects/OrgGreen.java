package main.java.objects;

import main.java.ecosystem.*;

public class OrgGreen extends Organism {

	public OrgGreen() {
		super();

		life = new LifeForce(1);

		species = 1;
		size = 5;
	}
}