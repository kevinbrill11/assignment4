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
 * This critter has a 10% chance of reproducing during
 * a timestep and "teleports" away from fights against
 * everything but Algae
 */
public class Critter3 extends Critter {

	/**
	 * 10% chance of producing offspring
	 * @see assignment4.Critter#doTimeStep()
	 */
	@Override
	public void doTimeStep() {
		if(Critter.getRandomInt(10) == 9){
			Critter3 baby = new Critter3();
			reproduce(baby, Critter.getRandomInt(8));			
		}

	}

	
	/**
	 * Critter only fights if the oponent is Algae, otherwise it
	 * "teleports" by making a new Critter in a random spot
	 * and losing the fight and subsequently dying. 
	 * @see assignment4.Critter#fight(java.lang.String)
	 * 
	 * @param oponent to be fought
	 * @return boolean value of attempt to fight
	 */
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
