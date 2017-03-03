/*Kevin Brill
 * kjb2786
 * Grayson Barrett
 * <Grayson eid>
 */
package assignment4;


public class InvalidCritterException extends Exception {
	String offending_class;
	
	public InvalidCritterException(String critter_class_name) {
		offending_class = critter_class_name;
	}
	
	public String toString() {
		return "Invalid Critter Class: " + offending_class;
	}

}
