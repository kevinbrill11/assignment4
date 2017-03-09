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

	@Override
	public void doTimeStep() {
		int dir = ((timeStep/sideLen)%4)*2;
		walk(dir);
		timeStep++;		
	}

	@Override
	public boolean fight(String oponent) {
		sideLen++;
		if(oponent.equals("@") || oponent.equals("3"))
			return true;
		return false;
	}
	
	@Override
	public String toString(){
		return "2";
	}

}
