package assignment4;
/* CRITTERS Critter1.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Kevin Brill
 * kjb27868
 * 16230
 * Grayson Barrett
 * gmb974
 * 16230
 * Slip days used: <0>
 * Spring 2017
 */

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
	
	public Critter1(){
		dir = Critter.getRandomInt(2) * 4;
		hasReproduced = false;
	}
	
	@Override
	public void doTimeStep() {
		run(dir);
		
		if (this.getEnergy() > 150 && !hasReproduced) {
			Critter1 crit = new Critter1();
			reproduce(crit, Critter.getRandomInt(8)); //reproduce randomly around the parent
		} 
		
		dir = Critter.getRandomInt(2) * 4; //0 or 4
		
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
