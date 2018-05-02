package com.centrica.excerise;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class Centrica 
{
	private static Logger log = Logger.getLogger(Centrica.class);
	private static final int NUMBER_OF_ARGUMENTS = 2;
	
    public static void main( String[] args )
    {
    	//Validate number of input parameters
    	if(args.length == NUMBER_OF_ARGUMENTS) {
    		//Check file
    		FileRead read = new FileRead();
    		try {
				read.validateInput(args);
			} catch (IOException e) {
				//Auto-generated catch block
				log.log(Level.DEBUG, "Error: " + e.getLocalizedMessage());
			}
    	}
    	else {
    		//Log incorrect input
    		log.log(Level.ERROR, "Invalid parameters entered.");
    	}
    	
    }
}
