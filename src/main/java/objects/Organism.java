package main.java.objects;

import java.util.Random;

import main.java.ecosystem.*;
import main.java.framework.*;

public class Organism {

	public int size, speed, species, attack, defense, posX, posY, eUse,
			sight = 100;
	double movX, movY;
	public LifeForce life;
    public static final int GREEN = 0, BLUE = 1, RED = 2, YELLOW = 3, ORANGE = 4;
    public static int[] lifeForces = new int[] { 100000, 5000, 50000, 20000, 100000};

    public Organism() {

		Random r = new Random();

		posX = r.nextInt(Main.FRAME_X - Main.EDGE * 2) + Main.EDGE;
		posY = r.nextInt(Main.FRAME_Y - Main.EDGE * 2) + Main.EDGE;
	}
    public void instantiate(int species) {
        life = new LifeForce(lifeForces[species]);

        this.species = species;

        size = attack + defense;
        eUse = (speed + size) * 10;

        sight = 100;
    }


	public void update() {
		double val = Math.sqrt(movX * movX + movY * movY);
		if (val > 0) {
			posX += movX * speed / val;
			posY += movY * speed / val;
		}


        action(1);
		if (posX < Main.EDGE) {
            // posX = Main.FRAME_X - Main.EDGE;
            //posX = Main.FRAME_X / 2;
            life.kill();
		}
		if (posY < Main.EDGE) {
			// posY =  Main.FRAME_Y - Main.EDGE;
             life.kill();
            // posX = Main.FRAME_Y / 2;
		}
		if (posX > Main.FRAME_X - Main.EDGE) {
			// posX = Main.EDGE;
//            posX = Main.FRAME_X / 2;
            life.kill();
		}
		if (posY > Main.FRAME_Y - Main.EDGE) {
//			posY = Main.EDGE;
//            posX = Main.FRAME_Y / 2;
            life.kill();
		}

		movX = 0;
		movY = 0;
	}

	public void action(int times) {
		life.useForce(eUse * times);
	}

	public int getDist(Organism org) {
		return (int) Math.sqrt((posX - org.posX) * (posX - org.posX)
				+ (posY - org.posY) * (posY - org.posY));
	}

	public void breed(Organism org) {
		if (life.canBreed() && org.life.canBreed()) {
			life.useForceBreed();
			org.life.useForceBreed();
			baby(org);
		}

        double distX = Math.abs(posX-org.posX);
        if(distX < 10){
            posX += 5 * (posX-org.posX)/distX;
        }

        double distY = Math.abs(posY-org.posY);
        if(distY < 10){
            posY += 5 * (posY-org.posY)/distY;
        }
	}

	public int getDmg(Organism org) {
		return Math.max(0, org.life.get()
				* ((((attack - org.defense) * 100) / attack) + 10) / 100);
	}

	public void attack() {
		System.out.println("Oops!! Organism attack?? Nu-uh");
	}

	public void move() {
		System.out.println("Oops!! Organism move?? Nu-uh");
	}

	public void closeTo(Organism org) {
		System.out.println("Oops!! Organism closeTo?? Nu-uh");
	}

	public void baby(Organism org) {
		System.out.println("Oops!! Organism baby?? Nu-uh");
	}
}