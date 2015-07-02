package main.java.framework;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.java.ecosystem.*;

public class Main {
	static JFrame f = new JFrame("Darwinism Beta");
	private static boolean animations = true, running = true;
	public static int FRAME_Y = 900, FRAME_X = 750, EDGE = 100;
	static JPanel pane;

	public static void main(String args[]) {
		setFrame();
		init();
		running = true;
		run();
		end();
	}

	public static void setFrame() {
		f.setSize(FRAME_X, FRAME_Y);
		pane = new Display();
		f.add(pane);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.addMouseListener(new myML());
		f.addKeyListener(new myKL());
	}

	public static void init() {
		Ecosystem.addOrgs();
	}

	public static void run() {
		while (running) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			update();
			pane.repaint();
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