package com.flowergarden.bouquet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;

public class MarriedBouquet implements Bouquet<GeneralFlower> {

	private float assemblePrice = 120;
	private Map<String, Set<GeneralFlower>> flowerList = new HashMap<>();

	@Override
	public float getPrice() {
		if (flowerList.isEmpty()) return 0;
		float result = assemblePrice;
		for (GeneralFlower generalFlower : flowerList) {
			result += generalFlower.getPrice();
		}
		return result;
	}

	@Override
	public void addFlower(GeneralFlower flower) {
			flowerList.add(flower);
	}

	@Override
	public Collection<GeneralFlower> searchFlowersByLenght(int start, int end) {
		List<GeneralFlower> searchResult = new ArrayList<GeneralFlower>();
		for (GeneralFlower flower : flowerList) {
			if (flower.getLenght() >= start && flower.getLenght() <= end) {
				searchResult.add(flower);
			}
		}
		return searchResult;
	}

	@Override
	public void sortByFreshness() {
		Collections.sort(flowerList);
	}

	@Override
	public Collection<GeneralFlower> getFlowers() {
		return flowerList;
	}

	public void setAssembledPrice(float price) {
		assemblePrice = price;
	}
}
