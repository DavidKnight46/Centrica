package com.centrica.excerise;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class FileReadTest {
	
	FileRead read = new FileRead();
	final private String FILE_IN = "C:\\Users\\New\\Desktop\\Hello_World_Test.txt";
	final private String FILE_OUT = "C:\\Users\\New\\Desktop\\Hello_World_Test_FINAL.txt";
	final private int SIZE_ARRAY = 2;
	
	String[] testArray = { FILE_IN, FILE_OUT };

	@Test
	public void validateInputTest() throws IOException {
		//fail("Not yet implemented");
		
		read.validateInput(testArray);
		
		assertTrue(read.getMap().containsKey("Hello"));
		assertTrue(read.getMap().containsKey("World"));
		
		assertTrue(read.getMap().size() == SIZE_ARRAY);
		
	}

}
