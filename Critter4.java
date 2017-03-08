package assignment4;
/* CRITTERS Critter4.java
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

/** Made by Kevin Brill 
 * 
 * This critter doesn't move if it's more than 10 time steps old, 
 * 	but will walk away from fights with Critter1 critters (Forrest Gump). 
 *  It will, however, always fight Grayson's critters.
 *  
 *  When fleeing from a fight, will move upward.
 * 
 * 
 *
 */
public class Critter4 extends Critter{
	int dir;
	int age;
	@Override
	public void doTimeStep() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean fight(String opponent) {
		if (opponent.equals("Critter1")) {
			walk(2); 
			return false; 
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "4";
	}

}
