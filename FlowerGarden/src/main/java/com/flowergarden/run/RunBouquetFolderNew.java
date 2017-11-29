package com.flowergarden.run;


import com.flowergarden.bouquet.MarriedBouquetPersistence;
import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

import java.io.File;

public class RunBouquetFolderNew {

    public static void main(String[] args) {
        GeneralFlower newRose, newChamomile;
        MarriedBouquetPersistence firstBouquet = new MarriedBouquetPersistence();

        for (int i = 0; i < 5; i++) {
            firstBouquet.addFlower(new Rose(
        }

        for (int i = 0; i < 2; i++) {
            firstBouquet.addFlower(new Chamomile(12, 50, 30, new FreshnessInteger(20 + i)));
        }

        firstBouquet.saveToFolder("bouquets" + File.pathSeparator + "married_bouquet_" + firstBouquet.hashCode());

        MarriedBouquetPersistence secondBouquet =
                firstBouquet.assembleFromFolder("bouquets" + File.pathSeparator + "married_bouquet_" + firstBouquet.hashCode());


        System.out.println("Count of flowers in first bouquet: " + firstBouquet.getFlowers().size());
        System.out.println("Count of flowers in second bouquet: " + secondBouquet.getFlowers().size());
    }
}
