public class LifeForce {

  private int lifeForce, species;

  public LifeForce(int species) {
    lifeForce = Ecosystem.getLifeForce(species);
    this.species = species;
  }

  public int get() {
    return lifeForce;
  }

  public void useForce(int n) {
    Ecosystem.giveBack(Math.min(n, lifeForce));
    lifeForce -= Math.min(n, lifeForce);
  }

  public boolean canBreed() {
    return lifeForce > Ecosystem.getLifeForce(species) * 1.5;
  }

  public void useForceBreed() {
    // give half of startEnergy to new Organism
    lifeForce -= Ecosystem.getLifeForce(species) / 2;

    // lose another fourth because breeding process
    Ecosystem.giveBack(Ecosystem.getLifeForce(species) / 4);
    lifeForce -= Ecosystem.getLifeForce(species) / 4;
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
