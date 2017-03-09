package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Kevin Brill
 * kjb2786
 * 16230
 * Grayson Barrett
 * gmb974
 * 16230
 * Slip days used: <0>
 * Spring 2017
 */


import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


/**
 * @author grays
 * skrt skrt
 */
public abstract class Critter {
	private static String myPackage;
	private static final String[] names = {"Algae", "Craig", "MyCritter1", "MyCritter6", "MyCritter7", "Critter1", "Critter2", "Critter3", "Critter4"};
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private static int timestep = 0;
	private static int currentStep = 0;

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	private int walked;
	
	/**This function makes the critter walk one space in the given direction
	 * 
	 * @param direction is the direction to walk
	 */
	protected final void walk(int direction) {
		energy = energy - Params.walk_energy_cost;
		if(walked == timestep) //if already walked in this time step
			return;
		walked = timestep;    //keeping track of last time step walked in
		
		switch(direction){
		case 0:
			this.x_coord++;
			break;
		case 1:
			this.x_coord++;
			this.y_coord--;
			break;
		case 2:
			this.y_coord--;
			break;
		case 3:
			this.x_coord--;
			this.y_coord--;
			break;
		case 4:
			this.x_coord--;
			break;
		case 5:
			this.y_coord++;
			this.x_coord--;
			break;
		case 6:
			this.y_coord++;
			break;
		case 7:
			this.y_coord++;
			this.x_coord++;
			break;
		default:
			System.out.println("you're so bad at inputting valid directions");
		}
		if(x_coord >= Params.world_width)		//handle the wrapping
			x_coord = 0;
		if(y_coord >= Params.world_height)
			y_coord = 0;
		if(x_coord < 0)
			x_coord = Params.world_width - 1;
		if(y_coord < 0)
			y_coord = Params.world_height -1;
	}
	
	/**This function makes the critter move two spaces in the given direction
	 * 
	 * @param direction is the direction to move
	 */
	protected final void run(int direction) {
		energy = energy - Params.run_energy_cost;
		if(walked == timestep)
			return;
		walked = timestep;
		
		switch(direction){
		case 0:
			this.x_coord+=2;
			break;
		case 1:
			this.x_coord+=2;
			this.y_coord-=2;
			break;
		case 2:
			this.y_coord-=2;
			break;
		case 3:
			this.x_coord-=2;
			this.y_coord-=2;
			break;
		case 4:
			this.x_coord-=2;
			break;
		case 5:
			this.y_coord+=2;
			this.x_coord-=2;
			break;
		case 6:
			this.y_coord+=2;
			break;
		case 7:
			this.y_coord+=2;
			this.x_coord+=2;
			break;
		default:
			System.out.println("you're so bad at inputting valid directions");
		}
		if(x_coord >= Params.world_width)		//handle the wrapping
			x_coord %= Params.world_width;
		if(y_coord >= Params.world_height)
			y_coord %= Params.world_height;
		if(x_coord < 0)
			x_coord += Params.world_width;
		if(y_coord <0)
			y_coord += Params.world_height;
	}
	
	/**This function spawns a new critter of the same type as the parent
	 * in a given direction
	 * 
	 * @param offspring is the type of the baby
	 * @param direction	is the direction to put the baby
	 */
	
	protected final void reproduce(Critter offspring, int direction) {
		//ensure the parent is alive with enough energy
		if(this.energy < Params.min_reproduce_energy){
			return;
		}
		
		offspring.energy = (this.energy)/2;
		this.energy = (this.energy+1)/2;    //added one to ensure rounding up
		
		offspring.x_coord = this.x_coord;
		offspring.y_coord = this.y_coord;
		
		offspring.walk(direction);
		offspring.energy += Params.walk_energy_cost; //add back energy used to walk
		
		babies.add(offspring);
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		boolean isName = false;
		for(int k=0; k<names.length; k++){
			if(names[k].equals(critter_class_name))
				isName = true;
		}
		
		if(!isName){
			throw new InvalidCritterException("Cannot find critter: " + critter_class_name);
		}
		
		Class<?> myCritter = null;
		Constructor<?> constructor = null;
		Object instanceOfMyCritter = null;

		try {
			String name = myPackage + "." + critter_class_name;
			myCritter = Class.forName(name); 	// Class object of specified name
		} 
		catch (Exception e) { //classNotFoundException
			e.printStackTrace();
		}
		try {
			constructor = myCritter.getConstructor();		// No-parameter constructor object
			instanceOfMyCritter = constructor.newInstance();	// Create new object using constructor
		} 
		catch (Exception e ){
			e.printStackTrace();
		}

		Critter me = (Critter)instanceOfMyCritter;		// Cast to Critter
		
		me.y_coord = getRandomInt(Params.world_height);
		me.x_coord = getRandomInt(Params.world_width);
		me.energy = Params.start_energy;
		me.walked = -1;
		
		population.add(me);
	}
	
	/**This method was used for testing purposes to place a new critter
	 * in a specific location
	 * 
	 * @param posX is the x coordinate of the new critter
	 * @param posY is the y coordinate of the new critter
	 * 
	 */
	/*public static void makeCritter(String critter_class_name, int x, int y) throws InvalidCritterException {
		boolean isName = false;
		for(int k=0; k<names.length; k++){
			if(names[k].equals(critter_class_name))
				isName = true;
		}
		
		if(!isName){
			throw new InvalidCritterException("Cannot find critter: " + critter_class_name);
		}
		
		Class<?> myCritter = null;
		Constructor<?> constructor = null;
		Object instanceOfMyCritter = null;

		try {
			String name = myPackage + "." + critter_class_name;
			myCritter = Class.forName(name); 	// Class object of specified name
		} 
		catch (Exception e) { //classNotFoundException
			e.printStackTrace();
			throw new InvalidCritterException(critter_class_name);
		}
		try {
			constructor = myCritter.getConstructor();		// No-parameter constructor object
			instanceOfMyCritter = constructor.newInstance();	// Create new object using constructor
		} 
		catch (Exception e ){
			e.printStackTrace();
		}

		Critter me = (Critter)instanceOfMyCritter;		// Cast to Critter
		
		me.x_coord = x;
		me.y_coord = y;
		me.energy = Params.start_energy;
		
		population.add(me);
	}*/
	
	/**This method determines if a specific location is occupied by something
	 * 
	 * @param posX is the x coordinate of the location to be checked
	 * @param posY is the y coordinate of the location to be checked
	 * @return true if occupied, false if not
	 */
	public static boolean isOccupied(int posX, int posY){
		for(Critter c: population){
			if(c.x_coord == posX && c.y_coord == posY){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method deducts the resting energy from critters
	 */
	private void updateRestEnergy(){
		energy = energy - Params.rest_energy_cost;
	}
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();

		Class<?> myCritter = null;
		Constructor<?> constructor = null;
		Object instanceOfMyCritter = null;
		try {
			myCritter = Class.forName(myPackage + "." + critter_class_name);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidCritterException(critter_class_name);
		}
		
		try {
			constructor = myCritter.getConstructor();		// No-parameter constructor object
			instanceOfMyCritter = constructor.newInstance();	// Create new object using constructor
		} 
		catch (Exception e ){
			e.printStackTrace();
		}
		
		for(Critter critter: population){
			if(instanceOfMyCritter.getClass().isInstance(critter)){
				result.add(critter);
			}
		}
	
		return result;
	}
	
	public static void stats(String name){
		java.util.ArrayList<Critter> critters = null;
		Class<?> myClass = null;
		Method method = null;
		try {
			critters = (java.util.ArrayList<Critter>) Critter.getInstances(name);
		} catch (InvalidCritterException e) {
			System.out.println("List Creation failed");
			e.printStackTrace();
		}
		
		try{
			myClass = Class.forName(myPackage + "." + name);
			method = myClass.getMethod("runStats", List.class);
		}
		catch(Exception e){
			System.out.println("Method method stuff failed");
		}
		
		try {
			method.invoke(myClass, critters);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("invoke failed");
			e.printStackTrace();
		}
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
		displayWorld();
	}
	/**
	 * This method handles encounters between critters and calls the 
	 * fight methods of critters
	 * @param c1 is the first critter in the encounter
	 * @param c2 is the second critter in the encounter
	 */
	public static void doEncounter(Critter c1, Critter c2){
		//int retval = 0;
		
		int c1Roll = 1;
		int c2Roll = 1;
		int x1old = c1.x_coord;
		int y1old = c1.y_coord;
		int x2old = c2.x_coord;
		int y2old = c2.y_coord;
		int flea;
		if(!c1.fight(c2.toString())){ //if Critter doesn't want to fight, they make no attempt to fight
			c1Roll = 0;
		}
		if(!c2.fight(c1.toString())){
			c2Roll = 0;
		}
		
		flea = 0;
		if(x1old != c1.x_coord || y1old != c1.y_coord){ //c1 tried to flea the fight
			for(Critter c: population){
				if(c.x_coord == c1.x_coord && c.y_coord == c1.y_coord)
					flea++;
			}
		}
		if(flea > 1){ //it matched the coordinates of more critters than just itself
			//move it back to original spot
			c1.x_coord = x1old;
			c1.y_coord = y1old;
		}
		
		flea = 0;
		if(x2old != c2.x_coord || y2old != c2.y_coord){ //c2 tried to flea the fight
			for(Critter c: population){
				if(c.x_coord == c2.x_coord && c.y_coord == c2.y_coord)
					flea++;
			}
		}
		if(flea > 1){ //it matched the coordinates of more critters than just itself
			//move it back to original spot
			c2.x_coord = x2old;
			c2.y_coord = y2old;
		}
		if(c1.energy > 0 && c2.energy > 0){ //check to see if still alive
			if(c1.x_coord == c2.x_coord && c1.y_coord == c2.y_coord){ //check if they're still in the same position
				if(c1Roll != 0)
					c1Roll = getRandomInt(c1.energy);
				if(c2Roll != 0)
					c2Roll = getRandomInt(c2.energy);
				
				if(c1Roll >= c2Roll){
					c1.energy += (c2.energy)/2;
					population.remove(c2); 
					//return 1;
				}
				else{
					c2.energy += (c1.energy)/2;
					population.remove(c1);
					//return 1;
				}
			}
		}
		else{ //at least one is dead from running away
			if(c1.energy <= 0){
				population.remove(c1);
				//retval++; TODO
			}
			if(c2.energy <= 0){
				population.remove(c2);
				//retval++; TODO
			}
		}
		//return retval; TODO
	}
	
	/**
	 * This method is a helper method for the makeCritter method
	 * @param class_name is the type of critter to be made
	 * @param num is the number of critters to make
	 * @throws InvalidCritterException if not a valid critter type
	 */
	public static void make(String class_name, int num) throws InvalidCritterException{
		if(num<0)
			System.out.println("error processing: make " + class_name + " " + num);
		for(int i = 0; i < num; i++){	
				Critter.makeCritter(class_name);
        }
	}
	
	
	/*public static void make(String class_name, int num, int x, int y){
		for(int i = 0; i < num; i++){	
        	try {
				Critter.makeCritter(class_name,x,y);
			} catch (InvalidCritterException e) {
				e.printStackTrace();
			}
        }
	}*/
	/**
	 * This function is the timekeeper of the program, which is called
	 * to advance the world by a single time step
	 */
	public static void worldTimeStep() {
		//int death = 0;
		timestep ++;
		while(currentStep < timestep){
			for(Critter x: population){
				x.doTimeStep();
			}
			for(int x = 0; x < population.size(); x++){
				if(population.get(x).getEnergy() <= 0){
					population.remove(x);				}
			}
			for(int i=0; i<population.size(); i++){
				Critter temp = population.get(i);
				for(int j=i+1; j<population.size(); j++){
					if(!(temp == population.get(j))){ //compare memory addresses to check if same object
						if(temp.x_coord == population.get(j).x_coord && temp.y_coord == population.get(j).y_coord){
							Critter temp2 = population.get(j);
							doEncounter(temp, temp2); //did someone die in the fight?
							if(temp.energy <= 0){
								j--;
								i--;
							}
							if(temp2.energy <= 0){
								j--;
							}
							//i -= death;
						}
					}
				}
			}
			
			for(Critter x: population){ //update rest energy
				x.updateRestEnergy();
			}
			
			for(int i = 0; i < Params.refresh_algae_count; i++){ //create new algae at end of each timestep
				try {
					makeCritter("Algae");
				} catch (InvalidCritterException e) {
					e.printStackTrace();
				}
			}
			
			population.addAll(babies);
			babies.clear(); //kill all the babies
			currentStep++;
		}
	}
	/**
	 * This is a helper function for the time step to keep track of the
	 * number of times to call worldTimeStep()
	 * @param n is the number of times to call worldTimeStep()
	 */
	public static void worldTimeStep(int n) {
		timestep += n-1;
		worldTimeStep();
	}
	
	/**
	 * This function displays the world
	 */
	public static void displayWorld() {
		System.out.print("+");
		for(int i = 0; i < Params.world_width; i++){
			System.out.print("-"); //TODO
			//System.out.print(i%10 + " ");
		}
		System.out.println("+");
		boolean printed;
		for(int y=0; y<Params.world_height; y++){
			System.out.print("|"); //TODO
			//System.out.print(y%10);
			for(int x=0; x<Params.world_width; x++){
				printed = false;
				for(Critter c: population){
					if(c.x_coord == x && c.y_coord == y){
						printed = true;
						System.out.print(c); //TODO
						break; //printing only the first appearance of overlapping critters
					}
				}
				if(!printed)
					System.out.print(" ");	//TODO			
			}
			System.out.println("|");
		}
		
		System.out.print("+");
		for(int i = 0; i < Params.world_width ; i++){
			System.out.print("-");
		}
		System.out.println("+");
	}
}
