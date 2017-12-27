package com.flowergarden.file;

import java.io.*;
import java.util.*;

import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.exception.FlowerNotFoundException;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

public class MarriedBouquetFilePersistence {

	// load flower from file
	private GeneralFlower loadFlowerFromFile(String folderPath) throws FlowerNotFoundException {
		String s, flowerClassName = "";
		int flowerLength = 0;
		int freshnessFlower = 0;
		float flowerPrice = 0;
		try (FileReader fr = new FileReader(folderPath); BufferedReader br = new BufferedReader(fr)) {
			// ClassName
			flowerClassName = br.readLine();
			// HashCode
			br.readLine();
			// Lenght
			s = br.readLine();
			s = s.substring(8);
			// System.out.println(s);
			flowerLength = Integer.valueOf(s);
			// Price
			s = br.readLine();
			s = s.substring(7);
			// System.out.println(s);
			flowerPrice = Float.valueOf(s);
			// Freshness
			s = br.readLine();
			s = s.substring(11);
			// System.out.println(s);
			freshnessFlower = Integer.valueOf(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		switch (flowerClassName) {
		case "ClassName: Rose":
			return new Rose(true, flowerLength, flowerPrice, new FreshnessInteger(freshnessFlower));
		case "ClassName: Chamomile":
			return new Chamomile(12, flowerLength, flowerPrice, new FreshnessInteger(freshnessFlower));
		default:
			throw new FlowerNotFoundException("Error in class name.");
		}
	}

	public MarriedBouquet assembleFromFolder(String folderPath) {
		MarriedBouquet marriedBouquet = new MarriedBouquet();
		String directoryName = folderPath.replace(":", "/");
		File directory = new File(directoryName);
		// get all files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (!file.isDirectory()) {
				// System.out.println(file.getName());
				// System.out.println(file.getAbsolutePath());
				try {
					// load flower from file
					marriedBouquet.addFlower(loadFlowerFromFile(file.getAbsolutePath()));
				} catch (FlowerNotFoundException ex) {
					ex.printStackTrace();
					// ex.myOwnExceptionMsg();
				}
			}
		}
		return marriedBouquet;
	}

	// save flower from file
	private void saveFlowerToFile(String folderPath, GeneralFlower flower) {
		try (FileWriter fw = new FileWriter(
				folderPath + flower.getClass().getSimpleName() + "_" + flower.hashCode() + ".txt");
				BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write("ClassName: " + flower.getClass().getSimpleName());
			bw.newLine();
			bw.write("HashCode: " + flower.hashCode());
			bw.newLine();
			bw.write("Lenght: " + flower.getLenght());
			bw.newLine();
			bw.write("Price: " + flower.getPrice());
			bw.newLine();
			bw.write("Freshness: " + flower.getFreshness().getFreshness());
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// if the directory does not exist, create it
	private void createFolder(String folderName) {
		File theDir = new File(folderName);
		if (!theDir.exists()) {
			System.out.println("Creating directory: " + theDir.getName());
			boolean result = false;
			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
				se.printStackTrace();
			}
			if (result) {
				System.out.println("DIR created");
			}
		}
	}

	public void saveToFolder(String folderPath, MarriedBouquet bouquet) {
		String t = "";
		String[] folders = folderPath.split(File.pathSeparator);
		for (int i = 0; i < 2; i++) {
			t = t.concat(folders[i]);
			// check and create folder
			createFolder(t);
			t = t.concat("/");
		}
		// System.out.println(t);

		Collection<GeneralFlower> flowers = bouquet.getFlowers();
		for (GeneralFlower flower : flowers) {
			saveFlowerToFile(t, flower);
		}

	}
}
