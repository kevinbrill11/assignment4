package assignment4;
/* CRITTERS Main.java
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
 * Github URL: https://github.com/kevinbrill11/assignment4
 */

import java.util.Scanner;
import java.io.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        
        boolean done = false;
        
        /*for(int i = 0; i < 20; i++){			//fake population
        	try {
				Critter.makeCritter("Algae");
			} catch (InvalidCritterException e) {
				e.printStackTrace();
			}
        	if(i%4 ==0){
        		try {
					Critter.makeCritter("Craig");
				} catch (InvalidCritterException e) {
					e.printStackTrace();
				}
        	}
        }*/
        
        while(!done){
        	System.out.print("critters> ");
        	String read = kb.nextLine();
        	String[] input = read.split(" ");
        	switch(input[0]){
        	case "quit": 
        		if(input.length > 1)
        			System.out.println("error processing: " + read);
        		else
        			done = true;
        		break;
        	case "show":
        		if(input.length > 1)
        			System.out.println("error processing: " + read);
        		else
        			Critter.displayWorld();
        		break;
        	case "step":
        		if(input.length == 2){
        			try{
        				Critter.worldTimeStep(Integer.parseInt(input[1])); //possible exception
        			}
        			catch(Exception e){
        				System.out.println("error processing: " + read);
        			}
        		}
        		else if(input.length == 1){
        			Critter.worldTimeStep();
        		}
        		else
        			System.out.println("error processing: " + read);
        		break;
        	case "make":
        		if(input.length == 5){ //TODO
        			Critter.make(input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]), Integer.parseInt(input[4])); //possible exception
        		}
        		else if(input.length == 3){
        			try{
        				Critter.make(input[1], Integer.parseInt(input[2])); //possible exception
        			}
        			catch(Exception e){
       					System.out.println("error processing: " + read);
       				}
        		}
        		else if(input.length == 2){
        			try {
						Critter.make(input[1], 1);
					} catch (InvalidCritterException e) {
						System.out.println("error processing: " + read);
					}
        		}
        		else
        			System.out.println("error processing: " + read);
        		break;
        	case "seed":
        		if(input.length != 2)
        			System.out.println("error processing: " + read);
        		else
        			try{
        				Critter.setSeed(Integer.parseInt(input[1]));
        			}
        		catch(Exception e){
        			System.out.println("error processing: " + read);
        		}
        		break;
        	case "stats":
        		if(input.length != 2)
        			System.out.println("error processing: " + read);
        		else try {
					Critter.stats(input[1]);
				} 
        		catch (Exception e) {
					e.printStackTrace();
				}
        		break;

        	default:
        		System.out.println("invalid command: " + read);
        	}
        }
        
        /* Write your code above */
        System.out.flush();
        

    }
}
