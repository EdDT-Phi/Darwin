public class OrgGreen extends Organism {

  public OrgGreen() {
    super();
    instantiate(Organism.GREEN);
    size = 2;
  }

  protected void baby(Organism mate) {}
  protected void closeTo(Organism mate) {}
  protected void move() {}
  protected void attack() {}
}