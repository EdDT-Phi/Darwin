package framework.EdDT;

import java.util.ArrayList;

public class Debug {
	public static boolean active = false;
	@SuppressWarnings("rawtypes")
	private static ArrayList<ArrayList> values = new ArrayList<ArrayList>();
	private static int current = -1;

	@SuppressWarnings("unchecked")
	public static void addValue(Object o) {
		if (current == -1) {
			endRow();
		}
		values.get(current).add(o);
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
