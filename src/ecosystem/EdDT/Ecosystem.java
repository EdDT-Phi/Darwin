package ecosystem.EdDT;

import java.util.ArrayList;

import objects.EdDT.OrgBlue;
import objects.EdDT.OrgGreen;
import objects.EdDT.OrgOrange;
import objects.EdDT.OrgRed;
import objects.EdDT.OrgYellow;
import objects.EdDT.Organism;
import framework.EdDT.Debug;

public class Ecosystem {

	private static int energy = 1000000000;
	@SuppressWarnings("rawtypes")
	private static ArrayList<ArrayList> organisms = new ArrayList<ArrayList>();
	private static ArrayList<Organism> a;
	private static ArrayList<OrgGreen> gOrgs;
	private static ArrayList<OrgBlue> bOrgs;
	private static ArrayList<OrgRed> rOrgs;
	private static ArrayList<OrgYellow> yOrgs;
	private static ArrayList<OrgOrange> oOrgs;
	private static int[] amount = new int[] { 20, 200, 40, 50, 20 };

	public static void addOrgs() {

		a = new ArrayList<Organism>();

		rOrgs = new ArrayList<OrgRed>();
		bOrgs = new ArrayList<OrgBlue>();
		gOrgs = new ArrayList<OrgGreen>();
		yOrgs = new ArrayList<OrgYellow>();
		oOrgs = new ArrayList<OrgOrange>();

		organisms.add(gOrgs);
		organisms.add(bOrgs);
		organisms.add(rOrgs);
		organisms.add(yOrgs);
		organisms.add(oOrgs);

		for (int i = 0; i < amount[0]; i++) {
			gOrgs.add(new OrgGreen());
			energy -= LifeForce.lifeForces[0];
		}
		for (int i = 0; i < amount[1]; i++) {
			bOrgs.add(new OrgBlue());
			energy -= LifeForce.lifeForces[1];
		}
		for (int i = 0; i < amount[2]; i++) {
			rOrgs.add(new OrgRed());
			energy -= LifeForce.lifeForces[2];
		}
		for (int i = 0; i < amount[3]; i++) {
			yOrgs.add(new OrgYellow());
			energy -= LifeForce.lifeForces[3];
		}
		for (int i = 0; i < amount[4]; i++) {
			oOrgs.add(new OrgOrange());
			energy -= LifeForce.lifeForces[4];
		}
	}

	public static void update() {
		updateGreen();
		updateBlue();
		updateRed();
		updateYellow();
		updateOrange();

		updateAll();

		garbageDisposal();
		addNew();
	}

	public static void updateGreen() {
		while (energy > LifeForce.lifeForces[0]) {
			gOrgs.add(new OrgGreen());
			energy -= LifeForce.lifeForces[0];
		}
	}

	public static void updateBlue() {

		for (int i = 0; i < bOrgs.size(); i++) {
			OrgBlue g1 = bOrgs.get(i);
			g1.move();
			for (int j = i + 1; j < bOrgs.size(); j++) {
				OrgBlue g2 = bOrgs.get(j);
				if (g1.getDist(g2) < 40) {
					g1.closeTo(g2); // mating purposes
				}
			}
		}
	}

	public static void updateRed() {
		for (int i = 0; i < rOrgs.size(); i++) {
			OrgRed g1 = rOrgs.get(i);
			g1.move();
			for (int j = i + 1; j < rOrgs.size(); j++) {
				OrgRed g2 = rOrgs.get(j);
				if (g1.getDist(g2) < 40) {
					g1.closeTo(g2);
				}
			}
		}
	}

	public static void updateYellow() {
		for (int i = 0; i < yOrgs.size(); i++) {
			OrgYellow g1 = yOrgs.get(i);
			g1.move();
			for (int j = i + 1; j < yOrgs.size(); j++) {
				OrgYellow g2 = yOrgs.get(j);
				if (g1.getDist(g2) < 40) {
					g1.closeTo(g2);
				}
			}
		}
	}

	public static void updateOrange() {
		for (int i = 0; i < oOrgs.size(); i++) {
			OrgOrange g1 = oOrgs.get(i);
			g1.move();
			for (int j = i + 1; j < oOrgs.size(); j++) {
				OrgOrange g2 = oOrgs.get(j);
				if (g1.getDist(g2) < 40) {
					g1.closeTo(g2);
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static void updateAll() {
		for (ArrayList orgs : organisms) {
			for (int i = 0; i < orgs.size(); i++) {
				Organism org = (Organism) orgs.get(i);
				org.update();
			}

		}
	}

	public static void garbageDisposal() {
		for (@SuppressWarnings("rawtypes")
		ArrayList orgs : organisms) {
			for (int i = 0; i < orgs.size(); i++) {
				Organism g = (Organism) orgs.get(i);
				if (g.life.isDead()) {
					g.life.kill();
					orgs.remove(g);
					i--;
				}
			}
		}
	}

	public static void addOrg(Organism org) {
		a.add(org);
	}

	public static void addNew() {
		for (int i = 0; i < a.size(); i++) {
			Organism org = a.get(i);
			if (org.species == 2) {
				bOrgs.add((OrgBlue) org);
			} else if (org.species == 3) {
				rOrgs.add((OrgRed) org);
			} else if (org.species == 4) {
				yOrgs.add((OrgYellow) org);
			} else if (org.species == 5) {
				oOrgs.add((OrgOrange) org);
			} else {
				System.out.println("??");
			}
		}
		a.clear();
	}

	public static void giveBack(int n) {
		if (n < 0) {
			n = n / (n - n);
		}
		energy += n;
	}

	public static ArrayList<OrgGreen> getGreen() {
		return gOrgs;
	}

	public static ArrayList<OrgBlue> getBlue() {
		return bOrgs;
	}

	public static ArrayList<OrgRed> getRed() {
		return rOrgs;
	}

	public static ArrayList<OrgYellow> getYellow() {
		return yOrgs;
	}
	
	public static ArrayList<OrgOrange> getOrange(){
		return oOrgs;
	}

	@SuppressWarnings("rawtypes")
	public static ArrayList<ArrayList> getAll() {
		return organisms;
	}

	public static ArrayList<Organism> getQ() {
		return a;
	}

	public static int getEnergy() {
		return energy;
	}

	@SuppressWarnings("rawtypes")
	public static int getTotalEnergy() {
		if (Debug.active) {
			int total = energy;
			for (ArrayList orgs : organisms) {
				for (int i = 0; i < orgs.size(); i++) {
					Organism org = (Organism) orgs.get(i);
					total += org.life.get();
				}
			}
			return total;
		}
		return 0;
	}

	@SuppressWarnings("rawtypes")
	public static int getTotalOrgs() {
		if (Debug.active) {
			int total = 0;
			for (ArrayList orgs : organisms) {
				total += orgs.size();
			}
			return total;
		}
		return 0;
	}
}