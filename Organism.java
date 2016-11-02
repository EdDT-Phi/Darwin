import java.util.Random;

public abstract class Organism {

  protected int size, speed, species, attack, defense, posX, posY, eUse,
      sight = 100;
  protected double movX, movY;
  protected static final int GREEN = 0, BLUE = 1, RED = 2, YELLOW = 3, ORANGE = 4;
  protected static Random r = new Random();
  protected LifeForce life;

  public Organism() {
    posX = r.nextInt(Main.FRAME_X - Main.EDGE * 2) + Main.EDGE;
    posY = r.nextInt(Main.FRAME_Y - Main.EDGE * 2) + Main.EDGE;
  }

  protected void instantiate(int species) {
      life = new LifeForce(species);

      this.species = species;
      size = attack + defense;
      eUse = (speed + size) * 10;

      sight = 100;
  }

  protected void mixnmatch(Organism org1, Organism org2) {
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
  }


  protected void update() {
    double val = Math.sqrt(movX * movX + movY * movY);
    if (val > 0) {
      posX += movX * speed / val;
      posY += movY * speed / val;
    }


    if (posX < Main.EDGE) {
      // posX = Main.FRAME_X - Main.EDGE;
      // posX = Main.FRAME_X / 2;
      // posX += 1;
      life.kill();
    }
    if (posY < Main.EDGE) {
      // posY =  Main.FRAME_Y - Main.EDGE;
      life.kill();
      // posX = Main.FRAME_Y / 2;
      // posY += 1;
    }
    if (posX > Main.FRAME_X - Main.EDGE) {
      // posX = Main.EDGE;
      // posX = Main.FRAME_X / 2;
      life.kill();
      // posX -= 1;
    }
    if (posY > Main.FRAME_Y - Main.EDGE) {
      // posY = Main.EDGE;
      // posX = Main.FRAME_Y / 2;
      life.kill();
      // posY -= 1;
    }

    movX = 0;
    movY = 0;

    action(1);
  }

  protected void action(int times) {
    life.useForce(eUse * times);
  }

  protected int getDist(Organism org) {
    return (int) Math.sqrt((posX - org.posX) * (posX - org.posX)
        + (posY - org.posY) * (posY - org.posY));
  }

  protected void breed(Organism mate) {
    if (life.canBreed() && mate.life.canBreed()) {
      life.useForceBreed();
      mate.life.useForceBreed();
      baby(mate);
    }
  }

  protected int getDmg(Organism org) {
    return Math.max(0, org.life.get()
        * ((((attack - org.defense) * 100) / attack) + 10) / 100);
  }

  abstract protected void attack();
  abstract protected void move();
  abstract protected void closeTo(Organism org);
  abstract protected void baby(Organism org);
}