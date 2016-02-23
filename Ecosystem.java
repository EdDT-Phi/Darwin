import java.util.ArrayList;

public class Ecosystem {

  private static int energy;
  private static ArrayList<ArrayList<Organism>> organisms = new ArrayList<>();
  private static ArrayList<Organism> a;
  private static ArrayList<Organism> gOrgs, bOrgs, rOrgs, yOrgs, oOrgs;
  private static int[] amount = new int[] { 20, 200, 40, 50, 20 };
  private static int[] lifeForces = { 100000, 5000, 50000, 20000, 100000};


  public static void init() {

        energy = 100000000;

    a = new ArrayList<>();

    rOrgs = new ArrayList<>();
    bOrgs = new ArrayList<>();
    gOrgs = new ArrayList<>();
    yOrgs = new ArrayList<>();
    oOrgs = new ArrayList<>();

    organisms.add(gOrgs);
    organisms.add(bOrgs);
    organisms.add(rOrgs);
    organisms.add(yOrgs);
    organisms.add(oOrgs);

    for (int i = 0; i < amount[0]; i++) {
      gOrgs.add(new OrgGreen());
      energy -= getLifeForce(Organism.GREEN);
    }
    for (int i = 0; i < amount[1]; i++) {
      bOrgs.add(new OrgBlue());
      energy -= getLifeForce(Organism.BLUE);
    }
    for (int i = 0; i < amount[2]; i++) {
      rOrgs.add(new OrgRed());
      energy -= getLifeForce(Organism.RED);
    }
    for (int i = 0; i < amount[3]; i++) {
      yOrgs.add(new OrgYellow());
      energy -= getLifeForce(Organism.YELLOW);
    }
    for (int i = 0; i < amount[4]; i++) {
      oOrgs.add(new OrgOrange());
      energy -= getLifeForce(Organism.ORANGE);
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
    while (energy > getLifeForce(Organism.GREEN)) {
      gOrgs.add(new OrgGreen());
      energy -= getLifeForce(Organism.GREEN);
    }
  }

  public static void updateBlue() {
    for (int i = 0; i < bOrgs.size(); i++) {
      Organism g1 = bOrgs.get(i);
      g1.move();
      for (int j = i + 1; j < bOrgs.size(); j++) {
        Organism g2 = bOrgs.get(j);
        if (g1.getDist(g2) < 40) {
          g1.closeTo(g2); // mating purposes
        }
      }
    }
  }

  public static void updateRed() {
    for (int i = 0; i < rOrgs.size(); i++) {
      Organism g1 = rOrgs.get(i);
      g1.move();
      for (int j = i + 1; j < rOrgs.size(); j++) {
        Organism g2 = rOrgs.get(j);
        if (g1.getDist(g2) < 40) {
          g1.closeTo(g2);
        }
      }
    }
  }

  public static void updateYellow() {
    for (int i = 0; i < yOrgs.size(); i++) {
      Organism g1 = yOrgs.get(i);
      g1.move();
      for (int j = i + 1; j < yOrgs.size(); j++) {
        Organism g2 = yOrgs.get(j);
        if (g1.getDist(g2) < 40) {
          g1.closeTo(g2);
        }
      }
    }
  }

  public static void updateOrange() {
    for (int i = 0; i < oOrgs.size(); i++) {
      Organism g1 = oOrgs.get(i);
      g1.move();
      for (int j = i + 1; j < oOrgs.size(); j++) {
        Organism g2 = oOrgs.get(j);
        if (g1.getDist(g2) < 40) {
          g1.closeTo(g2);
        }
      }
    }
  }

  public static void updateAll() {
    for (ArrayList<Organism> orgs : organisms) {
      for (Organism org: orgs) {
        org.update();
      }

    }
  }

  public static void garbageDisposal() {
    for (ArrayList orgs : organisms) {
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
    for (Organism org: a) {
      switch (org.species) {
                case Organism.BLUE:
                    bOrgs.add(org);
                    break;
                case Organism.RED:
                    rOrgs.add(org);
                    break;
                case Organism.YELLOW:
                    yOrgs.add(org);
                    break;
                case Organism.ORANGE:
                    oOrgs.add(org);
                    break;
                default:
                    System.out.println(org.species + "??");
            }
    }
    a.clear();
  }

  public static void giveBack(int n) {
    if (n < 0) {
      System.out.println("Woah negative energy???");
            Main.stop();
    }
    energy += n;
  }

  public static ArrayList<Organism> getGreen() {
    return gOrgs;
  }

  public static ArrayList<Organism> getBlue() {
    return bOrgs;
  }

  public static ArrayList<Organism> getRed() {
    return rOrgs;
  }

  public static ArrayList<Organism> getYellow() {
    return yOrgs;
  }
  
  public static ArrayList<Organism> getOrange(){
    return oOrgs;
  }

  public static ArrayList<ArrayList<Organism>> getAll() {
    return organisms;
  }

  public static ArrayList<Organism> getQ() {
    return a;
  }

  public static int getEnergy() {
    return energy;
  }

  public static int getLifeForce(int species) {
    return lifeForces[species];
  }

  public static int getTotalEnergy() {
    if (Debug.active) {
      int total = energy;
      for (ArrayList<Organism> orgs : organisms) {
        for (Organism org: orgs) {
          total += org.life.get();
        }
      }
      return total;
    }
    return 0;
  }

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