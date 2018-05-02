package com.centrica.excerise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class FileRead {

	private static Logger log = Logger.getLogger(FileRead.class);
	private final int INPUT_FILE = 0;
	private final int OUTPUT_FILE = 1;

	/**
	 * Validate the command line inputs
	 * 
	 */
	private HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
	private Stream<Entry<String, Integer>> wordMapSorted;
	StringBuilder bui = new StringBuilder();

	public void validateInput(String[] arr) throws IOException {

			try (BufferedReader read = new BufferedReader(new FileReader(new File(arr[INPUT_FILE])))) {

				String line;

				while ((line = read.readLine()) != null) {
					log.log(Level.INFO, "Processing string: " + line);
					processLine(line);
				}
				
				sortKeyByValue(arr);
				
				writeToFile(arr);

			} catch (IOException e) {
				log.log(Level.DEBUG, "Error: " + e.getMessage());
			}

	}
	
	/**
	 * Access map for testing purposes
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getMap(){
		return this.wordMap;
	}

	/**
	 * Process each line in file and add each unique word to the Map
	 * 
	 * @param line
	 */
	private void processLine(String line) {

		String[] lineArray = line.split("\\W");

		for (String str : lineArray) {
			// check list contains str if not add to list,
			// if true find str "key" and increase value
			if (wordMap.containsKey(str)) {
				wordMap.put(str, wordMap.get(str) + 1);
				log.log(Level.INFO, "Increase count for " + str);
			} else {
				if (!str.isEmpty()) {
					wordMap.put(str, 1);
					log.log(Level.INFO, "Adding new string: " + str);
				}
			}
		}
	}

	/**
	 * Sort the Map of words into ascending value
	 * 
	 * @param arr
	 */
	private void sortKeyByValue(String[] arr) {
		wordMapSorted = wordMap.entrySet().stream().sorted(Map.Entry.comparingByValue());

		wordMapSorted.forEach(e -> processEntry(e, arr));

	}

	/**
	 * Add the key value of the map to StringBuilder for writing to 
	 * file
	 * 
	 * @param en
	 * @param arr
	 */
	private void processEntry(Map.Entry<String, Integer> en, String[] arr) {
		bui.append(en.getKey() + "\n");

	}

	/**
	 * Write the contents of the Map to a output file
	 * 
	 * @param arr
	 * @throws IOException
	 */
	private void writeToFile(String[] arr) throws IOException {

			FileWriter writer = new FileWriter(new File(arr[OUTPUT_FILE]));

			writer.write(bui.toString());
			writer.flush();

			writer.close();
		
	}

}
