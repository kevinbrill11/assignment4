package assignment4;

/**
 * Made by Kevin Brill
 * 
 * Critter description: Extremely aggressive critter that
 * lives fast and dies hard, just like Forrest Gump.
 * 
 * This critter is always running, just like Forrest Gump
 * 
 * The direction of the critter's movement is randomly selected
 * between either 0 or 4, just like how Forrest Gump ran from the 
 * west coast to the east coast.
 * 
 * This critter always fights
 * 
 * This critter will only reproduce if its energy is greater than 150,
 * and only once in its lifetime, just like Forrest Gump.
 * 
 *
 */
public class Critter1 extends Critter{
	boolean hasReproduced;
	int dir;
	
	@Override
	public void doTimeStep() {
		dir = Critter.getRandomInt(2) * 4;
		run(dir);
		
	}

	@Override
	public boolean fight(String opponent) {
			return true;
	}
	
	@Override
	public String toString() {
		return "1";
	}

}
