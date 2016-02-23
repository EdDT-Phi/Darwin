import java.util.Random;

public class OrgBlue extends Organism {

  Organism target;

  public OrgBlue() {
    super();

    Random r = new Random();

    speed = r.nextInt(3);
    attack = r.nextInt(3) + 1;
    defense = r.nextInt(3) + 1;

    instantiate(Organism.BLUE);
  }

  public OrgBlue(OrgBlue org1, OrgBlue org2) {
    mixnmatch(org1, org2);
    instantiate(Organism.BLUE);
  }

  public void closeTo(Organism org) {
    if (species == org.species) {
      breed(org);
    }
  }


  // O(n^2) D:
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