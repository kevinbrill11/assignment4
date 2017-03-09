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
 *  Direction of regular movement: down
 *  
 *  When fleeing from a fight, will move upward.
 * 
 *  This critter reproduces if it has at least 80 energy
 *  
 *
 */
public class Critter4 extends Critter{
	int dir;
	int age;
	
	public Critter4(){
		age = 0;
		dir = 6;
	}
	@Override
	public void doTimeStep() {
		age++;
		
		if(age<=10){
			walk(dir);
		}
		
		if (this.getEnergy() >= 80) {
			Critter4 crit = new Critter4();
			reproduce(crit, Critter.getRandomInt(8)); //reproduce randomly around the parent
		} 
	}

	@Override
	public boolean fight(String opponent) {
		if (opponent.equals("1")) {
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
