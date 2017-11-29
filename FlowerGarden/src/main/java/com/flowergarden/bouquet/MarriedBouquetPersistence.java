package com.flowergarden.bouquet;


import java.io.*;
import java.util.*;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;
import com.flowergarden.flowers.FlowerNotFoundException;

public class MarriedBouquetPersistence implements Bouquet<GeneralFlower> {

    private float assemblePrice = 120;

    private Map<String, Set<GeneralFlower>> flowerMap = new HashMap<>();//String - name of flower, for example "rose"

    @Override
    public float getPrice() {
        float priceAllFlowers = assemblePrice;
        boolean isBouquetNotEmpty = false;

        for (Map.Entry<String, Set<GeneralFlower>> entry : flowerMap.entrySet()) {
            Set<GeneralFlower> flowerSet = (Set<GeneralFlower>) entry.getValue();
            if (!flowerSet.isEmpty()) {
                isBouquetNotEmpty = true;
            }
        }

        if (!isBouquetNotEmpty) {
            return 0;
        }

        for (Map.Entry<String, Set<GeneralFlower>> entry : flowerMap.entrySet()) {
            Set<GeneralFlower> flowerSet = (Set<GeneralFlower>) entry.getValue();
            for (GeneralFlower flower : flowerSet) {
                priceAllFlowers += flower.getPrice();
            }
            //System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }

        return priceAllFlowers;
    }

    @Override
    public void addFlower(GeneralFlower flower) {
        String flowerName = flower.getClass().getSimpleName();
        Set<GeneralFlower> flowerSet = flowerMap.get(flowerName);
        if (flowerSet == null) {
            flowerSet = new TreeSet<GeneralFlower>();
            flowerMap.put(flowerName, flowerSet);
        }
        if (flowerSet == null) {
            System.out.println("1111 : ");
        }
        flowerSet.add(flower);
    }

    @Override
    public Collection<GeneralFlower> searchFlowersByLenght(int start, int end) {
        Set<GeneralFlower> searchResult = new HashSet<>();
        for (Map.Entry<String, Set<GeneralFlower>> entry : flowerMap.entrySet()) {
            Set<GeneralFlower> flowerSet = (Set<GeneralFlower>) entry.getValue();
            for (GeneralFlower flower : flowerSet) {
                if (flower.getLenght() >= start && flower.getLenght() <= end) {
                    searchResult.add(flower);
                }
            }
        }
        return searchResult;
    }

    @Override
    public void sortByFreshness() {
    }

    @Override
    public Collection<GeneralFlower> getFlowers() {
        Set<GeneralFlower> searchResult = new HashSet<>();
        for (Map.Entry<String, Set<GeneralFlower>> entry : flowerMap.entrySet()) {
            Set<GeneralFlower> flowerSet = (Set<GeneralFlower>) entry.getValue();
            for (GeneralFlower flower : flowerSet) {
                searchResult.add(flower);
            }
        }
        return searchResult;
    }

    public void setAssembledPrice(float price) {
        assemblePrice = price;
    }

    //load flower from file
    public GeneralFlower loadFlowerFromFile(String folderPath) throws FlowerNotFoundException {
        String s, flowerClassName = "";
        int flowerLength = 0;
        int freshnessFlower = 0;
        float flowerPrice = 0;
        try (FileReader fr = new FileReader(folderPath);
             BufferedReader br = new BufferedReader(fr)) {
            //ClassName
            flowerClassName = br.readLine();
            //HashCode
            br.readLine();
            //Lenght
            s = br.readLine();
            s = s.substring(8);
            //System.out.println(s);
            flowerLength = Integer.valueOf(s);
            //Price
            s = br.readLine();
            s = s.substring(7);
            //System.out.println(s);
            flowerPrice = Float.valueOf(s);
            //Freshness
            s = br.readLine();
            s = s.substring(11);
            //System.out.println(s);
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

    public MarriedBouquetPersistence assembleFromFolder(String folderPath) {
        MarriedBouquetPersistence marriedBouquet = new MarriedBouquetPersistence();
        String directoryName = folderPath.replace(":", "/");
        File directory = new File(directoryName);
        //get all files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (!file.isDirectory()) {
                //System.out.println(file.getName());
                //System.out.println(file.getAbsolutePath());
                try {
                    //load flower from file
                    marriedBouquet.addFlower(loadFlowerFromFile(file.getAbsolutePath()));
                } catch (FlowerNotFoundException ex) {
                    ex.printStackTrace();
                    //ex.myOwnExceptionMsg();
                }
            }
        }
        return marriedBouquet;
    }

    //save flower from file
    public void saveFlowerToFile(String folderPath, GeneralFlower flower) {
        try (FileWriter fw = new FileWriter(folderPath + flower.getClass().getSimpleName() + "_" + flower.hashCode() + ".txt");
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
    public void createFolder(String folderName) {
        File theDir = new File(folderName);
        if (!theDir.exists()) {
            System.out.println("Creating directory: " + theDir.getName());
            boolean result = false;
            try {
                theDir.mkdir();
                result = true;
            } catch (SecurityException se) {
                //handle it
                se.printStackTrace();
            }
            if (result) {
                System.out.println("DIR created");
            }
        }
    }

    public void saveToFolder(String folderPath) {
        String t = "";
        String[] folders = folderPath.split(File.pathSeparator);
        for (int i = 0; i < 2; i++) {
            t = t.concat(folders[i]);
            //check and create folder
            createFolder(t);
            t = t.concat("/");
        }
        //System.out.println(t);

        for (Map.Entry<String, Set<GeneralFlower>> entry : flowerMap.entrySet()) {
            Set<GeneralFlower> flowerSet = (Set<GeneralFlower>) entry.getValue();
            for (GeneralFlower flower : flowerSet) {
                saveFlowerToFile(t, flower);
            }
        }
    }
}
