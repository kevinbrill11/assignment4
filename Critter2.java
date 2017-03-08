package assignment4;

/**
 * @author grays
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
		if(oponent.equals("Algae"))
			return true;
		return false;
	}
	
	@Override
	public String toString(){
		return "2";
	}

}
