import java.util.ArrayList;

public class Debug {
  public static boolean active = false;
  private static ArrayList<ArrayList<Double>> values = new ArrayList<ArrayList<Double>>();
  private static int current = -1;

  public static void addValue(double d) {
    if (current == -1) {
      endRow();
    }
    values.get(current).add(d);
  }

  public static void endRow() {
    values.add(new ArrayList<>());
    current++;
  }

  public static void print() {
    for (int i = 0; i < values.size(); i++) {
      for (int j = 0; j < values.get(i).size(); j++) {
        System.out.print(values.get(i).get(j) + "\t");
      }
      System.out.println();
    }
  }
}
