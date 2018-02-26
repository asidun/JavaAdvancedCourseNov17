package com.flowergarden.bouquet.adapter;

import java.util.Collection;

import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.flowers.GeneralFlower;

public class MarriedBouquetAdapter {
	
	private MarriedBouquet bouquet;

	public MarriedBouquetAdapter(MarriedBouquet bouquet){
		this.bouquet = bouquet;
	}
	
	public Collection<GeneralFlower> searchFlowersLongerThen(int start){
		return bouquet.searchFlowersByLenght(start, Integer.MAX_VALUE);
	}

}
