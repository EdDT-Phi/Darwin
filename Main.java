import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
  static JFrame frame = new JFrame("Darwinism Beta");
  private static boolean animations = false, running = true;
  public static final int FRAME_Y = 900, FRAME_X = 1500, EDGE = 100;
  static JPanel pane;

  public static void main(String args[]) {
    if(args.length != 0) Debug.active = args[0].equals("--debug");
    init();
    running = true;
    run();
    end();
  }

  public static void init() {
    frame.setSize(FRAME_X, FRAME_Y);
    pane = new Display();
    frame.add(pane);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addMouseListener(new myML());
    frame.addKeyListener(new myKL());

    Ecosystem.init();
  }

  public static void run() {
    while (running) {
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      // repaint calls update
      pane.repaint();

      if(Ecosystem.getBlue().size() + Ecosystem.getRed().size() +Ecosystem.getYellow().size() +Ecosystem.getOrange().size() == 0) {
          init();
      }
    }
  }

  public static void update() {
    Ecosystem.update();
  }

  public static void end() {
    Debug.addValue(Ecosystem.getTotalEnergy());
    Debug.addValue(Ecosystem.getTotalOrgs());
    System.out.println("Dafuq");
    Debug.print();
  }

    public static void stop() {
        running = false;
    }
}