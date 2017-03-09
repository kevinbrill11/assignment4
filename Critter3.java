package assignment4;
/* CRITTERS Critter3.java
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
 *
 */
public class Critter3 extends Critter {

	@Override
	public void doTimeStep() {
		
	}

	@Override
	public boolean fight(String oponent) {
		//always fight Algae
		if(oponent.equals("@"))
			return true;
		
		//produce new Critter3, lose the fight and die
		//essentially teleporting
		try {
			Critter.makeCritter("Critter3");
		} 
		catch (InvalidCritterException e) {
			System.out.println("Error teleporting");
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "3";
	}

}
