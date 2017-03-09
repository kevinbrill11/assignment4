package assignment4;
/* CRITTERS Critter2.java
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
 * @author Grayson Barrett
 * Critter2 walks in squares:
 * the side length of the square starts at 1
 * and increases every time they get in a fight
 */
public class Critter2 extends Critter {
	private static int timeStep;
	private int sideLen = 1;
	
	static{
		timeStep = 0;
	}

	/**
	 * the critter walks in a direction based on the timeStep of 
	 * Critter2 and its side length.
	 * @see assignment4.Critter#doTimeStep()
	 */
	@Override
	public void doTimeStep() {
		int dir = ((timeStep/sideLen)%4)*2;
		walk(dir);
		timeStep++;		
	}

	/**
	 * Critter increases the side length of its square path
	 * with every fight. Attempts to fight all other Critters.
	 * @see assignment4.Critter#fight(java.lang.String)
	 * 
	 * @param oponent to be fought
	 * @return boolean value of attempt to fight
	 */
	@Override
	public boolean fight(String oponent) {
		sideLen++;
		return true;
	}
	
	@Override
	public String toString(){
		return "2";
	}

}
